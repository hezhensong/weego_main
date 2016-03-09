package com.weego.main.model;


import java.io.Serializable;

public class City implements Serializable {
    private static final long serialVersionUID = -7851645676723567152L;

    private String id;
    private String cityName;
    private String cityNameEn;
    private String cityNamePy;
    private boolean isShow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getCityNamePy() {
        return cityNamePy;
    }

    public void setCityNamePy(String cityNamePy) {
        this.cityNamePy = cityNamePy;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
