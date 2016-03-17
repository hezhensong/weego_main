package com.weego.main.dto;

import com.weego.main.model.DynamicContent;
import com.weego.main.model.RecommendContent;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendInfoDto {
    private String cityId;

    private String cityName;

    private Date time;

    private RecommendContentDto content;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public RecommendContentDto getContent() {
        return content;
    }

    public void setContent(RecommendContentDto content) {
        this.content = content;
    }
}
