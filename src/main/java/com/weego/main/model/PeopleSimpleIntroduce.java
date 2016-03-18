package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PeopleSimpleIntroduce {

    @JsonProperty("image")
    private String image;

    @JsonProperty("desc")
    private String desc;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("title")
    private String title;


}
