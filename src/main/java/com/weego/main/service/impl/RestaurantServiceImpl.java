package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.RestaurantDao;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailActivitiesDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIDetailSpecialDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POIDetailTagDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.model.BasePOIActivities;
import com.weego.main.model.BasePOIComments;
import com.weego.main.model.BasePOITag;
import com.weego.main.model.Restaurant;
import com.weego.main.model.RestaurantDish;
import com.weego.main.service.RestaurantService;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantDao restaurantDao;

	public POIListDto getRestaurantsByCityId(String cityId, String labelId) {
		POIListDto poiListDto = new POIListDto();
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Restaurant> restaurants = restaurantDao.getRestaurantsByCityId(cityId, labelId);
		if (restaurants != null && restaurants.size() > 0) {
			for (Restaurant restaurant : restaurants) {
				POIBaseDto poiBaseDto = new POIBaseDto();
				poiBaseDto.setCardId(restaurant.getId());
				poiBaseDto.setBrief(restaurant.getBriefIntroduction());
				poiBaseDto.setCoverImage(restaurant.getCoverImage());
				List<BasePOITag> tags = restaurant.getSubTag();
				if(tags != null && tags.size() > 0) {
					poiBaseDto.setTag(restaurant.getSubTag().get(0).getTag());
				}
				poiBaseDto.setTitle(restaurant.getName());
				poiBaseDtos.add(poiBaseDto);
			}
		}
		poiListDto.setData(poiBaseDtos);
		return poiListDto;
	}

	@Override
	public POIDetailDto getRestaurantById(String id) {
		POIDetailDto poiDetailDto = new POIDetailDto();
		POIDetailSumDto poiDetailSumDto = new POIDetailSumDto();
		Restaurant restaurant = restaurantDao.getRestaurantById(id);

		if (restaurant != null) {
			poiDetailSumDto.setId(restaurant.getId());
			poiDetailSumDto.setType(1);
			poiDetailSumDto.setName(restaurant.getName());
			poiDetailSumDto.setNameEn(restaurant.getNameEn());
			poiDetailSumDto.setAddress(restaurant.getAddress());
			poiDetailSumDto.setTel(restaurant.getTel());
			poiDetailSumDto.setWebsite(restaurant.getWebsite());
			poiDetailSumDto.setBriefIntroduction(restaurant
					.getBriefIntroduction());
			poiDetailSumDto.setIntroduction(restaurant.getIntroduction());
			poiDetailSumDto.setCityName(restaurant.getCityName());
			poiDetailSumDto.setCityId(restaurant.getCityId());

			String coordination = restaurant.getCoordination();
			if (coordination != null && coordination.split(",").length >= 2) {
				String latitude = coordination.split(",")[1];
				String longitude = coordination.split(",")[0];
				poiDetailSumDto.setLatitude(latitude);
				poiDetailSumDto.setLongitude(longitude);
			}

			poiDetailSumDto.setImage(restaurant.getImage());
			poiDetailSumDto.setCoverImage(restaurant.getCoverImage());
			poiDetailSumDto.setOpenTime(restaurant.getOpenTime());
			poiDetailSumDto.setPriceDesc(restaurant.getPriceDesc());
			poiDetailSumDto.setRating(restaurant.getRating());

			List<POIDetailSpecialDto> poiDetailSpecialDtos = new ArrayList<POIDetailSpecialDto>();
			List<RestaurantDish> restaurantDishs = restaurant
					.getRestaurantDishs();
			if (restaurantDishs != null && restaurantDishs.size() > 0) {
				for (RestaurantDish restaurantDish : restaurantDishs) {
					POIDetailSpecialDto poiDetailSpecialDto = new POIDetailSpecialDto();
					poiDetailSpecialDto.setAdvice(restaurantDish.getAdvice());
					poiDetailSpecialDto.setDesc(restaurantDish.getDesc());
					poiDetailSpecialDto.setTitle(restaurantDish.getTitle());
					poiDetailSpecialDto.setTag(restaurantDish.getTag());
					poiDetailSpecialDto.setCoverImage(restaurantDish
							.getCoverImage());
					poiDetailSpecialDtos.add(poiDetailSpecialDto);
				}
			}
			poiDetailSumDto.setSpecial(poiDetailSpecialDtos);

			List<POIDetailActivitiesDto> poiDetailActivitiesDtos = new ArrayList<POIDetailActivitiesDto>();
			List<BasePOIActivities> basePOIActivities = restaurant
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
			List<BasePOITag> basePOITags = restaurant.getSubTag();
			if (basePOITags != null && basePOITags.size() > 0) {
				for (BasePOITag basePOITag : basePOITags) {
					POIDetailTagDto poiDetailTagDto = new POIDetailTagDto();
					poiDetailTagDto.setId(basePOITag.getId());
					poiDetailTagDto.setName(basePOITag.getTag());
					poiDetailTagDtos.add(poiDetailTagDto);
				}
			}
			poiDetailSumDto.setTag(poiDetailTagDtos);

			poiDetailSumDto.setTips(restaurant.getTips());
			poiDetailSumDto.setCommentsUrl(restaurant.getCommentsUrl());
			poiDetailSumDto.setCommentFrom(restaurant.getCommentFrom());

			List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
			List<BasePOIComments> basePOIComments = restaurant.getComments();
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
			poiDetailSumDto.setOpenTableUrl(restaurant.getOpenTableUrl());
			poiDetailSumDto.setOpenDay(0);

			poiDetailSumDto.setFacilities(null);
			poiDetailDto.setData(poiDetailSumDto);
		}
		return poiDetailDto;
	}

}
