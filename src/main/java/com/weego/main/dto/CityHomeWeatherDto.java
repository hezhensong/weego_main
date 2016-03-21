package com.weego.main.dto;

import java.util.List;

public class CityHomeWeatherDto {

    private WeatherConditionDto condition;
    private List<WeatherForecastDto> forecast;

    public WeatherConditionDto getCondition() {
        return condition;
    }

    public void setCondition(WeatherConditionDto condition) {
        this.condition = condition;
    }

    public List<WeatherForecastDto> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherForecastDto> forecast) {
        this.forecast = forecast;
    }
}
