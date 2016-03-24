package com.weego.main.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.POITranslationDto;
import com.weego.main.service.BasePOIService;

@RestController
@RequestMapping("/api/v3/city")
public class BasePOIController {

	@Autowired
	BasePOIService basePOIService;

	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
	@ResponseBody
	public POIListDto getPOIList(@RequestParam("cityId") String cityId,
			@RequestParam("type") Integer type,
			@RequestParam("labelId") String labelId,
			@RequestParam("page") Integer page,
			@RequestParam("count") Integer count) {

		return basePOIService.getPOIsByCityId(cityId, type, labelId, page,
				count);
	}

	@RequestMapping(value = "/discovery/poi", method = RequestMethod.GET)
	@ResponseBody
	public POIDetailDto getPOIDetail(@RequestParam("id") String id,
			@RequestParam("type") Integer type,
		    @RequestParam("coordination") String coordination) {

		return basePOIService.getPOIDetailById(id, type, coordination);
	}

	@RequestMapping(value = "/discovery/specialList", method = RequestMethod.GET)
	@ResponseBody
	public POISpecialDto getPOISpecialList(@RequestParam("poiId") String poiId,
			@RequestParam("type") Integer type) {

		return basePOIService.getPOISpecialById(poiId, type);
	}

	@RequestMapping(value = "/discovery/specialDetail", method = RequestMethod.GET)
	@ResponseBody
	public POISpecialDetailDto getPOISpecialDetail(
			@RequestParam("specialId") String specialId,
			@RequestParam("type") Integer type) {

		return basePOIService.getPOISpecialDetailById(specialId, type);
	}

	@RequestMapping(value = "/discovery/comment", method = RequestMethod.GET)
	@ResponseBody
	public POICommentsDto getPOIComments(@RequestParam("poiId") String poiId,
			@RequestParam("type") Integer type) {

		return basePOIService.getPOICommentsById(poiId, type);
	}
	
	@RequestMapping(value = "/discovery/translate", method = RequestMethod.GET)
	@ResponseBody
	public POITranslationDto getPOITranslation(
			@RequestParam("content") String content,
			@RequestParam(value = "from", defaultValue = "en") String from,
			@RequestParam(value = "to", defaultValue = "zh") String to) {

		return basePOIService.getPOITranslation(content, from, to);
	}
}
