package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.Id;

public class WeatherTranslation {

    @Id
    private String id;

    @JsonProperty("english")
    private String english;

    @JsonProperty("chinese")
    private String chinese;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
