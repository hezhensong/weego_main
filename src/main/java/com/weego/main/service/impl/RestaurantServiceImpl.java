package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.RestaurantDao;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.model.Restaurant;
import com.weego.main.service.RestaurantService;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	RestaurantDao restaurantDao;

	@Override
	public POIListDto getRestaurantsByCityId(String cityId) {
		POIListDto poiListDto = new POIListDto();
		Map<String, Object> data = new HashMap<String, Object>();
		
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Restaurant> restaurants =  restaurantDao.getRestaurantsByCityId(cityId);
		for(Restaurant restaurant : restaurants) {
			POIBaseDto poiBaseDto = new POIBaseDto();
			poiBaseDto.setCardId(restaurant.getId());
			poiBaseDto.setBrief(restaurant.getBriefIntroduce());
			poiBaseDto.setCoverImage(restaurant.getCoverImage());
			poiBaseDto.setTag(restaurant.getMasterTag().getTag());
			poiBaseDto.setTitle(restaurant.getName());
			poiBaseDtos.add(poiBaseDto);
		}
		
		data.put("baseInfo", poiBaseDtos);
		poiListDto.setData(data);
		return poiListDto;
	}
	
}
