package com.weego.main.dto;

import java.util.List;

public class POIListDto extends BaseDto {
	private List<POIBaseDto> data;

	public List<POIBaseDto> getData() {
		return data;
	}

	public void setData(List<POIBaseDto> data) {
		this.data = data;
	}

}
