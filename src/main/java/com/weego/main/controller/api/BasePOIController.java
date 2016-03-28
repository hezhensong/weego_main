package com.weego.main.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.POITranslationDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.service.BasePOIService;

@RestController
@RequestMapping("/api/v3/city")
public class BasePOIController {

	private static Logger logger = LogManager.getLogger(BasePOIController.class);
	
	@Autowired
	BasePOIService basePOIService;

	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POIListDto> getPOIList(@RequestParam("cityId") String cityId,
			@RequestParam("type") Integer type,
			@RequestParam("labelId") String labelId,
			@RequestParam(value = "page" ,defaultValue = "1") Integer page,
			@RequestParam(value = "count" ,defaultValue = "5") Integer count) {

		POIListDto poiListDto = basePOIService.getPOIsByCityId(cityId, type, labelId, page, count);
		ResponseDto<POIListDto> pResponseDto = new ResponseDto<POIListDto>();
		
		if(poiListDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiListDto);
		}
		
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/poi", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POIDetailDto> getPOIDetail(@RequestParam("id") String id,
			@RequestParam("type") Integer type,
		    @RequestParam("coordination") String coordination) {
		
		POIDetailDto poiDetailDto = basePOIService.getPOIDetailById(id, type, coordination);
		ResponseDto<POIDetailDto> pResponseDto = new ResponseDto<POIDetailDto>();
		
		if(poiDetailDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市详情页为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiDetailDto);
		}
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/specialList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POISpecialDto> getPOISpecialList(@RequestParam("poiId") String poiId,
			@RequestParam("type") Integer type) {

		POISpecialDto poiSpecialDto = basePOIService.getPOISpecialById(poiId, type);
		ResponseDto<POISpecialDto> pResponseDto = new ResponseDto<POISpecialDto>();
		
		if(poiSpecialDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市特色列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiSpecialDto);
		}
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/specialDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POISpecialDetailDto> getPOISpecialDetail(
			@RequestParam("specialId") String specialId,
			@RequestParam("type") Integer type) {

		POISpecialDetailDto poiSpecialDetailDto = basePOIService.getPOISpecialDetailById(specialId, type);
		ResponseDto<POISpecialDetailDto> pResponseDto = new ResponseDto<POISpecialDetailDto>();
		
		if(poiSpecialDetailDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市特色详情页为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiSpecialDetailDto);
		}
		
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/comment", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POICommentsDto> getPOIComments(@RequestParam("poiId") String poiId,
			@RequestParam("type") Integer type) {

		POICommentsDto poiCommentsDto = basePOIService.getPOICommentsById(poiId, type);
		ResponseDto<POICommentsDto> pResponseDto = new ResponseDto<POICommentsDto>();
		
		if(poiCommentsDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市评论列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiCommentsDto);
		}
		return pResponseDto;
	}
	
	@RequestMapping(value = "/discovery/translate", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POITranslationDto> getPOITranslation(
			@RequestParam("content") String content,
			@RequestParam(value = "from", defaultValue = "en") String from,
			@RequestParam(value = "to", defaultValue = "zh") String to) {

		POITranslationDto poiTranslationDto = basePOIService.getPOITranslation(content, from, to);
		ResponseDto<POITranslationDto> pResponseDto = new ResponseDto<POITranslationDto>();
		
		if(poiTranslationDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市评论列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiTranslationDto);
		}
		
		return pResponseDto;
	}
}
