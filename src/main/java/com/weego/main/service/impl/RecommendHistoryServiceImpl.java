package com.weego.main.service.impl;

import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.service.RecommendHistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by liuniandxx on 16-3-23.
 */
@Service("recommendHisService")
public class RecommendHistoryServiceImpl implements RecommendHistoryService {

    private Logger logger = LogManager.getLogger(RecommendHistoryServiceImpl.class);

    @Override
    public RecommendHistoryDto getRecommendHistory(String cityId, String userId) {
        logger.info("cityId = {}, userId = {}", cityId, userId);

        RecommendHistoryDto historyDto = new RecommendHistoryDto();
        try {

//            historyDto.setCityId(cityId);
//
//            logger.info("查询城市信息, cityId = {}", cityId);
//            City city = cityDao.getSpecifiedCity(cityId);
//            historyDto.setCityName(city.getName());
//
//            logger.info("----查询当天的推荐记录----");
//            Date today = DateUtil.yyyyMMdd(new Date());
//            logger.info("today = {}", DateUtil.formatyyyyMMdd(today));
//            List<RecommendInfo> todayRecommends = recommendInfoDao.getRecomendsSpecifyDay(cityId, today);
//            List<RecommendHistoryContentDto> todayDtos = covertToDto(todayRecommends, today);
//            logger.info("----查询当天推荐记录结束----");
//
//            logger.info("----查询昨天推荐记录----");
//            Date yesterday = DateUtil.afterNDays(today, -1);
//            logger.info("yesterday = {}", DateUtil.formatyyyyMMdd(yesterday));
//            List<RecommendInfo> yesterdayRecommends = recommendInfoDao.getRecomendsSpecifyDay(cityId, yesterday);
//            List<RecommendHistoryContentDto> yesterdayDtos = covertToDto(yesterdayRecommends, yesterday);
//            logger.info("----查询昨天推荐记录结束----");
//
//            logger.info("----查询前天推荐记录----");
//            Date dayBeforYest = DateUtil.afterNDays(today, -2);
//            logger.info("dayBeforeYest = {}", DateUtil.formatyyyyMMdd(dayBeforYest));
//            List<RecommendInfo> dayBeforeYestRecommends = recommendInfoDao.getRecomendsSpecifyDay(cityId, dayBeforYest);
//            List<RecommendHistoryContentDto> dayBeforeYestDtos = covertToDto(dayBeforeYestRecommends, dayBeforYest);
//            logger.info("----查询前天推荐记录结束----");
//
//            historyDto.setToday(todayDtos);
//            historyDto.setYesterday(yesterdayDtos);
//            historyDto.setDayBeforeYest(dayBeforeYestDtos);
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
