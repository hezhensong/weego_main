package com.weego.main.dto;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-19.
 */
public class RecommendCardDto extends BaseDto {
    private List<BaseCardDto> data;

    public List<BaseCardDto> getData() {
        return data;
    }

    public void setData(List<BaseCardDto> data) {
        this.data = data;
    }
}
