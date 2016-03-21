package com.weego.main.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weego.main.dto.SearchNearByDto;
import com.weego.main.service.SearchNearByService;

@RestController
@RequestMapping("/api/v3/city")
public class SearchNearbyController {
	
	@Autowired
	SearchNearByService searchNearByService;
	
	@ResponseBody
	@RequestMapping("/searchNearby")
	public SearchNearByDto getSearchNearByInfos(
			@RequestParam("cityId") String cityId,
    		@RequestParam("type") Integer type,
    		@RequestParam("coordination") String coordination,
			@RequestParam("sort") String sort) {
		
		return searchNearByService.getSearchNearByInfos(cityId, type, coordination, sort);
	}
}
