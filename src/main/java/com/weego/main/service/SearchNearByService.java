package com.weego.main.service;

import com.weego.main.dto.SearchNearByDto;

public interface SearchNearByService {
	SearchNearByDto getSearchNearByInfos(String cityId, Integer type,
			String coordination);
}
