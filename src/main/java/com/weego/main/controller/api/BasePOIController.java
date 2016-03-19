package com.weego.main.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weego.main.dto.POIListDto;
import com.weego.main.service.BasePOIService;

@RestController
@RequestMapping("/api/v3/city")
public class BasePOIController {
	
	@Autowired
	BasePOIService basePOIService;
	
	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
    @ResponseBody
    public POIListDto getCityList(
    		@RequestParam("cityId") String cityId,
    		@RequestParam("type") Integer type) {
		
        return basePOIService.getPOIsByCityId(cityId, type);
    }
}
