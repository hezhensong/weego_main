package com.weego.main.model;

import java.io.Serializable;

public class Weather implements Serializable {
    private static final long serialVersionUID = -7264688807134534953L;

    private String id;
    private String cityId;
    private String timestamp;
    private String yahooWeather;

    public String getYahooWeather() {
        return yahooWeather;
    }

    public void setYahooWeather(String yahooWeather) {
        this.yahooWeather = yahooWeather;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
