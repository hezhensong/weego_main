package com.weego.main.service.impl;

import com.google.common.base.Strings;
import com.weego.main.constant.RecommendType;
import com.weego.main.constant.Timedelta;
import com.weego.main.dao.*;
import com.weego.main.dto.RecommendHistoryContentDto;
import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.model.*;
import com.weego.main.service.RecommendHistoryService;
import com.weego.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
@Service("recommendHisService")
public class RecommendHistoryServiceImpl implements RecommendHistoryService {

    private Logger logger = LogManager.getLogger(RecommendHistoryServiceImpl.class);

    @Autowired
    private RecommendHistoryDao recommendHistoryDao;

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

    @Override
    public RecommendHistoryDto getRecommendHistory(String cityId, String userId) {
        logger.info("查询历史三天的推荐记录");
        logger.info("cityId = {}, userId = {}", cityId, userId);

        RecommendHistoryDto historyDto = new RecommendHistoryDto();
        try {
            City city = cityDao.getSpecifiedCity(cityId);
            String cityTimeZone = city.getTimezone();
            String cityName = city.getName();
            logger.info("cityTimeZone = {}, cityName = {}", cityTimeZone, cityName);

            Date today = DateUtil.getyyyyMMddSpecifyTimezone(cityTimeZone);
            Date yesterday = DateUtil.afterNDays(today, -1);
            Date dayBefroeYest = DateUtil.afterNDays(yesterday, -1);
            logger.info("today = {}, ms = {}", today, today.getTime());

            historyDto.setCityId(cityId);
            historyDto.setCityName(cityName);

            List<RecommendHistory> todayRecommendList = recommendHistoryDao.getRecommendHistoryByTime(cityId, userId, today);
            List<RecommendHistory> yesterdayRecommendList = recommendHistoryDao.getRecommendHistoryByTime(cityId, userId, yesterday);
            List<RecommendHistory> dayBefroeYestRecommendList = recommendHistoryDao.getRecommendHistoryByTime(cityId, userId, dayBefroeYest);

            List<RecommendHistoryContentDto> todayRecommendDtoList = convertToRecommendHistoryDto(todayRecommendList, cityTimeZone);
            List<RecommendHistoryContentDto> yesterdayRecommendDtoList = convertToRecommendHistoryDto(yesterdayRecommendList, cityTimeZone);
            List<RecommendHistoryContentDto> dayBeforeYestDtoList = convertToRecommendHistoryDto(dayBefroeYestRecommendList, cityTimeZone);

            historyDto.setToday(todayRecommendDtoList);
            historyDto.setYesterday(yesterdayRecommendDtoList);
            historyDto.setDayBeforeYest(dayBeforeYestDtoList);
        } catch (Exception e) {
            return null;
        }
        return historyDto;
    }


    private List<RecommendHistoryContentDto>
            convertToRecommendHistoryDto(List<RecommendHistory> recommendHistoryList, String timezone) throws ParseException {
        List<RecommendHistoryContentDto> dtoList = new ArrayList<>();

        if(recommendHistoryList == null || recommendHistoryList.size() <= 0) {
            return dtoList;
        }

        for(RecommendHistory history : recommendHistoryList) {
            List<RecommendContent> recommendContentList = history.getRecommendContentList();
            List<RecommendHistoryContentDto> contentDtoList = new ArrayList<>();
            String time = parseRecomendTime(history.getRecommendTime(), timezone);
            for(RecommendContent content : recommendContentList) {
                RecommendHistoryContentDto contentDto = new RecommendHistoryContentDto();
                contentDto.setType(content.getType());
                contentDto.setTime(time);
                contentDto.setContentId(content.getContentId());
                contentDto.setContentFirst(content.getContentFirst());
                contentDto.setContentSecond(content.getContentSecond());
                getContentDetail(contentDto);
                contentDtoList.add(contentDto);
            }
            dtoList.addAll(contentDtoList);
        }
        return dtoList;
    }

    private String parseRecomendTime(Date time, String cityTimeZone) throws ParseException {
        Date currentTime = DateUtil.getyyyyMMddHHmmssSpecifyTimezone(cityTimeZone);
        logger.info("currentTime = {}", currentTime);

        long timedelta = (currentTime.getTime() - time.getTime()) / 1000;

        if(timedelta > Timedelta.FiveHour.getTimedelta()) {
            return DateUtil.formatColonHM(time);
        } else if(timedelta > Timedelta.OneHour.getTimedelta()) {
            long cntHour = timedelta / Timedelta.OneHour.getTimedelta();
            return "" + cntHour + "小时前";
        } else if(timedelta > Timedelta.OneMinute.getTimedelta()) {
            long cntMinute = timedelta / Timedelta.OneMinute.getTimedelta();
            return "" + cntMinute + "分前";
        } else {
            return "" + timedelta + "秒前";
        }
    }


    private void getContentDetail(RecommendHistoryContentDto contentDto) {
        String contentId = contentDto.getContentId();
        Integer type = contentDto.getType();

        if(RecommendType.ATTRACTION.getType().equals(type)) {
            Attraction attraction = attractionDao.getAttractionById(contentId);
            contentDto.setContentTitle(attraction.getName());
            contentDto.setCoverImage(attraction.getCoverImage());
        } else if(RecommendType.RESTAURANT.getType().equals(type)){
            Restaurant restaurant = restaurantDao.getRestaurantById(contentId);
            contentDto.setCoverImage(restaurant.getCoverImage());
            contentDto.setContentTitle(restaurant.getName());
        } else if(RecommendType.SHOPPING.getType().equals(type) ||
                RecommendType.SHOPPINGCIRCLE.getType().equals(type)) {
            Shopping shopping = shoppingDao.getShoppingById(contentId);
            contentDto.setContentTitle(shopping.getName());
            contentDto.setCoverImage(shopping.getCoverImage());
        } else if(RecommendType.ACTIVITY.getType().equals(type)) {
            Activity activity = activityDao.getSpecifiedCity(contentId);
            contentDto.setContentTitle(activity.getTitle());
            contentDto.setCoverImage(activity.getCoverImage());
        } else if(RecommendType.PGC.getType().equals(type)) {
            Pgc pgc = pgcDao.getSpecifiedPgc(contentId);
            contentDto.setContentTitle(pgc.getTitle());
            contentDto.setCoverImage(pgc.getCoverImage());
        } else if (RecommendType.NEWS.getType().equals(type)){
            News news = newsDao.getNewsById(contentId);
            if(!Strings.isNullOrEmpty(news.getLead())) {
                contentDto.setContentTitle(news.getLead());
            } else {
                contentDto.setContentTitle(news.getNewsContentList().get(0).getTitle());
            }
            contentDto.setCoverImage(news.getNewsContentList().get(0).getImage());
        }
    }


}
