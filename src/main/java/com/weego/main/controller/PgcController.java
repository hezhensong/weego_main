package com.weego.main.controller;

import com.weego.main.dto.PgcListDto;
//import com.weego.main.service.PgcService;

import com.weego.main.model.Pgc;
import com.weego.main.service.impl.PgcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v3/pgc")
public class PgcController {

	@Autowired
	PgcServiceImpl pgcService;//PgcServiceImpl具体的实现类继承的接口

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Pgc> getPgcList() {
		return pgcService.getPgcList();
	}

}
