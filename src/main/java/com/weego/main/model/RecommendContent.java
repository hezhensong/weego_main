package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class RecommendContent {

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("content_oid")
    private String contentId;

    @JsonProperty("content_first")
    private String contentFirst;

    @JsonProperty("content_second")
    private String contentSecond;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
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
}
