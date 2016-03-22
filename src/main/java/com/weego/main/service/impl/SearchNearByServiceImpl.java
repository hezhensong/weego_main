package com.weego.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dto.SearchNearByDto;
import com.weego.main.service.AttractionService;
import com.weego.main.service.RestaurantService;
import com.weego.main.service.SearchNearByService;
import com.weego.main.service.ShoppingService;

@Service("searchNearByService")
public class SearchNearByServiceImpl implements SearchNearByService {
	@Autowired
	AttractionService attractionService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	ShoppingService shoppingService;
	@Override
	public SearchNearByDto getSearchNearByInfos(String cityId, Integer type, String coordination, String sort,
			Double range, Integer price, String special) {
		if (type == 0) {
			return attractionService.getAttractionsByCityIdAndCoordination(cityId, coordination, sort, range, price, special);
		} else if (type == 1) {
			return restaurantService.getRestaurantsByCityIdAndCoordination(cityId, coordination, sort, range, price, special);
		} else if (type == 2) {
			return shoppingService.getShoppingsByCityIdAndCoordination(cityId, coordination, sort, range, price, special);
		} else {
			System.out.println("type 参数值有误");
			return null;
		}
	}
	
	
	
	
}
