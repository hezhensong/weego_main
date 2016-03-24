package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class PolicyMap {

    @Id
    private ObjectId id;

    @JsonProperty("policy_id")
    private ObjectId policyId;

    @JsonProperty("content_id")
    private ObjectId contentId;

    @JsonProperty("first_title")
    private String firstTitle;

    @JsonProperty("second_title")
    private String secondTitle;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("last_modify_time")
    private Date lastModifyTime;

    @JsonProperty("last_modify_person")
    private String last_modify_person;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getPolicyId() {
        return policyId;
    }

    public void setPolicyId(ObjectId policyId) {
        this.policyId = policyId;
    }

    public ObjectId getContentId() {
        return contentId;
    }

    public void setContentId(ObjectId contentId) {
        this.contentId = contentId;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLast_modify_person() {
        return last_modify_person;
    }

    public void setLast_modify_person(String last_modify_person) {
        this.last_modify_person = last_modify_person;
    }
}
