package com.weego.main.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.POITranslationBaseDto;
import com.weego.main.dto.POITranslationDto;
import com.weego.main.service.AttractionService;
import com.weego.main.service.BasePOIService;
import com.weego.main.service.BrandService;
import com.weego.main.service.DishService;
import com.weego.main.service.RestaurantService;
import com.weego.main.service.ShoppingService;
import com.weego.main.service.SpotService;
import com.weego.main.util.BaiDuTranslateUtil;

@Service("basePOIService")
public class BasePOIServiceImpl implements BasePOIService {
	
	private static Logger logger = LogManager.getLogger(BasePOIServiceImpl.class);

	@Autowired
	AttractionService attractionService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	ShoppingService shoppingService;
	@Autowired
	SpotService spotService;
	@Autowired
	DishService dishService;
	@Autowired
	BrandService brandService;
	
	@Override
	public POIListDto getPOIsByCityId(String cityId, Integer type, String labelId, 
			Integer page, Integer count) {
		if (type == 0) {
			return attractionService.getAttractionsByCityId(cityId, labelId, page, count);
		} else if (type == 1) {
			return restaurantService.getRestaurantsByCityId(cityId, labelId, page, count);
		} else if (type == 2) {
			return shoppingService.getShoppingsByCityId(cityId, labelId, page, count);
		} else {
			logger.info("type 参数值有误");
			return null;
		}
	}

	@Override
	public POIDetailDto getPOIDetailById(String id, Integer type, String coordination) {
		if (type == 0) {
			return attractionService.getAttractionById(id, coordination);
		} else if (type == 1) {
			return restaurantService.getRestaurantById(id, coordination);
		} else if (type == 2) {
			return shoppingService.getShoppingById(id, coordination);
		} else {
			logger.info("type 参数值有误");
			return null;
		}
	}

	@Override
	public POISpecialDto getPOISpecialById(String id, Integer type) {
		if (type == 0) {
			return attractionService.getAttractionSpotsById(id);
		} else if (type == 1) {
			return restaurantService.getRestaurantDishesById(id);
		} else if (type == 2) {
			return shoppingService.getShoppingBrandsById(id);
		} else {
			logger.info("type 参数值有误");
			return null;
		}
	}

	@Override
	public POISpecialDetailDto getPOISpecialDetailById(String specialId, Integer type) {
		if (type == 0) {
			return spotService.getSpotById(specialId);
		} else if (type == 1) {
			return dishService.getDishById(specialId);
		} else if (type == 2) {
			return brandService.getBrandById(specialId);
		} else {
			logger.info("type 参数值有误");
			return null;
		}
	}

	@Override
	public POICommentsDto getPOICommentsById(String id, Integer type) {
		if (type == 0) {
			return attractionService.getAttractionCommentsById(id);
		} else if (type == 1) {
			return restaurantService.getRestaurantCommentsById(id);
		} else if (type == 2) {
			return shoppingService.getShoppingCommentsById(id);
		} else {
			logger.info("type 参数值有误");
			return null;
		}
	}

	@Override
	public POITranslationDto getPOITranslation(String content, String from, String to) {
		POITranslationDto poiTranslationDto = new POITranslationDto();
		POITranslationBaseDto poiTranslationBaseDto = new POITranslationBaseDto();
		try {
			String translation = BaiDuTranslateUtil.translate(content, from, to);
			poiTranslationBaseDto.setOrigin(content);
			poiTranslationBaseDto.setTranslation(translation);
		} catch (Exception e) {
			logger.info("百度翻译出错了!");
			e.printStackTrace();
		}
		poiTranslationDto.setData(poiTranslationBaseDto);
		return poiTranslationDto;
	}
	
	
	
	
}
