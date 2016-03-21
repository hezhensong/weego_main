package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityArea {

    @JsonProperty("continent")
    private String continent;

    @JsonProperty("country")
    private String country;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
