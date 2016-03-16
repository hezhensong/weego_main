package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityActivityParagraphs {

	private String imageID;

	private String imageTitle;

	@JsonProperty("desc")
	private String imageDesc;

	@JsonProperty("imageDesc")
	private String imageBrief;

	public String getImageID() {
		return imageID;
	}

	public void setImageID(String imageID) {
		this.imageID = imageID;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getImageDesc() {
		return imageDesc;
	}

	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}

	public String getImageBrief() {
		return imageBrief;
	}

	public void setImageBrief(String imageBrief) {
		this.imageBrief = imageBrief;
	}

}
