package com.weego.main.service;

import com.weego.main.dto.CityHomeDto;
import com.weego.main.dto.CityListContinentDto;

import java.util.List;

public interface CityService {

    CityHomeDto getCityHome(String cityId);

    List<CityListContinentDto> getOnlineCityList();
}
