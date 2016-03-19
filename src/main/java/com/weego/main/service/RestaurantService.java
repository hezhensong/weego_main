package com.weego.main.service;

import com.weego.main.dto.POIListDto;

public interface RestaurantService {
	POIListDto getRestaurantsByCityId(String cityId);
}
