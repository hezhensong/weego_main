package com.weego.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.DishDao;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.model.Dish;
import com.weego.main.service.DishService;

@Service("dishService")
public class DishServiceImpl implements DishService {
	
	@Autowired
	DishDao dishDao;

	@Override
	public POISpecialDetailDto getDishById(String id) {
		POISpecialDetailDto poiSpecialDetailDto = new POISpecialDetailDto();
		POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
		Dish dish =  dishDao.getDishById(id);
		if(dish != null) {
			poiSepcialBaseDto.setSpecialId(dish.getId());
			poiSepcialBaseDto.setCoverImage(dish.getCoverImage());
			poiSepcialBaseDto.setTitle(dish.getTitle());
			poiSepcialBaseDto.setDesc(dish.getDesc());
			poiSepcialBaseDto.setTag(dish.getTag());
			poiSpecialDetailDto.setData(poiSepcialBaseDto);
		}
		return poiSpecialDetailDto;
	}
	
}
