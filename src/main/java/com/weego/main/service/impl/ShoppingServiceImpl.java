package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.ShoppingDao;
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
import com.weego.main.dto.POISpecialDetailDto;
import com.weego.main.dto.POISpecialDto;
import com.weego.main.dto.SearchNearByBaseDto;
import com.weego.main.dto.SearchNearByDto;
import com.weego.main.model.BasePOIActivities;
import com.weego.main.model.BasePOIComments;
import com.weego.main.model.BasePOITag;
import com.weego.main.model.Shopping;
import com.weego.main.model.ShoppingBrand;
import com.weego.main.service.ShoppingService;
import com.weego.main.util.DistanceUtil;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

	private static Logger logger = LogManager.getLogger(ShoppingServiceImpl.class);
	
	@Autowired
	ShoppingDao shoppingDao;

	@Override
	public POIListDto getShoppingsByCityId(String cityId, String labelId) {
		POIListDto poiListDto = new POIListDto();
		List<POIBaseDto> poiBaseDtos = new ArrayList<POIBaseDto>();
		List<Shopping> shoppings = shoppingDao.getShoppingsByCityId(cityId,labelId);
		if (shoppings != null && shoppings.size() > 0) {
			for (Shopping shopping : shoppings) {
				POIBaseDto poiBaseDto = new POIBaseDto();
				poiBaseDto.setCardId(shopping.getId());
				poiBaseDto.setBrief(shopping.getBriefIntroduction());
				poiBaseDto.setCoverImage(shopping.getCoverImage());
				List<BasePOITag> tags = shopping.getSubTag();
				if(tags != null && tags.size() > 0) {
					poiBaseDto.setTag(shopping.getSubTag().get(0).getTag());
				}
				poiBaseDto.setTitle(shopping.getName());
				poiBaseDtos.add(poiBaseDto);
			}
		}
		poiListDto.setData(poiBaseDtos);
		return poiListDto;
	}

	@Override
	public POIDetailDto getShoppingById(String id) {
		POIDetailDto poiDetailDto = new POIDetailDto();
		POIDetailSumDto poiDetailSumDto = new POIDetailSumDto();
		Shopping shopping = shoppingDao.getShoppingById(id);
		if (shopping != null) {
			poiDetailSumDto.setId(shopping.getId());
			poiDetailSumDto.setType(2);
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

			String coordination = shopping.getCoordination();
			if (coordination != null && coordination.split(",").length >= 2) {
				String latitude = coordination.split(",")[1];
				String longitude = coordination.split(",")[0];
				poiDetailSumDto.setLatitude(latitude);
				poiDetailSumDto.setLongitude(longitude);
			}

			poiDetailSumDto.setImage(shopping.getImage());
			poiDetailSumDto.setCoverImage(shopping.getCoverImage());
			poiDetailSumDto.setOpenTime(shopping.getOpenTime());
			poiDetailSumDto.setPriceDesc(shopping.getPriceDesc());
			poiDetailSumDto.setRating(shopping.getRating());

			List<POIDetailSpecialDto> poiDetailSpecialDtos = new ArrayList<POIDetailSpecialDto>();
			List<ShoppingBrand> shoppingBrands = shopping.getShoppingBrands();
			if (shoppingBrands != null && shoppingBrands.size() > 0) {
				for (ShoppingBrand shoppingBrand : shoppingBrands) {
					POIDetailSpecialDto poiDetailSpecialDto = new POIDetailSpecialDto();
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
					poiDetailActivitiesDto.setActivityId(basePOIActivity
							.getId());
					poiDetailActivitiesDto.setTitle(basePOIActivity.getTitle());
					// not finished

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
			poiDetailSumDto.setOpenTableUrl(shopping.getOpenTableUrl());
			poiDetailSumDto.setOpenDay(0);

			poiDetailSumDto.setFacilities(null);
			poiDetailDto.setData(poiDetailSumDto);
		}
		return poiDetailDto;
	}

	@Override
	public POISpecialDto getShoppingBrandsById(String id) {
		POISpecialDto poiSpecialDto = new POISpecialDto();
		List<POISepcialBaseDto> poiSepcialBaseDtos = new ArrayList<POISepcialBaseDto>();
		Shopping shopping = shoppingDao.getShoppingById(id);
		List<ShoppingBrand> shoppingBrands = new ArrayList<ShoppingBrand>(); 
		if (shopping != null) {
			shoppingBrands = shopping.getShoppingBrands();
			if (shoppingBrands != null && shoppingBrands.size() > 0) {
				for (ShoppingBrand shoppingBrand : shoppingBrands) {
					POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
					poiSepcialBaseDto.setSpecialId(null);
					poiSepcialBaseDto.setCoverImage(shoppingBrand.getCoverImage());
					poiSepcialBaseDto.setTag(shoppingBrand.getTag());
					poiSepcialBaseDto.setTitle(shoppingBrand.getTitle());
					poiSepcialBaseDto.setDesc(shoppingBrand.getDesc());
					poiSepcialBaseDtos.add(poiSepcialBaseDto);
				}
			}
			poiSpecialDto.setData(poiSepcialBaseDtos);
		}
		return poiSpecialDto;
	}

	@Override
	public POISpecialDetailDto getShoppingBrandDetail(String specialId) {
		POISpecialDetailDto poiSpecialDetailDto = new POISpecialDetailDto();
		POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();

		// 假数据
		poiSepcialBaseDto.setSpecialId(null);
		poiSepcialBaseDto.setCoverImage("56595a752a3885bc3700017e.jpeg");
		poiSepcialBaseDto.setTitle("Stuart Weitzman");
		poiSepcialBaseDto.setTag("good");
		poiSepcialBaseDto.setDesc("Beautiful place");
		
		poiSpecialDetailDto.setData(poiSepcialBaseDto);
		return poiSpecialDetailDto;
	}

	@Override
	public POICommentsDto getShoppingCommentsById(String id) {
		POICommentsDto poiCommentsDto = new POICommentsDto();
		List<POIDetailCommentsDto> poiDetailCommentsDtos = new ArrayList<POIDetailCommentsDto>();
		Shopping shopping = shoppingDao.getShoppingById(id);
		if(shopping != null) {
			List<BasePOIComments> basePOIComments = shopping.getComments();
			if(basePOIComments != null && basePOIComments.size() > 0) {
				for(BasePOIComments basePOIComment:basePOIComments) {
					POIDetailCommentsDto poiDetailCommentsDto = new POIDetailCommentsDto();
					poiDetailCommentsDto.setNickname(basePOIComment.getNickname());
					poiDetailCommentsDto.setDate(basePOIComment.getDate());
					poiDetailCommentsDto.setText(basePOIComment.getText());
					poiDetailCommentsDto.setRating(basePOIComment.getRating());
					poiDetailCommentsDto.setTitle(basePOIComment.getTitle());
					poiDetailCommentsDto.setLanguage(basePOIComment.getLanguage());
					poiDetailCommentsDtos.add(poiDetailCommentsDto);
				}
			}
			poiCommentsDto.setData(poiDetailCommentsDtos);	
		}
		return poiCommentsDto;
	}

	@Override
	public SearchNearByDto getShoppingsByCityIdAndCoordination(String cityId, String coordination) {
		SearchNearByDto searchNearByDto = new SearchNearByDto();
		List<SearchNearByBaseDto> searchNearByBaseDtos = new ArrayList<SearchNearByBaseDto>();
		List<Shopping> shoppings = shoppingDao.getShoppingsByCityIdAndCoordination(cityId, coordination);
		
		if(shoppings != null && shoppings.size() > 0) {
			for(Shopping shopping : shoppings) {
				SearchNearByBaseDto searchNearByBaseDto = new SearchNearByBaseDto();
				searchNearByBaseDto.setId(shopping.getId());
				searchNearByBaseDto.setName(shopping.getName());
				searchNearByBaseDto.setAddress(shopping.getAddress());
				searchNearByBaseDto.setCoverImage(shopping.getCoverImage());

				String newCoordination = shopping.getCoordination();
				if (newCoordination != null && newCoordination.split(",").length >= 2) {
					String newLongitude = newCoordination.split(",")[0];
					String newLatitude = newCoordination.split(",")[1];

					if (!coordination.contains(",")) {
						logger.info("coordination 参数值有误");
					} else {
						if (coordination.split(",").length >= 2) {
							String longitude = coordination.split(",")[0];
							String latitude = coordination.split(",")[1];
							String distance = DistanceUtil.getDistance(newLongitude, newLatitude, longitude, latitude);
							searchNearByBaseDto.setDistance(Double.valueOf(distance));
						}
					}

					searchNearByBaseDto.setLatitude(newLatitude);
					searchNearByBaseDto.setLongitude(newLongitude);
				}
				
				searchNearByBaseDto.setScore(shopping.getRating());
				List<BasePOITag> tags = shopping.getSubTag();
				if (tags != null && tags.size() > 0) {
					searchNearByBaseDto.setTag(shopping.getSubTag().get(0).getTag());
				}
				searchNearByBaseDtos.add(searchNearByBaseDto);
			}
		}
		searchNearByDto.setData(searchNearByBaseDtos);
		return searchNearByDto;
	}
}
