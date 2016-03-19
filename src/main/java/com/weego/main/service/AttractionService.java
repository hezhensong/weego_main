package com.weego.main.service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;

public interface AttractionService {
	POIListDto getAttractionsByCityId(String cityId, String labelId);
	
	POIDetailDto getAttractionById(String id);
	
	POISpecialDto getAttractionSpotsById(String id);
	
	POISpecialDetailDto getAttractionSpotDetail(String specialId);
	
	POICommentsDto getAttractionCommentsById(String id);
}
