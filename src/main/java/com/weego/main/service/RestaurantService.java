package com.weego.main.service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;

public interface RestaurantService {
	POIListDto getRestaurantsByCityId(String cityId, String labelId);
	
	POIDetailDto getRestaurantById(String id);
	
	POISpecialDto getRestaurantDishesById(String id);
	
	POISpecialDetailDto getRestaurantDishDetail(String specialId);
	
	POICommentsDto getRestaurantCommentsById(String id);
}
