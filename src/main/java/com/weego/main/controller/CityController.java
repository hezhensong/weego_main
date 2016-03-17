package com.weego.main.controller;

import com.weego.main.dto.CityHomeDto;
import com.weego.main.dto.CityListDto;
import com.weego.main.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/city")
public class CityController {
    private Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<CityListDto> getCityList() {
        return cityService.getCityList();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public CityHomeDto getCityHome(@RequestParam("cityId") String cityId) {

        logger.info("cityId = {}", cityId);
        return cityService.getCityHome(cityId);
    }
}
