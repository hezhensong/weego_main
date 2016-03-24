package com.weego.main.dto;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendHistoryDto {

    private String cityId;

    private String cityName;

    private List<RecommendHistoryContentDto> today;

    private List<RecommendHistoryContentDto> yesterday;

    private List<RecommendHistoryContentDto> dayBeforeYest;


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<RecommendHistoryContentDto> getToday() {
        return today;
    }

    public void setToday(List<RecommendHistoryContentDto> today) {
        this.today = today;
    }

    public List<RecommendHistoryContentDto> getYesterday() {
        return yesterday;
    }

    public void setYesterday(List<RecommendHistoryContentDto> yesterday) {
        this.yesterday = yesterday;
    }

    public List<RecommendHistoryContentDto> getDayBeforeYest() {
        return dayBeforeYest;
    }

    public void setDayBeforeYest(List<RecommendHistoryContentDto> dayBeforeYest) {
        this.dayBeforeYest = dayBeforeYest;
    }
}
