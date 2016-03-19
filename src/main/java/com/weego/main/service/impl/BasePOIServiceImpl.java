package com.weego.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.service.AttractionService;
import com.weego.main.service.BasePOIService;
import com.weego.main.service.RestaurantService;
import com.weego.main.service.ShoppingService;

@Service("basePOIService")
public class BasePOIServiceImpl implements BasePOIService {

	@Autowired
	AttractionService attractionService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	ShoppingService shoppingService;
	
	@Override
	public POIListDto getPOIsByCityId(String cityId, Integer type, String labelId) {
		if (type == 0) {
			return attractionService.getAttractionsByCityId(cityId, labelId);
		} else if (type == 1) {
			return restaurantService.getRestaurantsByCityId(cityId, labelId);
		} else if (type == 2) {
			return shoppingService.getShoppingsByCityId(cityId, labelId);
		} else {
			System.out.println("type 参数值有误");
			return null;
		}
	}

	@Override
	public POIDetailDto getPOIDetailById(String id, Integer type) {
		if (type == 0) {
			return attractionService.getAttractionById(id);
		} else if (type == 1) {
			return restaurantService.getRestaurantById(id);
		} else if (type == 2) {
			return shoppingService.getShoppingById(id);
		} else {
			System.out.println("type 参数值有误");
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
			System.out.println("type 参数值有误");
			return null;
		}
	}

	@Override
	public POISpecialDetailDto getPOISpecialDetailById(String specialId, Integer type) {
		if (type == 0) {
			return attractionService.getAttractionSpotDetail(specialId);
		} else if (type == 1) {
			return restaurantService.getRestaurantDishDetail(specialId);
		} else if (type == 2) {
			return shoppingService.getShoppingBrandDetail(specialId);
		} else {
			System.out.println("type 参数值有误");
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
			System.out.println("type 参数值有误");
			return null;
		}
	}
	
	
	
	
}
