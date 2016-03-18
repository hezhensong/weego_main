package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.Id;

public class CityLabel {

    @Id
    private String id;

    @JsonProperty("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
