package com.weego.main.controller.api;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.*;
import com.weego.main.service.RecommendCardService;
import com.weego.main.service.RecommendHistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-14.
 */
@RestController
@RequestMapping("/api/v3/city")
public class RecommendController {
    private Logger logger = LogManager.getLogger(RecommendController.class);

    @Autowired
    private RecommendCardService recommendInfoService;

    @Autowired
    private RecommendHistoryService recommendHistoryService;

    // 动态推荐列表
    @RequestMapping(value = "/recommendation/history", method = RequestMethod.GET)
    public ResponseDto<RecommendHistoryDto> getHistory(@RequestParam("cityId") String cityId,
                                                       @RequestParam("userId") String userId) {
        logger.info("开始动态推荐列表查询");
        logger.info("cityId = {}, userId = {}", cityId, userId);

        ResponseDto<RecommendHistoryDto> responseDto = new ResponseDto<>();
        RecommendHistoryDto recommendHistoryDto = recommendHistoryService.getRecommendHistory(cityId, userId);

        if (recommendHistoryDto != null) {
            responseDto.setData(recommendHistoryDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;
    }

    // 动态推荐卡片
    @RequestMapping(value = "/recommendation/card", method = RequestMethod.GET)
    public ResponseDto<RecommendCardDto> getCard(@RequestParam("cityId") String cityId,
                                                     @RequestParam("userId") String userId,
                                                     @RequestParam("coordinate") String coordinate,
                                                     @RequestParam("time") String time) {
        logger.info("开始动态推荐卡片查询");
        logger.info("cityId = {}, userId = {}, coordinate = {}, time = {}", cityId, userId, coordinate, time);

        ResponseDto<RecommendCardDto> responseDto = new ResponseDto<>();
        RecommendCardDto recommendCardDto = recommendInfoService.getRecommendCards(cityId, userId, coordinate, time);

        if (recommendCardDto != null) {
            responseDto.setData(recommendCardDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;
    }

    // 新闻详情页
    @RequestMapping(value = "/recommendation/news", method = RequestMethod.GET)
    public ResponseDto<RecommendNewsDetailDto> getNews(@RequestParam("newsId") String newsId) {
        logger.info("newsId = {}", newsId);

        ResponseDto<RecommendNewsDetailDto> responseDto = new ResponseDto<>();

        RecommendNewsDetailDto recommendNewsDetailDto = recommendInfoService.getRecommendNewsById(newsId);
        if (recommendNewsDetailDto == null) {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
            logger.fatal("新闻不存在 newsId=[{}]", newsId);

        } else {
            responseDto.setData(recommendNewsDetailDto);
        }

        return responseDto;
    }
}
