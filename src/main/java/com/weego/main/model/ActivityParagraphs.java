package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityParagraphs {

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("image_title")
    private String imageTitle;

    @JsonProperty("detail_up")
    private String detailUp;

    @JsonProperty("detail_down")
    private String detailDown;

    @JsonProperty("image_brief")
    private String imageBrief;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getDetailUp() {
        return detailUp;
    }

    public void setDetailUp(String detailUp) {
        this.detailUp = detailUp;
    }

    public String getDetailDown() {
        return detailDown;
    }

    public void setDetailDown(String detailDown) {
        this.detailDown = detailDown;
    }

    public String getImageBrief() {
        return imageBrief;
    }

    public void setImageBrief(String imageBrief) {
        this.imageBrief = imageBrief;
    }

}
