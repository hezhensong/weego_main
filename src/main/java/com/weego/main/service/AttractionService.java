package com.weego.main.service;

import com.weego.main.dto.POIListDto;

public interface AttractionService {
	POIListDto getAttractionsByCityId(String cityId);
}
