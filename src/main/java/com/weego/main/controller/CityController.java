package com.weego.main.controller;

import com.weego.main.dto.CityHomeDto;
import com.weego.main.dto.CityListDto;
import com.weego.main.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/city")
public class CityController {

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<CityListDto> getCityList() {
        return cityService.getCityList();
    }

    @RequestMapping(value = "/home/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public CityHomeDto getCityHome(@PathVariable("cityId") String cityId) {

        System.out.println(cityId);
        return cityService.getCityHome(cityId);
    }
}
