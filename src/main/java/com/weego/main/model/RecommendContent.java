package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendContent {

    @JsonProperty("content_id")
    private String contentId;

    @JsonProperty("content_url")
    private String contentUrl;

    @JsonProperty("content_title")
    private String contentTitle;

    @JsonProperty("content_first")
    private String contentFirst;

    @JsonProperty("content_second")
    private String contentSecond;

    @JsonProperty("content_desc")
    private String contentDesc;

    @JsonProperty("cover_image")
    private String coverImage;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentFirst() {
        return contentFirst;
    }

    public void setContentFirst(String contentFirst) {
        this.contentFirst = contentFirst;
    }

    public String getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(String contentSecond) {
        this.contentSecond = contentSecond;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
