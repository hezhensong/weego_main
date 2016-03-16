package com.weego.main.model;

import java.util.List;
import java.util.Map;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityActivity {

    @Id
    private String Id;

    @JsonProperty("cover_image")
    private String image;

    @JsonProperty("type")
    private String type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("detail_address")
    private String deaddress;

    @JsonProperty("start_time")
    private String openTime;

    @JsonProperty("end_time")
    private String closeTime;

    @JsonProperty("act_time")
    private String zcttime;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("latitude")
    private String latidute;

    @JsonProperty("act_url")
    private String acturl;

    @JsonProperty("order_url")
    private String orderurl;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("paragraphs")
    private Map<String, List<CityActivityParagraphs>> paragraphs;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getDeaddress() {
        return deaddress;
    }

    public void setDeaddress(String deaddress) {
        this.deaddress = deaddress;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getZcttime() {
        return zcttime;
    }

    public void setZcttime(String zcttime) {
        this.zcttime = zcttime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatidute() {
        return latidute;
    }

    public void setLatidute(String latidute) {
        this.latidute = latidute;
    }

    public String getActurl() {
        return acturl;
    }

    public void setActurl(String acturl) {
        this.acturl = acturl;
    }

    public String getOrderurl() {
        return orderurl;
    }

    public void setOrderurl(String orderurl) {
        this.orderurl = orderurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, List<CityActivityParagraphs>> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(Map<String, List<CityActivityParagraphs>> paragraphs) {
        this.paragraphs = paragraphs;
    }

}
