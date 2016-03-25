package com.weego.main.service.impl;

import com.google.common.base.Strings;
import com.weego.main.constant.RecommendType;
import com.weego.main.dao.*;
import com.weego.main.dto.RecommendCardDto;
import com.weego.main.dto.RecommendNewsDetailContentDto;
import com.weego.main.dto.RecommendNewsDetailDto;
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

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RecommendHistoryDao recommendHistoryDao;

    @Override
    public List<RecommendCardDto> getRecommendCards(String cityId, String userId, String coordinate, String time) {
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
                getRecommendCard(typeSet, RecommendType.ATTRACTION, attractionIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> restaurantIdList = policy.getPolicyType().getRestaurantList();
                getRecommendCard(typeSet, RecommendType.RESTAURANT, restaurantIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> shoppingIdList = policy.getPolicyType().getShoppingList();
                getRecommendCard(typeSet, RecommendType.SHOPPING, shoppingIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> activityIdList = policy.getPolicyType().getActivityList();
                getRecommendCard(typeSet, RecommendType.ACTIVITY, activityIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> pgcIdList = policy.getPolicyType().getPgcList();
                getRecommendCard(typeSet, RecommendType.PGC, pgcIdList, policy.getId(), recommendCardDtoList);

                List<ObjectId> newsIdList = policy.getPolicyType().getNewsList();
                getRecommendCard(typeSet, RecommendType.NEWS, newsIdList, policy.getId(), recommendCardDtoList);
            }
        } catch (Exception e) {
            return null;
        }

        insertRecommendsIntoHistory(recommendCardDtoList, cityId, userId);
        return recommendCardDtoList;
    }

    @Override
    public RecommendNewsDetailDto getRecommendNewsById(String newsId) {
        RecommendNewsDetailDto recommendNewsDetailDto = new RecommendNewsDetailDto();

        try {
            News news = newsDao.getNewsById(newsId);
            recommendNewsDetailDto.setLead(news.getLead());
            recommendNewsDetailDto.setLeadText(news.getLeadText());

            List<RecommendNewsDetailContentDto> contentDtoList = new ArrayList<>();
            recommendNewsDetailDto.setContents(contentDtoList);

            for (NewsContent content : news.getNewsContentList()) {
                RecommendNewsDetailContentDto contentDto = new RecommendNewsDetailContentDto();
                contentDto.setTitle(content.getTitle());
                contentDto.setText(content.getText());
                contentDto.setUrl(content.getUrl());
                contentDto.setImage(content.getImage());
                contentDto.setImageDesc(content.getImageDesc());
                contentDto.setSource(content.getSource());
                contentDto.setDate(content.getDate());

                contentDtoList.add(contentDto);
            }

        } catch (Exception e) {
            logger.fatal("新闻详情页获取失败 {}", e.getStackTrace());
            return null;
        }

        return recommendNewsDetailDto;
    }

    private void insertRecommendsIntoHistory(List<RecommendCardDto> recommendCardDtoList, String cityid, String userId) {
        if(recommendCardDtoList == null || recommendCardDtoList.size() <= 0) {
            return;
        }

        City city = cityDao.getSpecifiedCity(cityid);

        RecommendHistory recommendHistory = new RecommendHistory();
        recommendHistory.setCityId(new ObjectId(cityid));
        recommendHistory.setUserId(new ObjectId(userId));
        recommendHistory.setRecommendTime(DateUtil.getyyyyMMddHHmmssSpecifyTimezone(city.getTimezone()));

        List<RecommendContent> recommendContentList = new ArrayList<>();
        for(RecommendCardDto cardDto : recommendCardDtoList) {
            RecommendContent recommendContent = new RecommendContent();
            recommendContent.setType(cardDto.getType());
            recommendContent.setContentId(cardDto.getId());
            recommendContent.setContentFirst(cardDto.getFirstTitle());
            recommendContent.setContentSecond(cardDto.getSecondTitle());
            recommendContentList.add(recommendContent);
        }
        recommendHistory.setRecommendContentList(recommendContentList);
        recommendHistoryDao.saveRecommendHistory(recommendHistory);
    }

    private void getRecommendCard(Set<RecommendType> typeSet, RecommendType type, List<ObjectId> idList, ObjectId policyId, List<RecommendCardDto> recommendCardDtoList) {
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
