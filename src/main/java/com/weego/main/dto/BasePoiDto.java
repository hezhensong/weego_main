package com.weego.main.dto;

/**
 * Created by liuniandxx on 16-3-21.
 */
public class BasePoiDto {
    private String id;

    private String type;

    private String poiDes;

    private String poiImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoiDes() {
        return poiDes;
    }

    public void setPoiDes(String poiDes) {
        this.poiDes = poiDes;
    }

    public String getPoiImage() {
        return poiImage;
    }

    public void setPoiImage(String poiImage) {
        this.poiImage = poiImage;
    }
}
