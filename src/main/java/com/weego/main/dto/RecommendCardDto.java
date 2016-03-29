package com.weego.main.dto;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-29.
 */
public class RecommendCardDto {
    private String cityId;
    private List<CardContentDto> content;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<CardContentDto> getContent() {
        return content;
    }

    public void setContent(List<CardContentDto> content) {
        this.content = content;
    }
}
