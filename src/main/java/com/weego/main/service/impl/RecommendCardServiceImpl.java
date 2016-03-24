package com.weego.main.service.impl;

import com.google.common.base.Strings;
import com.weego.main.constant.RecommendType;
import com.weego.main.dao.*;
import com.weego.main.dto.RecommendCardDto;
import com.weego.main.model.*;
import com.weego.main.service.PolicyService;
import com.weego.main.service.RecommendCardService;
import com.weego.main.util.DateUtil;
import com.weego.main.util.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuniandxx on 16-3-16.
 */
@Service("recommendInfoService")
public class RecommendCardServiceImpl implements RecommendCardService {
    private Logger logger = LogManager.getLogger(RecommendCardServiceImpl.class);

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PolicyMapDao policyMapDao;

    @Autowired
    private AttractionDao attractionDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private ShoppingDao shoppingDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private PgcDao pgcDao;

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<RecommendCardDto> getRecommendCards(String cityId, String coordinate, String time) {
        logger.info("动态推荐卡片查询");
        logger.info("cityId = {}, coordination = {}, time = {}", cityId, coordinate, time);

        List<RecommendCardDto> recommendCardDtoList = new ArrayList<>();

        try {
            List<Policy> matchTimePolicyList = policyService.fiterPolicyByTimeRange(cityId, time);
            List<Policy> policyList = policyService.filterPolicyByDistance(matchTimePolicyList, coordinate);

            if (policyList == null || policyList.size() <= 0) {
                logger.info("没有匹配的推荐策略!!");
                return recommendCardDtoList;
            }

            Set<RecommendType> typeSet = new HashSet<>();
            for (Policy policy : policyList) {
                List<ObjectId> attractionIdList = policy.getPolicyType().getAttractionList();
                getRecommenCard(typeSet, RecommendType.ATTRACTION, attractionIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> restaurantIdList = policy.getPolicyType().getRestaurantList();
                getRecommenCard(typeSet, RecommendType.RESTAURANT, restaurantIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> shoppingIdList = policy.getPolicyType().getShoppingList();
                getRecommenCard(typeSet, RecommendType.SHOPPING, shoppingIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> activityIdList = policy.getPolicyType().getActivityList();
                getRecommenCard(typeSet, RecommendType.ACTIVITY, activityIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> pgcIdList = policy.getPolicyType().getPgcList();
                getRecommenCard(typeSet, RecommendType.PGC, pgcIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> newsIdList = policy.getPolicyType().getNewsList();
                getRecommenCard(typeSet, RecommendType.NEWS, newsIdList, policy.getId(), recommendCardDtoList);
            }
        } catch (Exception e) {
            return null;
        }
        return recommendCardDtoList;
    }

    private void getRecommenCard(Set<RecommendType> typeSet, RecommendType type, List<ObjectId> idList, ObjectId policyId, List<RecommendCardDto> recommendCardDtoList) {
        if (!typeSet.contains(type) && idList != null && idList.size() > 0) {
            Response<RecommendCardDto> response = getRecommendCard(idList, type, policyId);
            if (response.isStatus()) {
                recommendCardDtoList.add(response.getData());
                typeSet.add(type);
            }
        }
    }

    private Response<RecommendCardDto> getRecommendCard(List<ObjectId> contentIdList, RecommendType type, ObjectId policyId) {
        Response<RecommendCardDto> response = new Response<RecommendCardDto>(false, null);

        if (contentIdList == null || contentIdList.size() <= 0) {
            return response;
        }

        if (type.equals(RecommendType.ACTIVITY)) {
            for (ObjectId contentId : contentIdList) {
                Attraction attraction = attractionDao.getAttractionById(contentId.toString());
                response.setStatus(true);
                PolicyMap policyMap = policyMapDao.getPolicyMap(policyId.toString(), type.getType(), contentId.toString());
                RecommendCardDto recommendCardDto = new RecommendCardDto(contentId.toString(),
                        type.getType(), policyMap.getFirstTitle(),
                        policyMap.getSecondTitle(), attraction.getCoverImage(),
                        attraction.getName(), attraction.getBriefIntroduction());
                response.setData(recommendCardDto);
                return response;
            }
        } else if (type.equals(RecommendType.RESTAURANT)) {
            for (ObjectId contentId : contentIdList) {
                Restaurant restaurant = restaurantDao.getRestaurantById(contentId.toString());
                response.setStatus(true);
                PolicyMap policyMap = policyMapDao.getPolicyMap(policyId.toString(), type.getType(), contentId.toString());
                RecommendCardDto recommendCardDto = new RecommendCardDto(contentId.toString(), type.getType(), policyMap.getFirstTitle(),
                        policyMap.getSecondTitle(), restaurant.getCoverImage(),
                        restaurant.getName(), restaurant.getBriefIntroduction());
                response.setData(recommendCardDto);
                return response;
            }
        } else if (type.equals(RecommendType.SHOPPING) || type.equals(RecommendType.SHOPPINGCIRCLE)) {
            for (ObjectId contentId : contentIdList) {
                Shopping shopping = shoppingDao.getShoppingById(contentId.toString());
                response.setStatus(true);
                PolicyMap policyMap = policyMapDao.getPolicyMap(policyId.toString(), type.getType(), contentId.toString());
                RecommendCardDto recommendCardDto = new RecommendCardDto(contentId.toString(), type.getType(), policyMap.getFirstTitle(),
                        policyMap.getSecondTitle(), shopping.getCoverImage(),
                        shopping.getName(), shopping.getBriefIntroduction());
                response.setData(recommendCardDto);
                return response;
            }
        } else if (type.equals(RecommendType.ACTIVITY)) {
            for (ObjectId contentId : contentIdList) {
                Activity activity = activityDao.getSpecifiedCity(contentId.toString());
                response.setStatus(true);

                PolicyMap policyMap = policyMapDao.getPolicyMap(policyId.toString(), type.getType(), contentId.toString());
                RecommendCardDto recommendCardDto = new RecommendCardDto(contentId.toString(), type.getType(), policyMap.getFirstTitle(),
                        policyMap.getSecondTitle(), activity.getCoverImage(),
                        activity.getTitle(), parseActivityTime(activity)); // todo 判断时间
                response.setData(recommendCardDto);
                return response;
            }
        } else if (type.equals(RecommendType.PGC)) {
            for (ObjectId contentId : contentIdList) {
                Pgc pgc = pgcDao.getSpecifiedPgc(contentId.toString());
                response.setStatus(true);
                PolicyMap policyMap = policyMapDao.getPolicyMap(policyId.toString(), type.getType(), contentId.toString());
                RecommendCardDto recommendCardDto = new RecommendCardDto(contentId.toString(), type.getType(), policyMap.getFirstTitle(),
                        policyMap.getSecondTitle(), pgc.getCoverImage(),
                        "", pgc.getIntroduction());
                response.setData(recommendCardDto);
            }
        } else if (type.equals(RecommendType.NEWS)) {
            for (ObjectId contentId : contentIdList) {
                News news = newsDao.getNewsById(contentId.toHexString());
                response.setStatus(true);
                PolicyMap policyMap = policyMapDao.getPolicyMap(policyId.toString(), type.getType(), contentId.toString());
                String coverImage = news.getNewsContentList().get(0).getImage();
                String desc = "";
                if (!Strings.isNullOrEmpty(news.getLeadText())) {
                    desc = news.getLeadText();
                } else {
                    desc = news.getNewsContentList().get(0).getText();
                }
                RecommendCardDto recommendCardDto = new RecommendCardDto(contentId.toString(), type.getType(), policyMap.getFirstTitle(),
                        policyMap.getSecondTitle(), coverImage,
                        "", desc);
                response.setData(recommendCardDto);
                return response;
            }
        }
        return response;
    }

    private String parseActivityTime(Activity activity) {
        DateTime openTime = new DateTime(activity.getOpenTime());
        DateTime closeTime = new DateTime(activity.getCloseTime());

        if (closeTime.getYear() == openTime.getYear()) {
            return DateUtil.formatSlantyyyyMMdd(activity.getOpenTime()) + "-" + DateUtil.formatSlantMMdd(activity.getCloseTime());
        } else {
            return DateUtil.formatSlantyyyyMMdd(activity.getOpenTime()) + "-" + DateUtil.formatSlantyyyyMMdd(activity.getCloseTime());
        }
    }
}
