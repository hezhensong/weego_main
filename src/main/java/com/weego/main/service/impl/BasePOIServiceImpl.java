package com.weego.main.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static Logger logger = LogManager.getLogger(BasePOIServiceImpl.class);

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
    public List<POIBaseDto> getPOIsByCityId(String cityId, Integer type, String labelId, Integer page, Integer count) {
        if (type == 0) {
            return attractionService.getAttractionsByCityId(cityId, labelId, page, count);
        } else if (type == 1) {
            return restaurantService.getRestaurantsByCityId(cityId, labelId, page, count);
        } else if (type == 2) {
            return shoppingService.getShoppingByCityId(cityId, labelId, page, count);
        } else {
            logger.info("type 参数值有误");
            return null;
        }
    }

    @Override
    public POIDetailSumDto getPOIDetailById(String id, Integer type, String coordination) {
        if (type == 0) {
            return attractionService.getAttractionById(id, coordination);
        } else if (type == 1) {
            return restaurantService.getRestaurantById(id, coordination);
        } else if (type == 2) {
            return shoppingService.getShoppingById(id, coordination);
        } else {
            logger.info("type 参数值有误");
            return null;
        }
    }

    @Override
    public List<POISepcialBaseDto> getPOISpecialById(String id, Integer type) {
        if (type == 0) {
            return attractionService.getAttractionSpotsById(id);
        } else if (type == 1) {
            return restaurantService.getRestaurantDishesById(id);
        } else if (type == 2) {
            return shoppingService.getShoppingBrandsById(id);
        } else {
            logger.info("type 参数值有误");
            return null;
        }
    }

    @Override
    public POISepcialBaseDto getPOISpecialDetailById(String specialId, Integer type) {
        if (type == 0) {
            return spotService.getSpotById(specialId);
        } else if (type == 1) {
            return dishService.getDishById(specialId);
        } else if (type == 2) {
            return brandService.getBrandById(specialId);
        } else {
            logger.info("type 参数值有误");
            return null;
        }
    }

    @Override
    public List<POIDetailCommentsDto> getPOICommentsById(String id, Integer type) {
        if (type == 0) {
            return attractionService.getAttractionCommentsById(id);
        } else if (type == 1) {
            return restaurantService.getRestaurantCommentsById(id);
        } else if (type == 2) {
            return shoppingService.getShoppingCommentsById(id);
        } else {
            logger.info("type 参数值有误");
            return null;
        }
    }

    @Override
    public POITranslationBaseDto getPOITranslation(String content, String from, String to) {
        POITranslationBaseDto poiTranslationBaseDto = new POITranslationBaseDto();
        try {
            String translation = BaiDuTranslateUtil.translate(content, from, to);
            poiTranslationBaseDto.setOrigin(content);
            poiTranslationBaseDto.setTranslation(translation);
        } catch (Exception e) {
            logger.info("百度翻译出错了!");
            e.printStackTrace();
        }
        return poiTranslationBaseDto;
    }

   /* @Override
    public ModelAndView getPOIDetail(String id, Integer type) {

        ModelAndView mv = null;
        POIDetailDto detailDto = new POIDetailDto();
        if (type == 0) {
             mv = new ModelAndView("POIspot");
            detailDto = attractionService.getAttractionById(id, "-73.9991637,40.7536854");
        } else if (type == 1) {
            mv = new ModelAndView("POIfood");
            detailDto = restaurantService.getRestaurantById(id, "-73.9991637,40.7536854");
        } else if (type == 2) {
            mv = new ModelAndView("POIshop");
            detailDto = shoppingService.getShoppingById(id, "-73.9991637,40.7536854");
        } else {
            logger.info("type 参数值有误");
            mv=null;
            detailDto= null;
        }

        if (detailDto != null) {
            POIDetailSumDto detailSum = detailDto.getData();
            if(detailSum!=null){
                mv.addObject("type",detailSum.getType());
                mv.addObject("coverimage",detailSum.getCoverImage());
                mv.addObject("title",detailSum.getName());
                mv.addObject("english_title",detailSum.getNameEn());
                mv.addObject("reviews",detailSum.getRating());
                mv.addObject("foreword",detailSum.getBriefIntroduction());
                //需要循环的tag
                mv.addObject("tags",detailSum.getTag());
                
                mv.addObject("breif",detailSum.getIntroduction());
                mv.addObject("tips",detailSum.getTips());

                //菜品推荐循环遍历
                mv.addObject("recommends",detailSum.getSpecial());
                
                mv.addObject("commentFrom",detailSum.getCommentFrom());
                //评论
                if(detailSum.getComments().size()>0){
                    mv.addObject("comments",detailSum.getComments().get(0));
                }else{
                    mv.addObject("comments","null");
                }
               
                
                //后面一段
                mv.addObject("price",detailSum.getPriceDesc());
                mv.addObject("phone",detailSum.getTel());
                mv.addObject("web",detailSum.getWebsite());
                //营业时间是列表形式的
                mv.addObject("times",detailSum.getOpenTime());
                //设施，需要循环遍历，传的是一个对象，里面的值是true or false
                mv.addObject("facilitie",detailSum.getFacilities());
                
                
                return mv;
            }else{
                return null;
            }

        } else {
            logger.info("通过id找不到相应的POI");
            return null;
        }

    }*/

}
