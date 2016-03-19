package com.weego.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restaurant extends BasePOI {
	@JsonProperty("dish")
	private List<RestaurantDish> restaurantDishs;

	@JsonProperty("facilities")
	private RestaurantFacilities restaurantFacilities;

	public List<RestaurantDish> getRestaurantDishs() {
		return restaurantDishs;
	}

	public void setRestaurantDishs(List<RestaurantDish> restaurantDishs) {
		this.restaurantDishs = restaurantDishs;
	}

	public RestaurantFacilities getRestaurantFacilities() {
		return restaurantFacilities;
	}

	public void setRestaurantFacilities(
			RestaurantFacilities restaurantFacilities) {
		this.restaurantFacilities = restaurantFacilities;
	}

}
