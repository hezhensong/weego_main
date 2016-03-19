package com.weego.main.service;

import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;

public interface ShoppingService {
	POIListDto getShoppingsByCityId(String cityId, String labelId);

	POIDetailDto getShoppingById(String id);
}
