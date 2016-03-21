package com.weego.main.dto;

import java.util.Map;

/**
 * Created by liuniandxx on 16-3-21.
 */
public class PersonDto extends BaseDto {
    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
