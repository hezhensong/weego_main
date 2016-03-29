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
import com.weego.main.dto.POIDetailActivitiesDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailSpecialDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POIDetailTagDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.SearchNearByBaseDto;
import com.weego.main.dto.SearchNearByListDto;
import com.weego.main.dto.SearchNearByTagDto;
import com.weego.main.model.Activity;
import com.weego.main.model.Attraction;
import com.weego.main.model.AttractionSpot;
import com.weego.main.model.BasePOIActivities;
import com.weego.main.model.BasePOIComments;
import com.weego.main.model.BasePOIOpenTime;
import com.weego.main.model.BasePOITag;
import com.weego.main.service.AttractionService;
import com.weego.main.util.DateUtil;
import com.weego.main.util.DistanceUtil;

@Service("attractionService")
public class AttractionServiceImpl implements AttractionService {

	private static Logger logger = LogManager.getLogger(AttractionServiceImpl.class);

	@Autowired
	AttractionDao attractionDao;

	@Autowired
	ActivityDao activityDao;

	@Override
	public List<POIBaseDto> getAttractionsByCityId(String cityId, String labelId,
			Integer page, Integer count) {

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
		return poiBaseDtos;
	}

	@Override
	public POIDetailSumDto getAttractionById(String id, String coordination) {
		POIDetailSumDto poiDetailSumDto = new POIDetailSumDto();
		Attraction attraction = attractionDao.getAttractionById(id);

		if (attraction != null) {
			poiDetailSumDto.setId(attraction.getId());
			poiDetailSumDto.setType(attraction.getType());
			poiDetailSumDto.setName(attraction.getName());
			poiDetailSumDto.setNameEn(attraction.getNameEn());
			poiDetailSumDto.setAddress(attraction.getAddress());
			poiDetailSumDto.setTel(attraction.getTel());
			poiDetailSumDto.setWebsite(attraction.getWebsite());
			poiDetailSumDto.setBriefIntroduction(attraction.getBriefIntroduction());
			poiDetailSumDto.setIntroduction(attraction.getIntroduction());
			poiDetailSumDto.setCityName(attraction.getCityName());
			poiDetailSumDto.setCityId(attraction.getCityId());

			
			String newCoordination = attraction.getCoordination();
			if (newCoordination != null && newCoordination.split(",").length >= 2) {
				String newLatitude = newCoordination.split(",")[1];
				String newLongitude = newCoordination.split(",")[0];
				poiDetailSumDto.setLatitude(newLatitude);
				poiDetailSumDto.setLongitude(newLongitude);
				
				if(coordination.split(",").length >= 2) {
					String latitude = coordination.split(",")[1];
					String longitude = coordination.split(",")[0];
					Double distance =  DistanceUtil.getDistance(latitude, longitude, newLatitude, newLongitude);
					poiDetailSumDto.setDistance(distance);
				} else {
					logger.info("coordination 参数值有误");
				}
			}

			poiDetailSumDto.setImage(attraction.getImage());
			poiDetailSumDto.setCoverImage(attraction.getCoverImage());
			
			List<BasePOIOpenTime> openTimes = attraction.getOpenTime();
			List<String> openTimeDesc = new ArrayList<String>();
			if(openTimes != null && openTimes.size() > 0) {
				for(BasePOIOpenTime openTime : openTimes) {
					openTimeDesc.add(openTime.getDesc());
				}
				poiDetailSumDto.setOpenTime(openTimeDesc);
			}
			
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
					poiDetailSpecialDto.setCoverImage(attractionSpot.getCoverImage());
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
					poiDetailActivitiesDto.setActivityId(basePOIActivity.getId());
					poiDetailActivitiesDto.setTitle(basePOIActivity.getTitle());

					Activity activity = activityDao
							.getSpecifiedActivity(basePOIActivity.getId());
					if (activity != null) {
						poiDetailActivitiesDto.setActTime(activity.getActTime());
						poiDetailActivitiesDto.setCoverImage(activity.getCoverImage());
						poiDetailActivitiesDto.setDesc(activity.getDescription());
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
					poiDetailCommentsDto.setNickname(basePOIComment.getNickname());
					if(basePOIComment.getDate() != null) {
						poiDetailCommentsDto.setDate(DateUtil.getDiscoveryTimeFormatter(basePOIComment.getDate()));
					}
					poiDetailCommentsDto.setText(basePOIComment.getText());
					poiDetailCommentsDto.setRating(basePOIComment.getRating());
					poiDetailCommentsDto.setTitle(basePOIComment.getTitle());
					poiDetailCommentsDto.setLanguage(basePOIComment
							.getLanguage());
					poiDetailCommentsDtos.add(poiDetailCommentsDto);
				}
			}
			poiDetailSumDto.setComments(poiDetailCommentsDtos);

			// List<String> openTime = attraction.getOpenTime();

			String openDesc = "营业中";
			poiDetailSumDto.setOpenTimeDesc(openDesc);
			poiDetailSumDto.setOpenTableUrl(attraction.getOpenTableUrl());
			poiDetailSumDto.setOpenDay(0);
			poiDetailSumDto.setFacilities(null);
		}
		return poiDetailSumDto;
	}

	@Override
	public List<POISepcialBaseDto> getAttractionSpotsById(String id) {
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
		}
		return poiSepcialBaseDtos;
	}

	@Override
	public List<POIDetailCommentsDto> getAttractionCommentsById(String id) {
		List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
		Attraction attraction = attractionDao.getAttractionById(id);
		if (attraction != null) {
			List<BasePOIComments> basePOIComments = attraction.getComments();
			if (basePOIComments != null && basePOIComments.size() > 0) {
				for (BasePOIComments basePOIComment : basePOIComments) {
					POIDetailCommentsDto poiDetailCommentsDto = new POIDetailCommentsDto();
					poiDetailCommentsDto.setNickname(basePOIComment.getNickname());
					if(basePOIComment.getDate() != null) {
						poiDetailCommentsDto.setDate(DateUtil.getDiscoveryTimeFormatter(basePOIComment.getDate()));
					}
					poiDetailCommentsDto.setText(basePOIComment.getText());
					poiDetailCommentsDto.setRating(basePOIComment.getRating());
					poiDetailCommentsDto.setTitle(basePOIComment.getTitle());
					poiDetailCommentsDto.setLanguage(basePOIComment
							.getLanguage());
					poiDetailCommentsDtos.add(poiDetailCommentsDto);
				}
			}
		}
		return poiDetailCommentsDtos;
	}

	@Override
	public SearchNearByBaseDto getAttractionsByCityIdAndCoordination(String cityId, String coordination, String sort,
			Double range, Integer price, String special) {
		
		SearchNearByBaseDto searchNearByBaseDto = new SearchNearByBaseDto();
		List<SearchNearByTagDto> tagList = new ArrayList<SearchNearByTagDto>();
		
		List<SearchNearByListDto> searchNearByListDtos = new ArrayList<SearchNearByListDto>();
		List<Attraction> attractions = attractionDao.getAttractionsByCityIdAndCoordination(cityId, coordination, price);
		
		if (attractions != null && attractions.size() > 0) {
			for (Attraction attraction : attractions) {
				SearchNearByListDto searchNearByListDto = new SearchNearByListDto();
				searchNearByListDto.setId(attraction.getId());
				searchNearByListDto.setName(attraction.getName());
				searchNearByListDto.setAddress(attraction.getAddress());
				searchNearByListDto.setCoverImage(attraction.getCoverImage());
				searchNearByListDto.setPriceDesc(attraction.getPriceDesc());
				searchNearByListDto.setPriceLevel(attraction.getPriceLevel());
				
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
							searchNearByListDto.setDistance(distance);
						}
					}
					searchNearByListDto.setLatitude(newLatitude);
					searchNearByListDto.setLongitude(newLongitude);
				}

				searchNearByListDto.setScore(attraction.getRating());
				List<BasePOITag> tags = attraction.getSubTag();
				if (tags != null && tags.size() > 0) {
					List<SearchNearByTagDto> tempTags = new ArrayList<SearchNearByTagDto>();
					for(int i=0; i<tags.size(); i++) {
						SearchNearByTagDto searchNearByTagDto = new SearchNearByTagDto();
						searchNearByTagDto.setId(tags.get(i).getId());
						searchNearByTagDto.setTag(tags.get(i).getTag());
						tempTags.add(searchNearByTagDto);
						if(!tagList.contains(searchNearByTagDto)) {
							tagList.add(searchNearByTagDto);
						}
					}
					searchNearByListDto.setTags(tempTags);
				}
				searchNearByListDtos.add(searchNearByListDto);
			}
		}
		
		if(searchNearByListDtos != null && searchNearByListDtos.size() > 0) {
			
			for(int i=0;i<searchNearByListDtos.size();i++) {
				SearchNearByListDto tempSearch = searchNearByListDtos.get(i);
				List<SearchNearByTagDto> tempTag = tempSearch.getTags();
				List<String> tempTagIds = new ArrayList<String>();
				if(tempTag != null) {
					for(SearchNearByTagDto temp:tempTag) {
						tempTagIds.add(temp.getId());
					}
				}
			    
				Double tempRange = tempSearch.getDistance();
				if(tempRange == null || tempRange > range) {
					searchNearByListDtos.remove(i);
					// List 每 remove 掉一个元素以后,后面的元素都会向前移动,需要把 i 移回来
					i--;
				} else if(!special.split(",")[0].equals("sp")) {
					List<String> specialTags = new ArrayList<String>();
					for(int k=0; k<special.split(",").length; k++) {
						specialTags.add(special.split(",")[k]);
					}
					if(tempTag == null || tempTagIds.size()> 0  && !tempTagIds.containsAll(specialTags)) {
						searchNearByListDtos.remove(i);
						i--;
					}
				}
			}
			
			if(searchNearByListDtos.size() > 0) {
				// 排序
				if (sort.equals("distance")) {
					Collections.sort(searchNearByListDtos,
							new Comparator<SearchNearByListDto>() {
								public int compare(SearchNearByListDto o1, SearchNearByListDto o2) {
									if(o1.getDistance() == null && o2.getDistance() == null) {
										return 0;
									}
									
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
					Collections.sort(searchNearByListDtos,
							new Comparator<SearchNearByListDto>() {
								public int compare(SearchNearByListDto o1, SearchNearByListDto o2) {
									int comparation = o2.getScore().compareTo(o1.getScore());
									if(comparation == 0) {
										if(o1.getDistance() == null && o2.getDistance() == null) {
											return 0;
										}
										
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
		}
		
		searchNearByBaseDto.setTagList(tagList);
		searchNearByBaseDto.setSearches(searchNearByListDtos);
		return searchNearByBaseDto;
	}
}
