package com.weego.main.dto;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendHistoryDto extends BaseDto {
    public List<RecommendDataDto> data;

    public List<RecommendDataDto> getData() {
        return data;
    }

    public void setData(List<RecommendDataDto> data) {
        this.data = data;
    }
}
