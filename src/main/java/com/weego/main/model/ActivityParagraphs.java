package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityParagraphs {

	@JsonProperty("image_url")
	private String imageUrl;

	@JsonProperty("image_title")
	private String imageTitle;

<<<<<<< HEAD
	@JsonProperty("detail")
	private String detail;

	@JsonProperty("image_brief")
	private String imageBrief;
=======
    @JsonProperty("detail")
    private String detail;
>>>>>>> 97bbe94119c782e17edc7ba87973db24a415ad99

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getDetail() {
		return detail;
	}

<<<<<<< HEAD
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImageBrief() {
		return imageBrief;
	}

	public void setImageBrief(String imageBrief) {
		this.imageBrief = imageBrief;
	}
=======
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageBrief() {
        return imageBrief;
    }

    public void setImageBrief(String imageBrief) {
        this.imageBrief = imageBrief;
    }
>>>>>>> 97bbe94119c782e17edc7ba87973db24a415ad99

}
