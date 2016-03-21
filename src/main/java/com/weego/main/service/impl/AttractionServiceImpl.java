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
import com.weego.main.dao.AttractionDao;
import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POICommentsDto;
import com.weego.main.dto.POIDetailActivitiesDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailDto;
import com.weego.main.dto.POIDetailSpecialDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POIDetailTagDto;
import com.weego.main.dto.POIListDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.SearchNearByBaseDto;
import com.weego.main.dto.SearchNearByDto;
import com.weego.main.model.Activity;
import com.weego.main.model.Attraction;
import com.weego.main.model.AttractionSpot;
import com.weego.main.model.BasePOIActivities;
import com.weego.main.model.BasePOIComments;
import com.weego.main.model.BasePOITag;
import com.weego.main.service.AttractionService;
import com.weego.main.util.DistanceUtil;

@Service("attractionService")
public class AttractionServiceImpl implements AttractionService {

	private static Logger logger = LogManager
			.getLogger(AttractionServiceImpl.class);

	@Autowired
	AttractionDao attractionDao;

	@Autowired
	ActivityDao activityDao;

	@Override
	public POIListDto getAttractionsByCityId(String cityId, String labelId,
			Integer page, Integer count) {
		POIListDto poiListDto = new POIListDto();
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Attraction> attractions = attractionDao.getAttractionsByCityId(
				cityId, labelId, page, count);
		if (attractions != null && attractions.size() > 0) {
			for (Attraction attraction : attractions) {
				POIBaseDto poiBaseDto = new POIBaseDto();
				poiBaseDto.setCardId(attraction.getId());
				poiBaseDto.setBrief(attraction.getBriefIntroduction());
				poiBaseDto.setCoverImage(attraction.getCoverImage());
				List<BasePOITag> tags = attraction.getSubTag();
				if (tags != null && tags.size() > 0) {
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
					poiDetailSpecialDto.setId(attractionSpot.getId());
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

					Activity activity = activityDao
							.getSpecifiedCity(basePOIActivity.getId());
					if (activity != null) {
						poiDetailActivitiesDto
								.setActTime(activity.getActTime());
						poiDetailActivitiesDto.setCoverImage(activity
								.getImage());
						poiDetailActivitiesDto.setDesc(activity
								.getDescription());
						poiDetailActivitiesDto.setTag("");
					}

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
			// List<String> openTime = attraction.getOpenTime();

			String openTimeDesc = "营业中";
			poiDetailSumDto.setOpenTimeDesc(openTimeDesc);
			poiDetailSumDto.setOpenTableUrl(attraction.getOpenTableUrl());
			poiDetailSumDto.setOpenDay(0);
			poiDetailSumDto.setFacilities(null);
			poiDetailDto.setData(poiDetailSumDto);
		}
		return poiDetailDto;
	}

	@Override
	public POISpecialDto getAttractionSpotsById(String id) {
		POISpecialDto poiSpecialDto = new POISpecialDto();
		List<POISepcialBaseDto> poiSepcialBaseDtos = new ArrayList<POISepcialBaseDto>();
		Attraction attraction = attractionDao.getAttractionById(id);
		List<AttractionSpot> attractionSpots = new ArrayList<AttractionSpot>();
		if (attraction != null) {
			attractionSpots = attraction.getAttractionSpots();
			if (attractionSpots != null && attractionSpots.size() > 0) {
				for (AttractionSpot attractionSpot : attractionSpots) {
					POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
					poiSepcialBaseDto.setSpecialId(attractionSpot.getId());
					poiSepcialBaseDto.setCoverImage(attractionSpot
							.getCoverImage());
					poiSepcialBaseDto.setTag(attractionSpot.getTag());
					poiSepcialBaseDto.setTitle(attractionSpot.getTitle());
					poiSepcialBaseDto.setDesc(attractionSpot.getDesc());
					poiSepcialBaseDtos.add(poiSepcialBaseDto);
				}
			}
			poiSpecialDto.setData(poiSepcialBaseDtos);
		}
		return poiSpecialDto;
	}

	@Override
	public POICommentsDto getAttractionCommentsById(String id) {
		POICommentsDto poiCommentsDto = new POICommentsDto();
		List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
		Attraction attraction = attractionDao.getAttractionById(id);
		if (attraction != null) {
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
			poiCommentsDto.setData(poiDetailCommentsDtos);
		}
		return poiCommentsDto;
	}

	@Override
	public SearchNearByDto getAttractionsByCityIdAndCoordination(String cityId,
			String coordination, String sort) {
		SearchNearByDto searchNearByDto = new SearchNearByDto();
		List<SearchNearByBaseDto> searchNearByBaseDtos = new ArrayList<SearchNearByBaseDto>();
		List<Attraction> attractions = attractionDao
				.getAttractionsByCityIdAndCoordination(cityId, coordination);

		if (attractions != null && attractions.size() > 0) {
			for (Attraction attraction : attractions) {
				SearchNearByBaseDto searchNearByBaseDto = new SearchNearByBaseDto();
				searchNearByBaseDto.setId(attraction.getId());
				searchNearByBaseDto.setName(attraction.getName());
				searchNearByBaseDto.setAddress(attraction.getAddress());
				searchNearByBaseDto.setCoverImage(attraction.getCoverImage());

				String newCoordination = attraction.getCoordination();
				if (newCoordination != null && newCoordination.split(",").length >= 2) {
					String newLatitude = newCoordination.split(",")[1];
					String newLongitude = newCoordination.split(",")[0];

					if (!coordination.contains(",")) {
						logger.info("coordination 参数值有误");
					} else {
						if (coordination.split(",").length >= 2) {
							String latitude = coordination.split(",")[1];
							String longitude = coordination.split(",")[0];
							Double distance = DistanceUtil.getDistance(newLatitude, newLongitude, latitude, longitude);
							searchNearByBaseDto.setDistance(distance);
						}
					}
					searchNearByBaseDto.setLatitude(newLatitude);
					searchNearByBaseDto.setLongitude(newLongitude);
				}

				searchNearByBaseDto.setScore(attraction.getRating());
				List<BasePOITag> tags = attraction.getSubTag();
				if (tags != null && tags.size() > 0) {
					searchNearByBaseDto.setTag(attraction.getSubTag().get(0)
							.getTag());
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
