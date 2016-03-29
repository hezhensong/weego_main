package com.weego.main.dto;

/**
 * Created by liuniandxx on 16-3-19.
 */
public class CardContentDto {
    private String id;

    private Integer type;

    private String firstTitle;

    private String secondTitle;

    private String title;

    private String coverImage;

    private String desc;

    private String lon;

    private String lat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public CardContentDto() {}

    public CardContentDto(String id, int type, String firstTitle, String secondTitle,
                          String coverImage, String title, String desc, String lon, String lat) {
        this.id = id;
        this.type = type;
        this.firstTitle = firstTitle;
        this.secondTitle = secondTitle;
        this.coverImage = coverImage;
        this.title = title;
        this.desc = desc;
        this.lon = lon;
        this.lat = lat;
    }


}
