package com.weego.main.dto;

import java.util.List;

public class CityWeatherDto {

    private WeatherConditionDto weatherConditionDto;
    private List<WeatherForecastDto> weatherForecastDtoList;

    public WeatherConditionDto getWeatherConditionDto() {
        return weatherConditionDto;
    }

    public void setWeatherConditionDto(WeatherConditionDto weatherConditionDto) {
        this.weatherConditionDto = weatherConditionDto;
    }

    public List<WeatherForecastDto> getWeatherForecastDtoList() {
        return weatherForecastDtoList;
    }

    public void setWeatherForecastDtoList(List<WeatherForecastDto> weatherForecastDtoList) {
        this.weatherForecastDtoList = weatherForecastDtoList;
    }
}
