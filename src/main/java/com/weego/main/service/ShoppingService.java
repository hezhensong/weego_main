package com.weego.main.service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;

public interface ShoppingService {
	POIListDto getShoppingsByCityId(String cityId, String labelId);

	POIDetailDto getShoppingById(String id);
	
	POISpecialDto getShoppingBrandsById(String id);
	
	POISpecialDetailDto getShoppingBrandDetail(String specialId);
	
	POICommentsDto getShoppingCommentsById(String id);
}
