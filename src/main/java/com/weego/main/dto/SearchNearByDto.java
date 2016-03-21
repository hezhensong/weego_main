package com.weego.main.dto;

import java.util.List;

public class SearchNearByDto extends BaseDto {
	private List<SearchNearByBaseDto> data;

	public List<SearchNearByBaseDto> getData() {
		return data;
	}

	public void setData(List<SearchNearByBaseDto> data) {
		this.data = data;
	}

}
