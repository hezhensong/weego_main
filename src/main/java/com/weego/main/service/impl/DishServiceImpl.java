package com.weego.main.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.DishDao;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.model.Dish;
import com.weego.main.service.DishService;

@Service("dishService")
public class DishServiceImpl implements DishService {
	private static Logger logger = LogManager.getLogger(DishServiceImpl.class);
	
	@Autowired
	DishDao dishDao;

	@Override
	public POISepcialBaseDto getDishById(String id) {
		POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
		try {
			Dish dish =  dishDao.getDishById(id);
			if(dish != null) {
				poiSepcialBaseDto.setSpecialId(dish.getId());
				poiSepcialBaseDto.setCoverImage(dish.getCoverImage());
				poiSepcialBaseDto.setTitle(dish.getTitle());
				poiSepcialBaseDto.setDesc(dish.getDesc());
				poiSepcialBaseDto.setTag(dish.getTag());
			}
		} catch (Exception e) {
			logger.info("餐厅的菜品详情接口出错!");
			e.printStackTrace();
		}
		return poiSepcialBaseDto;
	}
	
}
