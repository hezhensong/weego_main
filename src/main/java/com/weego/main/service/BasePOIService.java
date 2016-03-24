package com.weego.main.service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.POITranslationDto;

public interface BasePOIService {
	POIListDto getPOIsByCityId(String cityId, Integer type, String labelId, 
							Integer page, Integer count);
	
	POIDetailDto getPOIDetailById(String id, Integer type, String coordination);
	
	POISpecialDto getPOISpecialById(String id, Integer type);
	
	POISpecialDetailDto getPOISpecialDetailById(String specialId, Integer type);
	
	POICommentsDto getPOICommentsById(String id, Integer type);
	
	POITranslationDto getPOITranslation(String content, String from, String to);
}
