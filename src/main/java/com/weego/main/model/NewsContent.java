package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-24.
 */
public class NewsContent {

    @JsonProperty("title")
    private String title;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("source")
    private String source;

    @JsonProperty("image")
    private String image;

    @JsonProperty("imageDesc")
    private String imageDesc;

    @JsonProperty("text")
    private String text;

    @JsonProperty("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
