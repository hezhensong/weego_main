package com.weego.main.model;

import org.mongojack.Id;

public class BasePOIActivies {

	@Id
	private String id;

	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}