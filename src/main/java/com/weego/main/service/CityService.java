package com.weego.main.service;

import com.weego.main.dto.CityHomeDto;
import com.weego.main.dto.CityListContinentDto;
import com.weego.main.dto.CityListDto;

import java.util.List;

public interface CityService {

    List<CityListDto> getCityList();

    CityHomeDto getCityHome(String cityId);

    List<CityListContinentDto> getOnlineCityList();

//    List<CityListContinentDto> getCityList1();

}
