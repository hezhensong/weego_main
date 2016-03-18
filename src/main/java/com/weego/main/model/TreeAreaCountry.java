package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TreeAreaCountry {

    @JsonProperty("name")
    private String name;

    @JsonProperty("name_en")
    private String nameEn;

    @JsonProperty("code")
    private String code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("city_list")
    private List<TreeAreaCity> cityList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TreeAreaCity> getCityList() {
        return cityList;
    }

    public void setCityList(List<TreeAreaCity> cityList) {
        this.cityList = cityList;
    }
}
