package com.weego.main.dto;

import com.weego.main.model.DynamicContent;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendDataDto {
    private String id;
    private String cityId;
    private Date time;
    private DynamicContent content;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public DynamicContent getContent() {
        return content;
    }

    public void setContent(DynamicContent content) {
        this.content = content;
    }
}
