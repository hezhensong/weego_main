package com.weego.main.dto;

import java.util.List;

public class CityListContinentDto {

    private String continentName;
    private String continentNameEn;
    private List<CityListCityDto> cityList;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getContinentNameEn() {
        return continentNameEn;
    }

    public void setContinentNameEn(String continentNameEn) {
        this.continentNameEn = continentNameEn;
    }

    public List<CityListCityDto> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityListCityDto> cityList) {
        this.cityList = cityList;
    }
}
