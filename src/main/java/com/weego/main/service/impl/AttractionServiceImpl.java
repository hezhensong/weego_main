package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.AttractionDao;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailActivitiesDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIDetailSpecialDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POIDetailTagDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.model.Attraction;
import com.weego.main.model.AttractionSpot;
import com.weego.main.model.BasePOIActivities;
import com.weego.main.model.BasePOIComments;
import com.weego.main.model.BasePOITag;
import com.weego.main.service.AttractionService;

@Service("attractionService")
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	AttractionDao attractionDao;

	@Override
	public POIListDto getAttractionsByCityId(String cityId, String labelId) {
		POIListDto poiListDto = new POIListDto();
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Attraction> attractions = attractionDao.getAttractionsByCityId(
				cityId, labelId);
		if (attractions != null && attractions.size() > 0) {
			for (Attraction attraction : attractions) {
				POIBaseDto poiBaseDto = new POIBaseDto();
				poiBaseDto.setCardId(attraction.getId());
				poiBaseDto.setBrief(attraction.getBriefIntroduction());
				poiBaseDto.setCoverImage(attraction.getCoverImage());
				List<BasePOITag> tags = attraction.getSubTag();
				if(tags != null && tags.size() > 0) {
					poiBaseDto.setTag(attraction.getSubTag().get(0).getTag());
				}
				poiBaseDto.setTitle(attraction.getName());
				poiBaseDtos.add(poiBaseDto);
			}
		}
		poiListDto.setData(poiBaseDtos);
		return poiListDto;
	}

	@Override
	public POIDetailDto getAttractionById(String id) {
		POIDetailDto poiDetailDto = new POIDetailDto();
		POIDetailSumDto poiDetailSumDto = new POIDetailSumDto();
		Attraction attraction = attractionDao.getAttractionById(id);

		if (attraction != null) {
			poiDetailSumDto.setId(attraction.getId());
			poiDetailSumDto.setType(0);
			poiDetailSumDto.setName(attraction.getName());
			poiDetailSumDto.setNameEn(attraction.getNameEn());
			poiDetailSumDto.setAddress(attraction.getAddress());
			poiDetailSumDto.setTel(attraction.getTel());
			poiDetailSumDto.setWebsite(attraction.getWebsite());
			poiDetailSumDto.setBriefIntroduction(attraction
					.getBriefIntroduction());
			poiDetailSumDto.setIntroduction(attraction.getIntroduction());
			poiDetailSumDto.setCityName(attraction.getCityName());
			poiDetailSumDto.setCityId(attraction.getCityId());

			String coordination = attraction.getCoordination();
			if (coordination != null && coordination.split(",").length >= 2) {
				String latitude = coordination.split(",")[1];
				String longitude = coordination.split(",")[0];
				poiDetailSumDto.setLatitude(latitude);
				poiDetailSumDto.setLongitude(longitude);
			}

			poiDetailSumDto.setImage(attraction.getImage());
			poiDetailSumDto.setCoverImage(attraction.getCoverImage());
			poiDetailSumDto.setOpenTime(attraction.getOpenTime());
			poiDetailSumDto.setPriceDesc(attraction.getPriceDesc());
			poiDetailSumDto.setRating(attraction.getRating());

			List<POIDetailSpecialDto> poiDetailSpecialDtos = new ArrayList<POIDetailSpecialDto>();
			List<AttractionSpot> attractionSpots = attraction
					.getAttractionSpots();
			if (attractionSpots != null && attractionSpots.size() > 0) {
				for (AttractionSpot attractionSpot : attractionSpots) {
					POIDetailSpecialDto poiDetailSpecialDto = new POIDetailSpecialDto();
					poiDetailSpecialDto.setAdvice(attractionSpot.getAdvice());
					poiDetailSpecialDto.setDesc(attractionSpot.getDesc());
					poiDetailSpecialDto.setTitle(attractionSpot.getTitle());
					poiDetailSpecialDto.setTag(attractionSpot.getTag());
					poiDetailSpecialDto.setCoverImage(attractionSpot
							.getCoverImage());
					poiDetailSpecialDtos.add(poiDetailSpecialDto);
				}
			}
			poiDetailSumDto.setSpecial(poiDetailSpecialDtos);

			List<POIDetailActivitiesDto> poiDetailActivitiesDtos = new ArrayList<POIDetailActivitiesDto>();
			List<BasePOIActivities> basePOIActivities = attraction
					.getActivities();
			if (basePOIActivities != null && basePOIActivities.size() > 0) {
				for (BasePOIActivities basePOIActivity : basePOIActivities) {
					POIDetailActivitiesDto poiDetailActivitiesDto = new POIDetailActivitiesDto();
					poiDetailActivitiesDto.setActivityId(basePOIActivity
							.getId());
					poiDetailActivitiesDto.setTitle(basePOIActivity.getTitle());
					// not finished

					poiDetailActivitiesDtos.add(poiDetailActivitiesDto);
				}
			}
			poiDetailSumDto.setActivities(poiDetailActivitiesDtos);

			List<POIDetailTagDto> poiDetailTagDtos = new ArrayList<POIDetailTagDto>();
			List<BasePOITag> basePOITags = attraction.getSubTag();
			if (basePOITags != null && basePOITags.size() > 0) {
				for (BasePOITag basePOITag : basePOITags) {
					POIDetailTagDto poiDetailTagDto = new POIDetailTagDto();
					poiDetailTagDto.setId(basePOITag.getId());
					poiDetailTagDto.setName(basePOITag.getTag());
					poiDetailTagDtos.add(poiDetailTagDto);
				}
			}
			poiDetailSumDto.setTag(poiDetailTagDtos);

			poiDetailSumDto.setTips(attraction.getTips());
			poiDetailSumDto.setCommentsUrl(attraction.getCommentsUrl());
			poiDetailSumDto.setCommentFrom(attraction.getCommentFrom());

			List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
			List<BasePOIComments> basePOIComments = attraction.getComments();
			if (basePOIComments != null && basePOIComments.size() > 0) {
				for (BasePOIComments basePOIComment : basePOIComments) {
					POIDetailCommentsDto poiDetailCommentsDto = new POIDetailCommentsDto();
					poiDetailCommentsDto.setNickname(basePOIComment
							.getNickname());
					poiDetailCommentsDto.setDate(basePOIComment.getDate());
					poiDetailCommentsDto.setText(basePOIComment.getText());
					poiDetailCommentsDto.setRating(basePOIComment.getRating());
					poiDetailCommentsDto.setTitle(basePOIComment.getTitle());
					poiDetailCommentsDto.setLanguage(basePOIComment
							.getLanguage());
					poiDetailCommentsDtos.add(poiDetailCommentsDto);
				}
			}
			poiDetailSumDto.setComments(poiDetailCommentsDtos);

			poiDetailSumDto.setDistance(0L);
			poiDetailSumDto.setOpenTimeDesc("测试一下");
			poiDetailSumDto.setOpenTableUrl(attraction.getOpenTableUrl());
			poiDetailSumDto.setOpenDay(0);

			poiDetailSumDto.setFacilities(null);

			poiDetailDto.setData(poiDetailSumDto);
		}
		return poiDetailDto;
	}

}
