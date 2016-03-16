package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class DynamicContent {

    @JsonProperty("recommend_start_date")
    private Date recommendStartDate;

    @JsonProperty("recommend_end_date")
    private Date recommendEndDate;

    @JsonProperty("recommend_start_time")
    private String recommendStartTime;

    @JsonProperty("recommend_end_time")
    private String recommendEndTime;

    @JsonProperty("recommend_poi_lon")
    private Double recommendPoiLon;

    @JsonProperty("recommend_poi_lat")
    private Double recommendPoiLat;

    @JsonProperty("recommend_content")
    private RecommendContent recommendContent;

    public Date getRecommendStartDate() {
        return recommendStartDate;
    }

    public void setRecommendStartDate(Date recommendStartDate) {
        this.recommendStartDate = recommendStartDate;
    }

    public Date getRecommendEndDate() {
        return recommendEndDate;
    }

    public void setRecommendEndDate(Date recommendEndDate) {
        this.recommendEndDate = recommendEndDate;
    }

    public String getRecommendStartTime() {
        return recommendStartTime;
    }

    public void setRecommendStartTime(String recommendStartTime) {
        this.recommendStartTime = recommendStartTime;
    }

    public String getRecommendEndTime() {
        return recommendEndTime;
    }

    public void setRecommendEndTime(String recommendEndTime) {
        this.recommendEndTime = recommendEndTime;
    }

    public Double getRecommendPoiLon() {
        return recommendPoiLon;
    }

    public void setRecommendPoiLon(Double recommendPoiLon) {
        this.recommendPoiLon = recommendPoiLon;
    }

    public Double getRecommendPoiLat() {
        return recommendPoiLat;
    }

    public void setRecommendPoiLat(Double recommendPoiLat) {
        this.recommendPoiLat = recommendPoiLat;
    }

    public RecommendContent getRecommendContent() {
        return recommendContent;
    }

    public void setRecommendContent(RecommendContent recommendContent) {
        this.recommendContent = recommendContent;
    }
}
