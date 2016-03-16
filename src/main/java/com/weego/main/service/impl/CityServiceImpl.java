package com.weego.main.service.impl;

import com.weego.main.dao.CityDao;
import com.weego.main.dto.CityBaseInfoDto;
import com.weego.main.dto.CityHomeDto;
import com.weego.main.dto.CityListDto;
import com.weego.main.model.City;
import com.weego.main.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<CityListDto> getCityList() {
        List<CityListDto> cityListDtoList = new ArrayList<>();

        List<City> cityList = cityDao.getOnlineCity();
        for (City city : cityList) {
        }

        return cityListDtoList;
    }

    @Override
    public CityHomeDto getCityHome(String cityId) {
        CityHomeDto cityHomeDto = new CityHomeDto();
        Map<String, Object> data = new HashMap<>();

        CityBaseInfoDto cityBaseInfoDto = new CityBaseInfoDto();
        City city = cityDao.getSpecifiedCity(cityId);
        cityBaseInfoDto.setCoverImage("还没找到呢。");

        data.put("baseInfo", cityBaseInfoDto);

        cityHomeDto.setData(data);
        return cityHomeDto;
    }
}
