package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.ShoppingDao;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.model.Shopping;
import com.weego.main.service.ShoppingService;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

	@Autowired
	ShoppingDao shoppingDao;
	
	@Override
	public POIListDto getShoppingsByCityId(String cityId) {
		POIListDto poiListDto = new POIListDto();
		Map<String, Object> data = new HashMap<String, Object>();
		
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Shopping> shoppings = shoppingDao.getShoppingsByCityId(cityId);
		for(Shopping shopping : shoppings) {
			POIBaseDto poiBaseDto = new POIBaseDto();
			poiBaseDto.setCardId(shopping.getId());
			poiBaseDto.setBrief(shopping.getBriefIntroduce());
			poiBaseDto.setCoverImage(shopping.getCoverImage());
			poiBaseDto.setTag(shopping.getMasterTag().getTag());
			poiBaseDto.setTitle(shopping.getName());
			poiBaseDtos.add(poiBaseDto);
		}
		
		data.put("baseInfo", poiBaseDtos);
		poiListDto.setData(data);
		return poiListDto;
	}

}
