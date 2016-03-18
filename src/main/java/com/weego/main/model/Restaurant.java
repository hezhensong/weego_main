package com.weego.main.model;

import java.util.List;

public class Restaurant extends BasePOI {
	private List<RestaurantDish> restaurantDishs;

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
