package com.weego.main.dto;

import java.util.List;

public class POICommentsDto extends BaseDto {
	private List<POIDetailCommentsDto> data;

	public List<POIDetailCommentsDto> getData() {
		return data;
	}

	public void setData(List<POIDetailCommentsDto> data) {
		this.data = data;
	}

}
