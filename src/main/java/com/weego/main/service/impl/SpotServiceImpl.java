package com.weego.main.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.SpotDao;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.model.Spot;
import com.weego.main.service.SpotService;

@Service("spotService")
public class SpotServiceImpl implements SpotService {
	private static Logger logger = LogManager.getLogger(SpotServiceImpl.class);
	
	@Autowired
	SpotDao spotDao;

	@Override
	public POISepcialBaseDto getSpotById(String id) {
		POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
		try {
			Spot spot =  spotDao.getSpotById(id);
			if(spot != null) {
				poiSepcialBaseDto.setSpecialId(spot.getId());
				poiSepcialBaseDto.setCoverImage(spot.getCoverImage());
				poiSepcialBaseDto.setTitle(spot.getTitle());
				poiSepcialBaseDto.setDesc(spot.getDesc());
				poiSepcialBaseDto.setTag(spot.getTag());
			}
		} catch (Exception e) {
			logger.info("景点的亮点详情接口出错!");
			e.printStackTrace();
		}
		return poiSepcialBaseDto;
	}
	
} 
