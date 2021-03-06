package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.ActivityDao;
import com.weego.main.dao.ShoppingDao;
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
import com.weego.main.model.BasePOIActivities;
import com.weego.main.model.BasePOIComments;
import com.weego.main.model.BasePOIOpenTime;
import com.weego.main.model.BasePOITag;
import com.weego.main.model.Shopping;
import com.weego.main.model.ShoppingBrand;
import com.weego.main.service.ShoppingService;
import com.weego.main.util.ActivityTimeUtil;
import com.weego.main.util.DateUtil;
import com.weego.main.util.DistanceUtil;
import com.weego.main.util.OpenTimeUtil;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

	private static Logger logger = LogManager
			.getLogger(ShoppingServiceImpl.class);

	@Autowired
	ShoppingDao shoppingDao;
	@Autowired
	ActivityDao activityDao;

	@Override
	public List<POIBaseDto> getShoppingByCityId(String cityId, String labelId,
										  Integer page, Integer count) {
		
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		
		try {
			List<Shopping> shoppings = shoppingDao.getShoppingsByCityId(cityId,
					labelId, page, count);
			if (shoppings != null && shoppings.size() > 0) {
				for (Shopping shopping : shoppings) {
					POIBaseDto poiBaseDto = new POIBaseDto();
					poiBaseDto.setCardId(shopping.getId());
					poiBaseDto.setBrief(shopping.getBriefIntroduction());
					poiBaseDto.setCoverImage(shopping.getCoverImage());
					List<BasePOITag> tags = shopping.getSubTag();
					if (tags != null && tags.size() > 0) {
						poiBaseDto.setTag(shopping.getSubTag().get(0).getTag());
					}
					poiBaseDto.setTitle(shopping.getName());
					poiBaseDtos.add(poiBaseDto);
				}
			}
		} catch (Exception e) {
			logger.info("探索城市购物列表接口出错!");
			e.printStackTrace();
		}
		return poiBaseDtos;
	}

	@Override
	public POIDetailSumDto getShoppingById(String id, String coordination) {
		POIDetailSumDto poiDetailSumDto = new POIDetailSumDto();
		
		try {
			Shopping shopping = shoppingDao.getShoppingById(id);
			if (shopping != null) {
				poiDetailSumDto.setId(shopping.getId());
				poiDetailSumDto.setType(shopping.getType());
				poiDetailSumDto.setName(shopping.getName());
				poiDetailSumDto.setNameEn(shopping.getNameEn());
				poiDetailSumDto.setAddress(shopping.getAddress());
				poiDetailSumDto.setTel(shopping.getTel());
				poiDetailSumDto.setWebsite(shopping.getWebsite());
				poiDetailSumDto.setBriefIntroduction(shopping
						.getBriefIntroduction());
				poiDetailSumDto.setIntroduction(shopping.getIntroduction());
				poiDetailSumDto.setCityName(shopping.getCityName());
				poiDetailSumDto.setCityId(shopping.getCityId());

				String newCoordination = shopping.getCoordination();
				if (newCoordination != null && newCoordination.split(",").length >= 2) {
					String newLatitude = newCoordination.split(",")[1];
					String newLongitude = newCoordination.split(",")[0];
					poiDetailSumDto.setLatitude(newLatitude);
					poiDetailSumDto.setLongitude(newLongitude);
					
					if(coordination.split(",").length >= 2) {
						String latitude = coordination.split(",")[1];
						String longitude = coordination.split(",")[0];
						Double distance =  DistanceUtil.getDistance(latitude, longitude, newLatitude, newLongitude);
						if(distance != null) {
							poiDetailSumDto.setDistance(distance);
						} else {
							poiDetailSumDto.setDistance(-1.0);
						}
					} else {
						logger.info("coordination 参数值有误");
					}
				} else {
					poiDetailSumDto.setDistance(-1.0);
					poiDetailSumDto.setLatitude("");
					poiDetailSumDto.setLongitude("");
				}
				
				poiDetailSumDto.setImage(shopping.getImage());
				poiDetailSumDto.setCoverImage(shopping.getCoverImage());
				
				List<BasePOIOpenTime> openTimes = shopping.getOpenTime();
				List<String> openTimeDesc = new ArrayList<String>();
				if(openTimes != null && openTimes.size() > 0) {
					for(BasePOIOpenTime openTime : openTimes) {
						openTimeDesc.add(openTime.getDesc());
					}
					poiDetailSumDto.setOpenTime(OpenTimeUtil.changeTimeDesc(openTimeDesc));
				} else {
					poiDetailSumDto.setOpenTime(new ArrayList<String>());
				}
				
				String openDesc = OpenTimeUtil.getOpenDesc(openTimes);
				poiDetailSumDto.setOpenTimeDesc(openDesc);
				
				poiDetailSumDto.setPriceDesc(shopping.getPriceDesc());
				poiDetailSumDto.setRating(shopping.getRating());

				List<POIDetailSpecialDto> poiDetailSpecialDtos = new ArrayList<POIDetailSpecialDto>();
				List<ShoppingBrand> shoppingBrands = shopping.getShoppingBrands();
				if (shoppingBrands != null && shoppingBrands.size() > 0) {
					for (ShoppingBrand shoppingBrand : shoppingBrands) {
						POIDetailSpecialDto poiDetailSpecialDto = new POIDetailSpecialDto();
						poiDetailSpecialDto.setId(shoppingBrand.getId());
						poiDetailSpecialDto.setAdvice(shoppingBrand.getAdvice());
						poiDetailSpecialDto.setDesc(shoppingBrand.getDesc());
						poiDetailSpecialDto.setTitle(shoppingBrand.getTitle());
						poiDetailSpecialDto.setTag(shoppingBrand.getTag());
						poiDetailSpecialDto.setCoverImage(shoppingBrand
								.getCoverImage());
						poiDetailSpecialDtos.add(poiDetailSpecialDto);
					}
				}
				poiDetailSumDto.setSpecial(poiDetailSpecialDtos);

				List<POIDetailActivitiesDto> poiDetailActivitiesDtos = new ArrayList<POIDetailActivitiesDto>();
				List<BasePOIActivities> basePOIActivities = shopping
						.getActivities();
				if (basePOIActivities != null && basePOIActivities.size() > 0) {
					for (BasePOIActivities basePOIActivity : basePOIActivities) {
						POIDetailActivitiesDto poiDetailActivitiesDto = new POIDetailActivitiesDto();
						poiDetailActivitiesDto.setActivityId(basePOIActivity.getId());
						poiDetailActivitiesDto.setTitle(basePOIActivity.getTitle());

						Activity activity = activityDao.getSpecifiedActivity(basePOIActivity.getId());
						if (activity != null) {
							Date date1 = activity.getOpenTime();
							Date date2 = activity.getCloseTime();
							poiDetailActivitiesDto.setActTime(ActivityTimeUtil.getActivityTimeDesc(date1, date2));
							poiDetailActivitiesDto.setCoverImage(activity.getCoverImage());
							poiDetailActivitiesDto.setDesc(activity.getDescription());
							poiDetailActivitiesDto.setTag("");
						} else {
							poiDetailActivitiesDto.setActTime("");
							poiDetailActivitiesDto.setCoverImage("");
							poiDetailActivitiesDto.setDesc("");
							poiDetailActivitiesDto.setTag("");
						}
						poiDetailActivitiesDtos.add(poiDetailActivitiesDto);
					}
				}
				poiDetailSumDto.setActivities(poiDetailActivitiesDtos);

				List<POIDetailTagDto> poiDetailTagDtos = new ArrayList<POIDetailTagDto>();
				List<BasePOITag> basePOITags = shopping.getSubTag();
				if (basePOITags != null && basePOITags.size() > 0) {
					for (BasePOITag basePOITag : basePOITags) {
						POIDetailTagDto poiDetailTagDto = new POIDetailTagDto();
						poiDetailTagDto.setId(basePOITag.getId());
						poiDetailTagDto.setName(basePOITag.getTag());
						poiDetailTagDtos.add(poiDetailTagDto);
					}
				}
				poiDetailSumDto.setTag(poiDetailTagDtos);

				poiDetailSumDto.setTips(shopping.getTips());
				poiDetailSumDto.setCommentsUrl(shopping.getCommentsUrl());
				poiDetailSumDto.setCommentFrom(shopping.getCommentFrom());

				List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
				List<BasePOIComments> basePOIComments = shopping.getComments();
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
				poiDetailSumDto.setOpenTableUrl(shopping.getOpenTableUrl());
				poiDetailSumDto.setOpenDay(0);
				poiDetailSumDto.setFacilities(new HashMap<Object, Object>());
			}
		} catch (Exception e) {
			logger.info("探索城市购物详情页出错!");
			e.printStackTrace();
		}
		return poiDetailSumDto;
	}

	@Override
	public List<POISepcialBaseDto> getShoppingBrandsById(String id) {
		List<POISepcialBaseDto> poiSepcialBaseDtos = new ArrayList<POISepcialBaseDto>();
		
		try {
			Shopping shopping = shoppingDao.getShoppingById(id);
			List<ShoppingBrand> shoppingBrands = new ArrayList<ShoppingBrand>();
			if (shopping != null) {
				shoppingBrands = shopping.getShoppingBrands();
				if (shoppingBrands != null && shoppingBrands.size() > 0) {
					for (ShoppingBrand shoppingBrand : shoppingBrands) {
						POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
						poiSepcialBaseDto.setSpecialId(shoppingBrand.getId());
						poiSepcialBaseDto.setCoverImage(shoppingBrand
								.getCoverImage());
						poiSepcialBaseDto.setTag(shoppingBrand.getTag());
						poiSepcialBaseDto.setTitle(shoppingBrand.getTitle());
						poiSepcialBaseDto.setDesc(shoppingBrand.getDesc());
						poiSepcialBaseDtos.add(poiSepcialBaseDto);
					}
				}
			}
		} catch (Exception e) {
			logger.info("探索城市购物的品牌接口出错!");
			e.printStackTrace();
		}
		return poiSepcialBaseDtos;
	}

	@Override
	public List<POIDetailCommentsDto> getShoppingCommentsById(String id) {
		List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
		
		try {
			Shopping shopping = shoppingDao.getShoppingById(id);
			if (shopping != null) {
				List<BasePOIComments> basePOIComments = shopping.getComments();
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
		} catch (Exception e) {
			logger.info("探索城市购物的评论列表接口出错!");
			e.printStackTrace();
		}
		return poiDetailCommentsDtos;
	}

	@Override
	public SearchNearByBaseDto getShoppingByCityIdAndCoordination(String cityId, String coordination, String sort,
															  Double range, Integer price, String special) {
		SearchNearByBaseDto searchNearByBaseDto = new SearchNearByBaseDto();
		List<SearchNearByTagDto> tagList = new ArrayList<SearchNearByTagDto>();
		
		try {
			List<SearchNearByListDto> searchNearByListDtos = new ArrayList<SearchNearByListDto>();
			List<Shopping> shoppings = shoppingDao.getShoppingsByCityIdAndCoordination(cityId, coordination, price);
			if (shoppings != null && shoppings.size() > 0) {
				for (Shopping shopping : shoppings) {
					SearchNearByListDto searchNearByListDto = new SearchNearByListDto();
					searchNearByListDto.setId(shopping.getId());
					searchNearByListDto.setName(shopping.getName());
					searchNearByListDto.setAddress(shopping.getAddress());
					searchNearByListDto.setCoverImage(shopping.getCoverImage());
					searchNearByListDto.setPriceDesc(shopping.getPriceDesc());
					searchNearByListDto.setPriceLevel(shopping.getPriceLevel());
					
					String newCoordination = shopping.getCoordination();
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

					searchNearByListDto.setScore(shopping.getRating());
					List<BasePOITag> tags = shopping.getSubTag();
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
		} catch (Exception e) {
			logger.info("搜索周边的购物接口出错!");
			e.printStackTrace();
		}
		return searchNearByBaseDto;
	}
}
