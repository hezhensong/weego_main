package com.weego.main.service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.SearchNearByDto;

public interface RestaurantService {
	POIListDto getRestaurantsByCityId(String cityId, String labelId, Integer page, Integer count);
	
	POIDetailDto getRestaurantById(String id, String coordination);
	
	POISpecialDto getRestaurantDishesById(String id);
	
	POICommentsDto getRestaurantCommentsById(String id);
	
	SearchNearByDto getRestaurantsByCityIdAndCoordination(String cityId, String coordination, String sort,
			Double range, Integer price, String special);
}
