package com.weego.main.service;

import java.util.List;

import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.SearchNearByBaseDto;

public interface AttractionService {
	List<POIBaseDto> getAttractionsByCityId(String cityId, String labelId, Integer page, Integer count);
	
	POIDetailSumDto getAttractionById(String id, String coordination);
	
	List<POISepcialBaseDto> getAttractionSpotsById(String id);
	
	List<POIDetailCommentsDto> getAttractionCommentsById(String id);
	
	SearchNearByBaseDto getAttractionsByCityIdAndCoordination(String  cityId, String coordination, String sort,
			Double range, Integer price, String special);
}
