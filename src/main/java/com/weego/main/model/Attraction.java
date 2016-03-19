package com.weego.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attraction extends BasePOI {
	@JsonProperty("spot")
	private List<AttractionSpot> attractionSpots;

	public List<AttractionSpot> getAttractionSpots() {
		return attractionSpots;
	}

	public void setAttractionSpots(List<AttractionSpot> attractionSpots) {
		this.attractionSpots = attractionSpots;
	}

}
