package com.weego.main.service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.SearchNearByDto;

public interface AttractionService {
	POIListDto getAttractionsByCityId(String cityId, String labelId, Integer page, Integer count);
	
	POIDetailDto getAttractionById(String id, String coordination);
	
	POISpecialDto getAttractionSpotsById(String id);
	
	POICommentsDto getAttractionCommentsById(String id);
	
	SearchNearByDto getAttractionsByCityIdAndCoordination(String  cityId, String coordination, String sort,
			Double range, Integer price, String special);
}
