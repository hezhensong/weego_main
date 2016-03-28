package com.weego.main.service;

import java.util.List;

import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.SearchNearByBaseDto;

public interface RestaurantService {
	List<POIBaseDto> getRestaurantsByCityId(String cityId, String labelId, Integer page, Integer count);
	
	POIDetailSumDto getRestaurantById(String id, String coordination);
	
	List<POISepcialBaseDto> getRestaurantDishesById(String id);
	
	List<POIDetailCommentsDto> getRestaurantCommentsById(String id);
	
	SearchNearByBaseDto getRestaurantsByCityIdAndCoordination(String cityId, String coordination, String sort,
			Double range, Integer price, String special);
}
