package com.weego.main.controller;

import com.weego.main.dao.CityDao;
import com.weego.main.dao.WeatherDao;
import com.weego.main.model.City;
import com.weego.main.model.Weather;
import com.weego.main.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/city")
public class CityController {

    @Autowired
    WeatherDao weatherDao;

    @Autowired
    CityDao cityDao;

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/home/{cityId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getCityHome(@PathVariable("cityId") String cityId) {

        System.out.println(cityId);

        List<City> cityList = cityDao.getCityHomeBaseInfo();

        String out = "";
        for (City city : cityList) {
            out += city.getId() + "<br/>";
            out += city.getCityName() + "<br/>";
            out += city.getCityNameEn() + "<br/>";
            out += city.getCityNamePy() + "<br/>";
            out += "<br/>";
        }

        return out;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getName() {
        List<Weather> weatherList = weatherDao.getAllCityWeather();

        String out = "";
        for (Weather weather : weatherList) {
            out += weather.getId() + "<br/>";
            out += weather.getCityId() + "<br/>";
            out += weather.getTimestamp() + "<br/>";
            out += weather.getYahooWeather() + "<br/>";
        }

        return out;
    }


}
