package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.Id;

import java.util.List;

public class Pgc {

    @Id
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("cover_image")
    private String coverImage;

    @JsonProperty("person")
    private String person;

    @JsonProperty("poi_list")
    private List<PgcPoi> poiList;

    @JsonProperty("introduction")
    private String introduction;

    @JsonProperty("city_id")
    private String cityId;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("original")
    private PgcOriginal original;

    @JsonProperty("tag")
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public List<PgcPoi> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<PgcPoi> poiList) {
        this.poiList = poiList;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public PgcOriginal getOriginal() {
        return original;
    }

    public void setOriginal(PgcOriginal original) {
        this.original = original;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
