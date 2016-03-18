package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.AttractionDao;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.model.Attraction;
import com.weego.main.service.AttractionService;

@Service("attractionService")
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	AttractionDao attractionDao;

	@Override
	public POIListDto getAttractionsByCityId(String cityId) {
		POIListDto poiListDto = new POIListDto();
		Map<String, Object> data = new HashMap<String, Object>();
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Attraction> attractions = attractionDao
				.getAttractionsByCityId(cityId);
		for (Attraction attraction : attractions) {
			POIBaseDto poiBaseDto = new POIBaseDto();
			poiBaseDto.setCardId(attraction.getId());
			poiBaseDto.setBrief(attraction.getBriefIntroduce());
			poiBaseDto.setCoverImage(attraction.getCoverImage());
			poiBaseDto.setTag(attraction.getMasterTag().getTag());
			poiBaseDto.setTitle(attraction.getName());
			poiBaseDtos.add(poiBaseDto);
		}
		data.put("baseInfo", poiBaseDtos);
		poiListDto.setData(data);
		return poiListDto;
	}

}
