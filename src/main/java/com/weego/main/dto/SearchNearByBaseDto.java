package com.weego.main.dto;

import java.util.List;

public class SearchNearByBaseDto {
	private List<SearchNearByTagDto> tagList;
	private List<SearchNearByListDto> searches;

	public List<SearchNearByTagDto> getTagList() {
		return tagList;
	}

	public void setTagList(List<SearchNearByTagDto> tagList) {
		this.tagList = tagList;
	}

	public List<SearchNearByListDto> getSearches() {
		return searches;
	}

	public void setSearches(List<SearchNearByListDto> searches) {
		this.searches = searches;
	}

}
