package com.weego.main.service;

import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;

public interface AttractionService {
	POIListDto getAttractionsByCityId(String cityId, String labelId);
	
	POIDetailDto getAttractionById(String id);
}
