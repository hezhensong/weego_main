package com.weego.main.dto;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-21.
 */
public class PgcDetailDto {

    private String coverImage;

    private String title;

    private PgcPeopleDto pgcPeople;

    private String introduction;

    List<BasePoiDto> pgcPoi;

    public List<BasePoiDto> getPgcPoi() {
        return pgcPoi;
    }

    public void setPgcPoi(List<BasePoiDto> pgcPoi) {
        this.pgcPoi = pgcPoi;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PgcPeopleDto getPgcPeople() {
        return pgcPeople;
    }

    public void setPgcPeople(PgcPeopleDto pgcPeople) {
        this.pgcPeople = pgcPeople;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
