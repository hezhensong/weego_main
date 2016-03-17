package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendContent {

    @JsonProperty("recommend_content_id")
    private String recommendContentId;

    @JsonProperty("recommend_content_url")
    private String recommedContentUrl;

    @JsonProperty("recommend_content_title")
    private String recommendContentTitle;

    @JsonProperty("recommend_content_first")
    private String recommendContentFirst;

    @JsonProperty("recommend_content_second")
    private String recommendContentSecond;

    @JsonProperty("recommend_content_desc")
    private String recommendContentDesc;

    @JsonProperty("cover_image")
    private String coverImage;

    public String getRecommendContentId() {
        return recommendContentId;
    }

    public void setRecommendContentId(String recommendContentId) {
        this.recommendContentId = recommendContentId;
    }

    public String getRecommedContentUrl() {
        return recommedContentUrl;
    }

    public void setRecommedContentUrl(String recommedContentUrl) {
        this.recommedContentUrl = recommedContentUrl;
    }

    public String getRecommendContentTitle() {
        return recommendContentTitle;
    }

    public void setRecommendContentTitle(String recommendContentTitle) {
        this.recommendContentTitle = recommendContentTitle;
    }

    public String getRecommendContentFirst() {
        return recommendContentFirst;
    }

    public void setRecommendContentFirst(String recommendContentFirst) {
        this.recommendContentFirst = recommendContentFirst;
    }

    public String getRecommendContentSecond() {
        return recommendContentSecond;
    }

    public void setRecommendContentSecond(String recommendContentSecond) {
        this.recommendContentSecond = recommendContentSecond;
    }

    public String getRecommendContentDesc() {
        return recommendContentDesc;
    }

    public void setRecommendContentDesc(String recommendContentDesc) {
        this.recommendContentDesc = recommendContentDesc;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
