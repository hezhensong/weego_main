package com.weego.main.dto;

import java.util.Map;

public class CityHomeDto extends BaseDto {

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
