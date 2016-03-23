package com.weego.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.SpotDao;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.model.Dish;
import com.weego.main.model.Spot;
import com.weego.main.service.SpotService;

@Service("spotService")
public class SpotServiceImpl implements SpotService {
	
	@Autowired
	SpotDao spotDao;

	@Override
	public POISpecialDetailDto getSpotById(String id) {
		POISpecialDetailDto poiSpecialDetailDto = new POISpecialDetailDto();
		POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
		Spot spot =  spotDao.getSpotById(id);
		if(spot != null) {
			poiSepcialBaseDto.setSpecialId(spot.getId());
			poiSepcialBaseDto.setCoverImage(spot.getCoverImage());
			poiSepcialBaseDto.setTitle(spot.getTitle());
			poiSepcialBaseDto.setDesc(spot.getDesc());
			poiSepcialBaseDto.setTag(spot.getTag());
			poiSpecialDetailDto.setData(poiSepcialBaseDto);
		}
		return poiSpecialDetailDto;
	}
	
} 
