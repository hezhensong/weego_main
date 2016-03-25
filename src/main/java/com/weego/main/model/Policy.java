package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class Policy {
    @Id
    private ObjectId id;

    @JsonProperty("city_id")
    private ObjectId cityId;

    @JsonProperty("start_time")
    private Date startTime;

    @JsonProperty("end_time")
    private Date endTime;

    @JsonProperty("radius")
    private Integer radius;

    @JsonProperty("type")
    private PolicyType policyType;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("coordination")
    private String coordination;

    @JsonProperty("last_modify_time")
    private Date lastModifyTime;

    @JsonProperty("last_modify_person")
    private String lastModifyPerson;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getCityId() {
        return cityId;
    }

    public void setCityId(ObjectId cityId) {
        this.cityId = cityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

    public String getCoordination() {
        return coordination;
    }

    public void setCoordination(String coordination) {
        this.coordination = coordination;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyPerson() {
        return lastModifyPerson;
    }

    public void setLastModifyPerson(String lastModifyPerson) {
        this.lastModifyPerson = lastModifyPerson;
    }
}

