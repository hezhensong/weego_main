package com.weego.main.service;

import java.util.List;

import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.POITranslationBaseDto;

public interface BasePOIService {
	List<POIBaseDto> getPOIsByCityId(String cityId, Integer type, String labelId, 
							Integer page, Integer count);
	
	POIDetailSumDto getPOIDetailById(String id, Integer type, String coordination);
	
	List<POISepcialBaseDto> getPOISpecialById(String id, Integer type);
	
	POISepcialBaseDto getPOISpecialDetailById(String specialId, Integer type);
	
	List<POIDetailCommentsDto> getPOICommentsById(String id, Integer type);
	
	POITranslationBaseDto getPOITranslation(String content, String from, String to);
	
//	ModelAndView getPOIDetail(String id, Integer type);
}
