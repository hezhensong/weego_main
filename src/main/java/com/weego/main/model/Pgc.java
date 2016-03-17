package com.weego.main.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.Id;
import java.util.List;
import java.util.Map;

public class Pgc {
	@Id
	private String _id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("cover_image")
	private String coverImage;

	@JsonProperty("person")
	private String person;

	@JsonProperty("poi_list")
	private List<String> poiList;

	@JsonProperty("head_image")
	private String headImage;

	@JsonProperty("introduction")
	private String introduction;

	@JsonProperty("city_id")
	private String cityId;

	public List<String> getPoiList() {
		return poiList;
	}

	public void setPoiList(List<String> poiList) {
		this.poiList = poiList;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

}
