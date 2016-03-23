package com.weego.main.dto;

import java.util.List;

public class ActivityDetailDto {

    private String id;

    private String image;

    private String type;

    private String title;

    private String detailAddress;

    private String actTime;

    private String longitude;

    private String latidute;

    private String actUrl;

    private String orderUrl;

    private String description;

    private List<ActivityParagraphsDto> paragraphs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatidute() {
        return latidute;
    }

    public void setLatidute(String latidute) {
        this.latidute = latidute;
    }

    public String getActUrl() {
        return actUrl;
    }

    public void setActUrl(String actUrl) {
        this.actUrl = actUrl;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ActivityParagraphsDto> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ActivityParagraphsDto> paragraphs) {
        this.paragraphs = paragraphs;
    }

}
