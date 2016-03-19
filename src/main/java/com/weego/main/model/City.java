package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.List;
import java.util.Map;


public class City {

    @Id
    private ObjectId id;

    private String name;

    @JsonProperty("name_en")
    private String nameEn;

    @JsonProperty("name_py")
    private String namePy;

    @JsonProperty("cover_image")
    private String coverImage;

    @JsonProperty("label_list")
    private Map<String, List<CityLabel>> labelList;

    private Area area;

    @JsonProperty("is_show")
    private boolean isShow;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Map<String, List<CityLabel>> getLabelList() {
        return labelList;
    }

    public void setLabelList(Map<String, List<CityLabel>> labelList) {
        this.labelList = labelList;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
