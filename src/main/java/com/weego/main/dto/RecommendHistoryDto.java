package com.weego.main.dto;

import com.weego.main.model.RecommendInfo;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendHistoryDto {
//    public Map<String, Object> data;
//
//    public Map<String, Object> getData() {
//        return data;
//    }
//
//    public void setData(Map<String, Object> data) {
//        this.data = data;
//    }

    private String cityId;

    private String cityName;

    private List<RecommendInfoDto> today;

    private List<RecommendInfoDto> yesterday;

    private List<RecommendInfoDto> dayBeforeYest;


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

    public List<RecommendInfoDto> getToday() {
        return today;
    }

    public void setToday(List<RecommendInfoDto> today) {
        this.today = today;
    }

    public List<RecommendInfoDto> getYesterday() {
        return yesterday;
    }

    public void setYesterday(List<RecommendInfoDto> yesterday) {
        this.yesterday = yesterday;
    }

    public List<RecommendInfoDto> getDayBeforeYest() {
        return dayBeforeYest;
    }

    public void setDayBeforeYest(List<RecommendInfoDto> dayBeforeYest) {
        this.dayBeforeYest = dayBeforeYest;
    }
}
