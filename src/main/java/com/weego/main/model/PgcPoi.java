package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PgcPoi {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("paragraph_desc")
    private String paragraphDesc;

    @JsonProperty("poi_image")
    private String poiImage;

    @JsonProperty("paragraph_title")
    private String paragraphTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getPoiImage() {
        return poiImage;
    }

    public void setPoiImage(String poiImage) {
        this.poiImage = poiImage;
    }

    public String getParagraphDesc() {
        return paragraphDesc;
    }

    public void setParagraphDesc(String paragraphDesc) {
        this.paragraphDesc = paragraphDesc;
    }

    public String getParagraphTitle() {
        return paragraphTitle;
    }

    public void setParagraphTitle(String paragraphTitle) {
        this.paragraphTitle = paragraphTitle;
    }
}
