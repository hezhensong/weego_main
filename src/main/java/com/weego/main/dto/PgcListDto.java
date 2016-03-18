package com.weego.main.dto;

import java.util.List;

public class PgcListDto extends BaseDto {


    private List<PgcListContinentDto> data;

    public List<PgcListContinentDto> getData() {

        return data;
    }

    public void setData(List<PgcListContinentDto> data) {
        this.data = data;
    }

}
