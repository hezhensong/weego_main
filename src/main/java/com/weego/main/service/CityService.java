package com.weego.main.service;

import com.weego.main.dto.CityBaseInfoDto;
import com.weego.main.dto.CityHomeDto;

import java.util.List;

public interface CityService {

    List<CityBaseInfoDto> getOnlineCity();

    CityHomeDto getCityHome(String cityId);


}
