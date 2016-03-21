package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.Id;

import java.util.List;

public class Pgc {

    @JsonProperty("_id")
    private String pgcId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("cover_image")
    private String coverImage;

    private String person;

    @JsonProperty("poi_list")
    private List<PgcPoi> poiList;

    private String introduction;

    @JsonProperty("city_id")
    private String cityId;

    public String getPgcId() {
        return pgcId;
    }

    public void setPgcId(String pgcId) {
        this.pgcId = pgcId;
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
}
