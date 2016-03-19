package com.weego.main.model;

import org.mongojack.Id;

public class BasePOILabel {
	@Id
	private String id;
	private String label;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
