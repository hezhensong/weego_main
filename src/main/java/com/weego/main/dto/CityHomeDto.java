package com.weego.main.dto;

public class CityHomeDto {

    private CityHomeBaseInfoDto baseInfo;
    private CityHomeLabelDto labels;
    private CityHomeWeatherDto weather;
    private CityHomePlanDto plan;
    private CityHomeActivityDto cityActivity;
    private CityHomePgcDto pgc;

    public CityHomeBaseInfoDto getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(CityHomeBaseInfoDto baseInfo) {
        this.baseInfo = baseInfo;
    }

    public CityHomeLabelDto getLabels() {
        return labels;
    }

    public void setLabels(CityHomeLabelDto labels) {
        this.labels = labels;
    }

    public CityHomeWeatherDto getWeather() {
        return weather;
    }

    public void setWeather(CityHomeWeatherDto weather) {
        this.weather = weather;
    }

    public CityHomePlanDto getPlan() {
        return plan;
    }

    public void setPlan(CityHomePlanDto plan) {
        this.plan = plan;
    }

    public CityHomeActivityDto getCityActivity() {
        return cityActivity;
    }

    public void setCityActivity(CityHomeActivityDto cityActivity) {
        this.cityActivity = cityActivity;
    }

    public CityHomePgcDto getPgc() {
        return pgc;
    }

    public void setPgc(CityHomePgcDto pgc) {
        this.pgc = pgc;
    }
}
