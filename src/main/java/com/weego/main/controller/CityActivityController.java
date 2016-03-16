package com.weego.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weego.main.dto.CityActivityHomeDto;
import com.weego.main.service.CityActivityService;

@RestController
@RequestMapping("api/v3/city/activity")
public class CityActivityController {

	@Autowired
	private CityActivityService cityActivityService;

	@RequestMapping(value = "/{cityActivityId}", method = RequestMethod.GET)
	@ResponseBody
	public CityActivityHomeDto getCityActivityHome(@PathVariable("cityActivityId") String cityActivityId) {

		System.out.println(cityActivityId);
		return cityActivityService.getCityActivityHome(cityActivityId);

	}

}
