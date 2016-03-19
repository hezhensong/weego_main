package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Person {

    @JsonProperty("_id")
    private String personId;

    @JsonProperty("username")
    private String userName;

    private String person;

    @JsonProperty("simple_introduce")
    private List<PeopleSimpleIntroduce> simpleIntroduce;

    @JsonProperty("head_image")
    private String headImage;

    @JsonProperty("job_desc")
    private String jobDesc;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }


    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public List<PeopleSimpleIntroduce> getSimpleIntroduce() {
        return simpleIntroduce;
    }

    public void setSimpleIntroduce(List<PeopleSimpleIntroduce> simpleIntroduce) {
        this.simpleIntroduce = simpleIntroduce;
    }
}
