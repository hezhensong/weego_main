package com.weego.main.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.util.Base64;
import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.dto.SearchNearByDto;
import com.weego.main.service.SearchNearByService;

@RestController
@RequestMapping("/api/v3/city")
public class SearchNearbyController {
	
	private static Logger logger = LogManager.getLogger(SearchNearbyController.class);
	
	@Autowired
	SearchNearByService searchNearByService;
	
	@ResponseBody
	@RequestMapping("/searchNearby")
	public ResponseDto<SearchNearByDto> getSearchNearByInfos(
			@RequestParam("cityId") String cityId,
    		@RequestParam("type") Integer type,
    		@RequestParam("coordination") String coordination,
			@RequestParam(value = "sort", defaultValue = "distance") String sort,
			@RequestParam(value = "range", defaultValue = "50000") Double range,
			@RequestParam(value = "price", defaultValue = "0") Integer price,
			@RequestParam(value = "special", defaultValue = "sp") String special) {
		
		SearchNearByDto searchNearByDto = searchNearByService.getSearchNearByInfos(cityId, type, coordination, sort, range, price, special);
		ResponseDto<SearchNearByDto> pResponseDto = new ResponseDto<SearchNearByDto>();
		
		if(searchNearByDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("搜索周边信息为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(searchNearByDto);
		}
		return pResponseDto;
	}
}
