package com.weego.main.dto;

import java.io.Serializable;

public class CityActivityDto implements Serializable {
    private static final long serialVersionUID = -3245523620591902191L;

    private String title;
    private String coverImage;

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
}
