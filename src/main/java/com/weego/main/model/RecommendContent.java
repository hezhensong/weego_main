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
}
