package com.weego.main.model;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Label {
	@Id
	private String id;
	private String name;
	@JsonProperty("name_en")
	private String nameEn;
	private Integer type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
