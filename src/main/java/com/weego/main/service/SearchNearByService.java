package com.weego.main.service;

import com.weego.main.dto.SearchNearByBaseDto;

public interface SearchNearByService {
	SearchNearByBaseDto getSearchNearByInfos(String cityId, Integer type,
			String coordination, String sort, Double range, Integer price, String special);
}
