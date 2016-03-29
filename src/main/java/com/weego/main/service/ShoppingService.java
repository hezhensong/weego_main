package com.weego.main.service;

import java.util.List;

import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.SearchNearByBaseDto;

public interface ShoppingService {

	List<POIBaseDto> getShoppingByCityId(String cityId, String labelId, Integer page, Integer count);

	POIDetailSumDto getShoppingById(String id, String coordination);

	List<POISepcialBaseDto> getShoppingBrandsById(String id);

	List<POIDetailCommentsDto> getShoppingCommentsById(String id);

	SearchNearByBaseDto getShoppingByCityIdAndCoordination(String cityId, String coordination, String sort,
                                                       Double range, Integer price, String special);
}
