package com.weego.main.controller;

import com.weego.main.dto.PgcListDto;
import com.weego.main.service.PgcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v3/pgc")
public class PgcController {

	@Autowired
	PgcService pgcService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<PgcListDto> getPgcList() {
		return pgcService.getPgcList();
	}

}
