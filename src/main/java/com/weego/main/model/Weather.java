package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.Id;

import java.util.Date;
import java.util.List;

public class Weather {

    @Id
    private String id;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("city_id")
    private String cityId;

    private WeatherCondition condition;

    private List<WeatherForecast> forecast;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }

    public List<WeatherForecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherForecast> forecast) {
        this.forecast = forecast;
    }
}
