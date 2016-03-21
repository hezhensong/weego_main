package com.weego.main.service.impl;

import com.weego.main.constant.RecommendType;
import com.weego.main.dao.*;
import com.weego.main.dto.*;
import com.weego.main.model.*;
import com.weego.main.service.RecommendInfoService;
import com.weego.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
@Service("recommendInfoService")
public class RecommendInfoServiceImpl implements RecommendInfoService {
    private Logger logger = LogManager.getLogger(RecommendInfoServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AttractionDao attractionDao;

    @Autowired
    private RecommendInfoDao recommendInfoDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private ActivityDao activityDao;

    @Override
    public RecommendHistoryDto getRecommendHistory(String cityId) {
        logger.info("cityId = {}", cityId);

        RecommendHistoryDto historyDto = new RecommendHistoryDto();

        historyDto.setCityId(cityId);

        logger.info("查询城市信息, cityId = {}", cityId);
        City city = cityDao.getSpecifiedCity(cityId);
        historyDto.setCityName(city.getName());

        logger.info("----查询当天的推荐记录----");
        Date today = DateUtil.yyyyMMdd(new Date());
        logger.info("today = {}", DateUtil.formatyyyyMMdd(today));
        List<RecommendInfo> todayRecommends = recommendInfoDao.getRecomendsSpecifyDay(cityId, today);
        List<RecommendInfoDto> todayDtos = covertToDto(todayRecommends, today);
        logger.info("----查询当天推荐记录结束----");

        logger.info("----查询昨天推荐记录----");
        Date yesterday = DateUtil.afterNDays(today, -1);
        logger.info("yesterday = {}", DateUtil.formatyyyyMMdd(yesterday));
        List<RecommendInfo> yesterdayRecommends = recommendInfoDao.getRecomendsSpecifyDay(cityId, yesterday);
        List<RecommendInfoDto> yesterdayDtos = covertToDto(yesterdayRecommends, yesterday);
        logger.info("----查询昨天推荐记录结束----");

        logger.info("----查询前天推荐记录----");
        Date dayBeforYest = DateUtil.afterNDays(today, -2);
        logger.info("dayBeforeYest = {}", DateUtil.formatyyyyMMdd(dayBeforYest));
        List<RecommendInfo> dayBeforeYestRecommends = recommendInfoDao.getRecomendsSpecifyDay(cityId, dayBeforYest);
        List<RecommendInfoDto> dayBeforeYestDtos = covertToDto(dayBeforeYestRecommends, dayBeforYest);
        logger.info("----查询前天推荐记录结束----");

        historyDto.setToday(todayDtos);
        historyDto.setYesterday(yesterdayDtos);
        historyDto.setDayBeforeYest(dayBeforeYestDtos);

        return historyDto;
    }

    @Override
    public RecommendCardDto getRecommendCards(String cityId, String coordinate, String time) {
        logger.info("cityId = {}, coordinate = {}, time = {}", cityId, coordinate, time);
        RecommendCardDto recommendCardDto = new RecommendCardDto();
        recommendCardDto.setCode(0);
        recommendCardDto.setMessage("success");

        logger.info("----查询指定时间的动态推荐----");
        Date dateTime = DateUtil.yyyyMMddToDate(time);
        List<RecommendInfo> recommendInfos = recommendInfoDao.getRecomendsSpecifyDay(cityId, dateTime);

        recommendCardDto.setData(new ArrayList<BaseCardDto>());
        List<BaseCardDto> datas = recommendCardDto.getData();
        if(recommendInfos != null && recommendInfos.size() > 0) {
            for(RecommendInfo elem : recommendInfos) {
                if (RecommendType.ATTRACTION.getType().equals(elem.getType())) {
                    AttractionCardDto cardDto = new AttractionCardDto();
                    cardDto.setId(elem.getContent().getContentId());
                    cardDto.setType(elem.getType());
                    cardDto.setFirstTitle(elem.getContent().getContentFirst());
                    cardDto.setSecondTitle(elem.getContent().getContentSecond());
                    cardDto.setCoverImage(elem.getContent().getCoverImage());
                    cardDto.setDesc(elem.getContent().getContentDesc());

                    Attraction attraction = attractionDao.getAttractionById(cardDto.getId());
                    if(attraction != null) {
                        cardDto.setAttractionName(attraction.getName());
                    }

                    datas.add(cardDto);
                } else if(RecommendType.RESTAURANT.getType().equals(elem.getType())) {
                    RestaurantCardDto cardDto = new RestaurantCardDto();
                    cardDto.setId(elem.getContent().getContentId());
                    cardDto.setType(elem.getType());
                    cardDto.setFirstTitle(elem.getContent().getContentFirst());
                    cardDto.setSecondTitle(elem.getContent().getContentSecond());
                    cardDto.setCoverImage(elem.getContent().getCoverImage());
                    cardDto.setDesc(elem.getContent().getContentDesc());

                    Restaurant restaurant = restaurantDao.getRestaurantById(cardDto.getId());
                    if(restaurant != null) {
                        cardDto.setRestaurantName(restaurant.getName());
                    }
                    datas.add(cardDto);
                } else if(RecommendType.ACTIVITY.getType().equals(elem.getType())) {
                    ActivityCardDto cardDto = new ActivityCardDto();
                    cardDto.setId(elem.getContent().getContentId());
                    cardDto.setType(elem.getType());
                    cardDto.setFirstTitle(elem.getContent().getContentFirst());
                    cardDto.setSecondTitle(elem.getContent().getContentSecond());
                    cardDto.setDesc(elem.getContent().getContentDesc());

                    Activity activity = activityDao.getSpecifiedCity(cardDto.getId());
                    if(activity != null) {
                        Date startDate = activity.getOpenTime();
                        Date endDate = activity.getCloseTime();
                        DateTime startDateTime = new DateTime(startDate);
                        DateTime endDateTime = new DateTime(endDate);
                        logger.info("判断城市活动的开始结束时间是否为同一年");
                        logger.info("startDate = {}， endDate = {}",
                                DateUtil.formatyyyyMMdd(startDate), DateUtil.formatyyyyMMdd(endDate));
                        if (endDateTime.getYear() > startDateTime.getYear()) {
                            cardDto.setActivityTime(DateUtil.formatyyyyMMdd(startDate) +
                                    "-" +
                                    DateUtil.formatyyyyMMdd(endDate));
                        } else {
                            cardDto.setActivityTime(DateUtil.formatyyyyMMdd(startDate) +
                                    "-" +
                                    DateUtil.formatMMdd(endDate));
                        }

                        logger.info("Activity Time: {}", cardDto.getActivityTime());
                    }
                    datas.add(cardDto);
                } else {
                    BaseCardDto cardDto = new BaseCardDto();
                    cardDto.setId(elem.getContent().getContentId());
                    cardDto.setType(elem.getType());
                    cardDto.setFirstTitle(elem.getContent().getContentFirst());
                    cardDto.setSecondTitle(elem.getContent().getContentSecond());
                    cardDto.setCoverImage(elem.getContent().getCoverImage());
                    cardDto.setDesc(elem.getContent().getContentDesc());

                    datas.add(cardDto);
                }
            }
        }
        return recommendCardDto;
    }

    private List<RecommendInfoDto> covertToDto(List<RecommendInfo> recommends, Date date) {
        List<RecommendInfoDto> dtos = new ArrayList<RecommendInfoDto>();
        if(recommends != null && recommends.size() > 0) {
            for(RecommendInfo elem : recommends) {
                RecommendInfoDto dto = new RecommendInfoDto();
                dto.setId(elem.getId().toString());
                dto.setType(elem.getType());
                dto.setTime(date);

                RecommendContent content = elem.getContent();
                RecommendContentDto contentDto = new RecommendContentDto();
                contentDto.setContentFirst(content.getContentFirst());
                contentDto.setContentSecond(content.getContentSecond());
                contentDto.setCoverImage(content.getCoverImage());
                contentDto.setContentTitle(content.getContentTitle());
                contentDto.setContentDesc(content.getContentDesc());
                dto.setContent(contentDto);

                dtos.add(dto);
            }
        }
        return dtos;
    }
}