package com.weego.main.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.Id;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendDynamic {
    @Id
    private String id;

    @JsonProperty("city_id")
    private String cityId;

    @JsonProperty("content")
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

    public DynamicContent getContent() {
        return content;
    }

    public void setContent(DynamicContent content) {
        this.content = content;
    }
}
