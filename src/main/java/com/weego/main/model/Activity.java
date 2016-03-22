package com.weego.main.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activity {

    @Id
    private ObjectId Id;

    @JsonProperty("city_id")
    private ObjectId cityId;

    @JsonProperty("cover_image")
    private String image;

    @JsonProperty("type")
    private String type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("address")
    private String address;

    @JsonProperty("detail_address")
    private String detailAddress;

    @JsonProperty("start_time")
    private Date openTime;

    @JsonProperty("end_time")
    private Date closeTime;

    @JsonProperty("act_time")
    private String actTime;

    @JsonProperty("coordination")
    private String coordination;

    @JsonProperty("act_url")
    private String actUrl;

    @JsonProperty("order_url")
    private String orderUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("paragraphs")
    private List<ActivityParagraphs> paragraphs;

    @JsonProperty("last_modified_time")
    private Date lastModifiedTime;

    @JsonProperty("last_modified_person")
    private String lastModifiedPerson;

    public ObjectId getId() {
        return Id;
    }

    public void setId(ObjectId id) {
        Id = id;
    }

    public ObjectId getCityId() {
        return cityId;
    }

    public void setCityId(ObjectId cityId) {
        this.cityId = cityId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    public String getCoordination() {
        return coordination;
    }

    public void setCoordination(String coordination) {
        this.coordination = coordination;
    }

    public String getActUrl() {
        return actUrl;
    }

    public void setActUrl(String actUrl) {
        this.actUrl = actUrl;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getLastModifiedPerson() {
        return lastModifiedPerson;
    }

    public void setLastModifiedPerson(String lastModifiedPerson) {
        this.lastModifiedPerson = lastModifiedPerson;
    }

    public List<ActivityParagraphs> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ActivityParagraphs> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
