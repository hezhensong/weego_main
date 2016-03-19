package com.weego.main.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendInfo {

    @Id
    private ObjectId id;

    @JsonProperty("city_id")
    private ObjectId cityId;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("start_time")
    private Date startTime;

    @JsonProperty("end_time")
    private Date end_time;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("coordination")
    private String coordination;

    @JsonProperty("content")
    private RecommendContent content;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getCityId() {
        return cityId;
    }

    public void setCityId(ObjectId cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public RecommendContent getContent() {
        return content;
    }

    public void setContent(RecommendContent content) {
        this.content = content;
    }

    public String getCoordination() {
        return coordination;
    }

    public void setCoordination(String coordination) {
        this.coordination = coordination;
    }
}
