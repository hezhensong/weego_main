package com.weego.main.dto;

import org.bson.types.ObjectId;

import java.util.Map;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendHistoryDto extends BaseDto {
    public Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
