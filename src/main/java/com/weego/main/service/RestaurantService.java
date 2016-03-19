package com.weego.main.service;

import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;

public interface RestaurantService {
	POIListDto getRestaurantsByCityId(String cityId, String labelId);
	
	POIDetailDto getRestaurantById(String id);
}
