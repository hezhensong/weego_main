package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.ActivityDao;
import com.weego.main.dao.RestaurantDao;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailActivitiesDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIDetailFacilitiesDto;
import com.weego.main.dto.POIDetailSpecialDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POIDetailTagDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.SearchNearByBaseDto;
import com.weego.main.dto.SearchNearByDto;
import com.weego.main.model.Activity;
import com.weego.main.model.BasePOIActivities;
import com.weego.main.model.BasePOIComments;
import com.weego.main.model.BasePOITag;
import com.weego.main.model.Restaurant;
import com.weego.main.model.RestaurantDish;
import com.weego.main.model.RestaurantFacilities;
import com.weego.main.service.RestaurantService;
import com.weego.main.util.DistanceUtil;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

	private static Logger logger = LogManager
			.getLogger(RestaurantServiceImpl.class);

	@Autowired
	RestaurantDao restaurantDao;
	@Autowired
	ActivityDao activityDao;

	public POIListDto getRestaurantsByCityId(String cityId, String labelId,
			Integer page, Integer count) {
		POIListDto poiListDto = new POIListDto();
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Restaurant> restaurants = restaurantDao.getRestaurantsByCityId(
				cityId, labelId, page, count);
		if (restaurants != null && restaurants.size() > 0) {
			for (Restaurant restaurant : restaurants) {
				POIBaseDto poiBaseDto = new POIBaseDto();
				poiBaseDto.setCardId(restaurant.getId());
				poiBaseDto.setBrief(restaurant.getBriefIntroduction());
				poiBaseDto.setCoverImage(restaurant.getCoverImage());
				List<BasePOITag> tags = restaurant.getSubTag();
				if (tags != null && tags.size() > 0) {
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
					poiDetailSpecialDto.setId(restaurantDish.getId());
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

					Activity activity = activityDao.getSpecifiedCity(basePOIActivity.getId());
					if (activity != null) {
						poiDetailActivitiesDto.setActTime(activity.getActTime());
						poiDetailActivitiesDto.setCoverImage(activity.getImage());
						poiDetailActivitiesDto.setDesc(activity.getDescription());
						poiDetailActivitiesDto.setTag("");
					}

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

			String openTimeDesc = "营业中";
			poiDetailSumDto.setOpenTimeDesc(openTimeDesc);
			poiDetailSumDto.setOpenTableUrl(restaurant.getOpenTableUrl());
			poiDetailSumDto.setOpenDay(0);

			POIDetailFacilitiesDto poiDetailFacilitiesDto = new POIDetailFacilitiesDto();
			RestaurantFacilities restaurantFacilities = restaurant.getRestaurantFacilities();
			if (restaurantFacilities != null) {
				poiDetailFacilitiesDto.setAlcohol(restaurantFacilities
						.getAlcohol());
				poiDetailFacilitiesDto
						.setNoise(restaurantFacilities.getNoise());
				poiDetailFacilitiesDto.setWaiter(restaurantFacilities
						.isWaiter());
				poiDetailFacilitiesDto.setTv(restaurantFacilities.isTv());
				poiDetailFacilitiesDto.setOutseat(restaurantFacilities
						.isOutseat());
				poiDetailFacilitiesDto.setGroup(restaurantFacilities.isGroup());
				poiDetailFacilitiesDto.setKid(restaurantFacilities.isKid());
				poiDetailFacilitiesDto.setCard(restaurantFacilities.isCard());
				poiDetailFacilitiesDto.setTakeout(restaurantFacilities
						.isTakeout());
				poiDetailFacilitiesDto.setDelivery(restaurantFacilities
						.isDelivery());
				poiDetailFacilitiesDto.setReserve(restaurantFacilities
						.isReserve());
				poiDetailFacilitiesDto.setWifi(restaurantFacilities.isWifi());
			}

			poiDetailSumDto.setFacilities(poiDetailFacilitiesDto);
			poiDetailDto.setData(poiDetailSumDto);
		}
		return poiDetailDto;
	}

	@Override
	public POISpecialDto getRestaurantDishesById(String id) {
		POISpecialDto poiSpecialDto = new POISpecialDto();
		List<POISepcialBaseDto> poiSepcialBaseDtos = new ArrayList<POISepcialBaseDto>();
		Restaurant restaurant = restaurantDao.getRestaurantById(id);
		List<RestaurantDish> restaurantDishs = new ArrayList<RestaurantDish>();
		if (restaurant != null) {
			restaurantDishs = restaurant.getRestaurantDishs();
			if (restaurantDishs != null && restaurantDishs.size() > 0) {
				for (RestaurantDish restaurantDish : restaurantDishs) {
					POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
					poiSepcialBaseDto.setSpecialId(restaurantDish.getId());
					poiSepcialBaseDto.setCoverImage(restaurantDish
							.getCoverImage());
					poiSepcialBaseDto.setTag(restaurantDish.getTag());
					poiSepcialBaseDto.setTitle(restaurantDish.getTitle());
					poiSepcialBaseDto.setDesc(restaurantDish.getDesc());
					poiSepcialBaseDtos.add(poiSepcialBaseDto);
				}
			}
			poiSpecialDto.setData(poiSepcialBaseDtos);
		}
		return poiSpecialDto;
	}

	@Override
	public POICommentsDto getRestaurantCommentsById(String id) {
		POICommentsDto poiCommentsDto = new POICommentsDto();
		List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
		Restaurant restaurant = restaurantDao.getRestaurantById(id);
		if (restaurant != null) {
			List<BasePOIComments> basePOIComments = restaurant.getComments();
			if (basePOIComments != null && basePOIComments.size() > 0) {
				for (BasePOIComments basePOIComment : basePOIComments) {
					POIDetailCommentsDto poiDetailCommentsDto = new POIDetailCommentsDto();
					poiDetailCommentsDto.setNickname(basePOIComment
							.getNickname());
					poiDetailCommentsDto.setDate(basePOIComment.getDate());
					System.out.println(basePOIComment.getDate());
					poiDetailCommentsDto.setText(basePOIComment.getText());
					poiDetailCommentsDto.setRating(basePOIComment.getRating());
					poiDetailCommentsDto.setTitle(basePOIComment.getTitle());
					poiDetailCommentsDto.setLanguage(basePOIComment
							.getLanguage());
					poiDetailCommentsDtos.add(poiDetailCommentsDto);
				}
			}
			poiCommentsDto.setData(poiDetailCommentsDtos);
		}
		return poiCommentsDto;
	}

	@Override
	public SearchNearByDto getRestaurantsByCityIdAndCoordination(String cityId,
			String coordination, String sort) {
		SearchNearByDto searchNearByDto = new SearchNearByDto();
		List<SearchNearByBaseDto> searchNearByBaseDtos = new ArrayList<SearchNearByBaseDto>();
		List<Restaurant> restaurants = restaurantDao.getRestaurantsByCityIdAndCoordination(cityId, coordination);

		if (restaurants != null && restaurants.size() > 0) {
			for (Restaurant restaurant : restaurants) {
				SearchNearByBaseDto searchNearByBaseDto = new SearchNearByBaseDto();
				searchNearByBaseDto.setId(restaurant.getId());
				searchNearByBaseDto.setName(restaurant.getName());
				searchNearByBaseDto.setAddress(restaurant.getAddress());
				searchNearByBaseDto.setCoverImage(restaurant.getCoverImage());

				String newCoordination = restaurant.getCoordination();
				if (newCoordination != null
						&& newCoordination.split(",").length >= 2) {
					String newLongitude = newCoordination.split(",")[0];
					String newLatitude = newCoordination.split(",")[1];

					if (!coordination.contains(",")) {
						logger.info("coordination 参数值有误");
					} else {
						if (coordination.split(",").length >= 2) {
							String longitude = coordination.split(",")[0];
							String latitude = coordination.split(",")[1];
							Double distance = DistanceUtil.getDistance(
									newLatitude, newLongitude, latitude,
									longitude);
							searchNearByBaseDto.setDistance(distance);
						}
					}

					searchNearByBaseDto.setLatitude(newLatitude);
					searchNearByBaseDto.setLongitude(newLongitude);
				}

				searchNearByBaseDto.setScore(restaurant.getRating());
				List<BasePOITag> tags = restaurant.getSubTag();
				if (tags != null && tags.size() > 0) {
					searchNearByBaseDto.setTag(restaurant.getSubTag().get(0).getTag());
				}
				searchNearByBaseDtos.add(searchNearByBaseDto);
			}
		}

		if(searchNearByBaseDtos != null && searchNearByBaseDtos.size() > 0) {
			// 排序
			if (sort.equals("distance")) {
				Collections.sort(searchNearByBaseDtos,
						new Comparator<SearchNearByBaseDto>() {
							public int compare(SearchNearByBaseDto o1, SearchNearByBaseDto o2) {
								if(o1.getDistance() == null) {
									return -1;
								}
								
								if(o2.getDistance() == null) {
									return 1;
								}
								
								int comparation = o1.getDistance().compareTo(o2.getDistance());
								if(comparation == 0) {
									return o2.getScore().compareTo(o1.getScore());
								} else {
									return comparation;
								}
							};
						});
			} else if (sort.equals("rating")) {
				Collections.sort(searchNearByBaseDtos,
						new Comparator<SearchNearByBaseDto>() {
							public int compare(SearchNearByBaseDto o1, SearchNearByBaseDto o2) {
								int comparation = o2.getScore().compareTo(o1.getScore());
								if(comparation == 0) {
									if(o1.getDistance() == null) {
										return -1; 
									}
									
									if(o2.getDistance() == null) {
										return 1;
									}
									
									return o1.getDistance().compareTo(o2.getDistance());
								} else {
									return comparation;
								}
							};
						});
			}
		}
		searchNearByDto.setData(searchNearByBaseDtos);
		return searchNearByDto;
	}

}
