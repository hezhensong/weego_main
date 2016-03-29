package com.weego.main.controller.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.POITranslationBaseDto;
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
	public ResponseDto<List<POIBaseDto>> getPOIList(@RequestParam("cityId") String cityId,
			@RequestParam("type") Integer type,
			@RequestParam("labelId") String labelId,
			@RequestParam(value = "page" ,defaultValue = "1") Integer page,
			@RequestParam(value = "count" ,defaultValue = "5") Integer count) {

		List<POIBaseDto> poiBaseDtos = basePOIService.getPOIsByCityId(cityId, type, labelId, page, count);
		ResponseDto<List<POIBaseDto>> pResponseDto = new ResponseDto<List<POIBaseDto>>();
		
		if(poiBaseDtos == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiBaseDtos);
		}
		
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/poi", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POIDetailSumDto> getPOIDetail(@RequestParam("id") String id,
			@RequestParam("type") Integer type,
		    @RequestParam("coordination") String coordination) {
		
		POIDetailSumDto poiDetailSumDto = basePOIService.getPOIDetailById(id, type, coordination);
		ResponseDto<POIDetailSumDto> pResponseDto = new ResponseDto<POIDetailSumDto>();
		
		if(poiDetailSumDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市详情页为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiDetailSumDto);
		}
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/specialList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<List<POISepcialBaseDto>> getPOISpecialList(@RequestParam("poiId") String poiId,
			@RequestParam("type") Integer type) {

		List<POISepcialBaseDto> poiSepcialBaseDtos = basePOIService.getPOISpecialById(poiId, type);
		ResponseDto<List<POISepcialBaseDto>> pResponseDto = new ResponseDto<List<POISepcialBaseDto>>();
		
		if(poiSepcialBaseDtos == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市特色列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiSepcialBaseDtos);
		}
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/specialDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POISepcialBaseDto> getPOISpecialDetail(
			@RequestParam("specialId") String specialId,
			@RequestParam("type") Integer type) {

		POISepcialBaseDto poiSepcialBaseDto = basePOIService.getPOISpecialDetailById(specialId, type);
		ResponseDto<POISepcialBaseDto> pResponseDto = new ResponseDto<POISepcialBaseDto>();
		
		if(poiSepcialBaseDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市特色详情页为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiSepcialBaseDto);
		}
		
		return pResponseDto;
	}

	@RequestMapping(value = "/discovery/comment", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<List<POIDetailCommentsDto>> getPOIComments(@RequestParam("poiId") String poiId,
			@RequestParam("type") Integer type) {

		List<POIDetailCommentsDto> poiDetailCommentsDtos = basePOIService.getPOICommentsById(poiId, type);
		ResponseDto<List<POIDetailCommentsDto>> pResponseDto = new ResponseDto<List<POIDetailCommentsDto>>();
		
		if(poiDetailCommentsDtos == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市评论列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiDetailCommentsDtos);
		}
		return pResponseDto;
	}
	
	@RequestMapping(value = "/discovery/translate", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto<POITranslationBaseDto> getPOITranslation(
			@RequestParam("content") String content,
			@RequestParam(value = "from", defaultValue = "en") String from,
			@RequestParam(value = "to", defaultValue = "zh") String to) {

		POITranslationBaseDto poiTranslationBaseDto = basePOIService.getPOITranslation(content, from, to);
		ResponseDto<POITranslationBaseDto> pResponseDto = new ResponseDto<POITranslationBaseDto>();
		
		if(poiTranslationBaseDto == null) {
			pResponseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
			logger.info("探索城市评论列表为空");
		} else {
			pResponseDto.setCodeMessage(ErrorCode.SUCCESS);
			pResponseDto.setData(poiTranslationBaseDto);
		}
		
		return pResponseDto;
	}
}
