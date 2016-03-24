package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class RecommendHistory {
    @Id
    private ObjectId id;

    @JsonProperty("city_id")
    private ObjectId cityId;

    @JsonProperty("user_id")
    private ObjectId userId;

    @JsonProperty("recommend_time")
    private Date recommendTime;

    @JsonProperty("recommend_content")
    private List<RecommendContent> recommendContentList;

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

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    public List<RecommendContent> getRecommendContentList() {
        return recommendContentList;
    }

    public void setRecommendContentList(List<RecommendContent> recommendContentList) {
        this.recommendContentList = recommendContentList;
    }
}
