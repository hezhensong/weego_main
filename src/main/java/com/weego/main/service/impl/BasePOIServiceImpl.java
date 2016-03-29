package com.weego.main.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.weego.main.dto.POIBaseDto;
import com.weego.main.dto.POIDetailCommentsDto;
import com.weego.main.dto.POIDetailSumDto;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.dto.POITranslationBaseDto;
import com.weego.main.service.AttractionService;
import com.weego.main.service.BasePOIService;
import com.weego.main.service.BrandService;
import com.weego.main.service.DishService;
import com.weego.main.service.RestaurantService;
import com.weego.main.service.ShoppingService;
import com.weego.main.service.SpotService;
import com.weego.main.util.BaiDuTranslateUtil;

@Service("basePOIService")
public class BasePOIServiceImpl implements BasePOIService {

	private static Logger logger = LogManager
			.getLogger(BasePOIServiceImpl.class);

	@Autowired
	AttractionService attractionService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	ShoppingService shoppingService;
	@Autowired
	SpotService spotService;
	@Autowired
	DishService dishService;
	@Autowired
	BrandService brandService;

	@Override
	public List<POIBaseDto> getPOIsByCityId(String cityId, Integer type,
			String labelId, Integer page, Integer count) {
		
		try {
			if (type == 0) {
				return attractionService.getAttractionsByCityId(cityId, labelId,
						page, count);
			} else if (type == 1) {
				return restaurantService.getRestaurantsByCityId(cityId, labelId,
						page, count);
			} else if (type == 2) {
				return shoppingService.getShoppingByCityId(cityId, labelId, page,
						count);
			}
		} catch (Exception e) {
			logger.info("POI 列表接口有误!");
			e.printStackTrace();
			return null;
		} 
		
		return null;
	}

	@Override
	public POIDetailSumDto getPOIDetailById(String id, Integer type,
			String coordination) {
		try {
			if (type == 0) {
				return attractionService.getAttractionById(id, coordination);
			} else if (type == 1) {
				return restaurantService.getRestaurantById(id, coordination);
			} else if (type == 2) {
				return shoppingService.getShoppingById(id, coordination);
			}
		} catch (Exception e) {
			logger.info("POI 详情页接口有误!");
			e.printStackTrace();
			return null;
		} 
		return null;
	}

	@Override
	public List<POISepcialBaseDto> getPOISpecialById(String id, Integer type) {
		try {
			if (type == 0) {
				return attractionService.getAttractionSpotsById(id);
			} else if (type == 1) {
				return restaurantService.getRestaurantDishesById(id);
			} else if (type == 2) {
				return shoppingService.getShoppingBrandsById(id);
			}
		} catch (Exception e) {
			logger.info("POI 特色列表接口有误!");
			e.printStackTrace();
			return null;
		} 
		return null;
	}

	@Override
	public POISepcialBaseDto getPOISpecialDetailById(String specialId,
			Integer type) {
		try {
			if (type == 0) {
				return spotService.getSpotById(specialId);
			} else if (type == 1) {
				return dishService.getDishById(specialId);
			} else if (type == 2) {
				return brandService.getBrandById(specialId);
			}
		} catch (Exception e) {
			logger.info("POI 特色详情接口有误!");
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public List<POIDetailCommentsDto> getPOICommentsById(String id, Integer type) {
		try {
			if (type == 0) {
				return attractionService.getAttractionCommentsById(id);
			} else if (type == 1) {
				return restaurantService.getRestaurantCommentsById(id);
			} else if (type == 2) {
				return shoppingService.getShoppingCommentsById(id);
			}
		} catch (Exception e) {
			logger.info("POI 评论列表接口有误!");
			e.printStackTrace();
			return null;
		} 
		return null;
	}

	@Override
	public POITranslationBaseDto getPOITranslation(String content, String from,
			String to) {
		POITranslationBaseDto poiTranslationBaseDto = new POITranslationBaseDto();
		try {
			String translation = BaiDuTranslateUtil.translate(content, from, to);
			poiTranslationBaseDto.setOrigin(content);
			poiTranslationBaseDto.setTranslation(translation);
		} catch (Exception e) {
			logger.info("百度翻译接口有误!");
			e.printStackTrace();
			return null;
		}
		return poiTranslationBaseDto;
	}

	@Override
	public ModelAndView getPOIDetail(String id, Integer type) {

		ModelAndView mv = null;
		POIDetailSumDto poiDetailSumDto = new POIDetailSumDto();
		if (type == 0) {
			mv = new ModelAndView("POIspot");
			poiDetailSumDto = attractionService.getAttractionById(id,
					"-73.9991637,40.7536854");
		} else if (type == 1) {
			mv = new ModelAndView("POIfood");
			poiDetailSumDto = restaurantService.getRestaurantById(id,
					"-73.9991637,40.7536854");
		} else if (type == 2) {
			mv = new ModelAndView("POIshop");
			poiDetailSumDto = shoppingService.getShoppingById(id,
					"-73.9991637,40.7536854");
		} else {
			logger.info("type 参数值有误");
			mv = null;
			poiDetailSumDto = null;
		}

		if (poiDetailSumDto != null) {
				mv.addObject("type", poiDetailSumDto.getType());
				mv.addObject("coverimage", poiDetailSumDto.getCoverImage());
				mv.addObject("title", poiDetailSumDto.getName());
				mv.addObject("english_title", poiDetailSumDto.getNameEn());
				mv.addObject("reviews", poiDetailSumDto.getRating());
				mv.addObject("foreword", poiDetailSumDto.getBriefIntroduction());
				// 需要循环的tag
				mv.addObject("tags", poiDetailSumDto.getTag());

				mv.addObject("breif", poiDetailSumDto.getIntroduction());
				mv.addObject("tips", poiDetailSumDto.getTips());

				// 菜品推荐循环遍历
				mv.addObject("recommends", poiDetailSumDto.getSpecial());

				mv.addObject("commentFrom", poiDetailSumDto.getCommentFrom());
				// 评论
				if (poiDetailSumDto.getComments().size() > 0) {
					mv.addObject("comments", poiDetailSumDto.getComments().get(0));
				} else {
					mv.addObject("comments", "null");
				}

				// 后面一段
				mv.addObject("price", poiDetailSumDto.getPriceDesc());
				mv.addObject("phone", poiDetailSumDto.getTel());
				mv.addObject("web", poiDetailSumDto.getWebsite());
				// 营业时间是列表形式的
				mv.addObject("times", poiDetailSumDto.getOpenTime());
				System.out.println("看看times是否为空"+poiDetailSumDto.getOpenTime());
				// 设施，需要循环遍历，传的是一个对象，里面的值是true or false
				mv.addObject("facilitie", poiDetailSumDto.getFacilities());

				return mv;
			} else {
				logger.info("通过id找不到相应的POI");
				return null;
			}
	}

}
