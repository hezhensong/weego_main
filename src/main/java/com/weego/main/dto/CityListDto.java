package com.weego.main.dto;

import java.util.List;

public class CityListDto extends BaseDto {

    private List<CityListContinentDto> data;

    public List<CityListContinentDto> getData() {
        return data;
    }

    public void setData(List<CityListContinentDto> data) {
        this.data = data;
    }
}
