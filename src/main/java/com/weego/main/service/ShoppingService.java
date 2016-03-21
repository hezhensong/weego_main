package com.weego.main.service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.SearchNearByDto;

public interface ShoppingService {
	POIListDto getShoppingsByCityId(String cityId, String labelId, Integer page, Integer count);

	POIDetailDto getShoppingById(String id);
	
	POISpecialDto getShoppingBrandsById(String id);
	
	POICommentsDto getShoppingCommentsById(String id);
	
	SearchNearByDto getShoppingsByCityIdAndCoordination(String cityId, String coordination, String sort);
}
