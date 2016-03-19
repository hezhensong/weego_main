package com.weego.main.service;

import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;

public interface BasePOIService {
	POIListDto getPOIsByCityId(String cityId, Integer type, String labelId);
	
	POIDetailDto getPOIDetailById(String id, Integer type);
}
