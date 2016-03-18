package com.weego.main.model;

import java.util.List;

public class Attraction extends BasePOI {
	private List<AttractionSpot> attractionSpots;

	public List<AttractionSpot> getAttractionSpots() {
		return attractionSpots;
	}

	public void setAttractionSpots(List<AttractionSpot> attractionSpots) {
		this.attractionSpots = attractionSpots;
	}

}
