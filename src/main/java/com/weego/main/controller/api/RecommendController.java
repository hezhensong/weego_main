package com.weego.main.controller.api;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.RecommendCardDto;
import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.dto.ResponseDto;
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

    //动态推荐列表
    @RequestMapping(value = "/recommendation/history", method = RequestMethod.GET)
    public ResponseDto<RecommendHistoryDto> getHistory(@RequestParam("cityId") String cityId,
                                                       @RequestParam("userId") String userId) {
        logger.info("开始动态推荐列表查询");
        logger.info("cityId = {}, userId = {}", cityId, userId);

        ResponseDto<RecommendHistoryDto> responseDto = new ResponseDto<RecommendHistoryDto>();
        RecommendHistoryDto recommendHistoryDto = recommendHistoryService.getRecommendHistory(cityId, userId);

        if (recommendHistoryDto != null) {
            responseDto.setData(recommendHistoryDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;
    }

    //动态推荐卡片
    @RequestMapping(value = "/recommendation/card", method = RequestMethod.GET)
    public ResponseDto<List<RecommendCardDto>> getCard(@RequestParam("cityId") String cityId,
                                                       @RequestParam("userId") String userId,
                                                       @RequestParam("coordinate") String coordinate,
                                                       @RequestParam("time") String time) {
        logger.info("开始动态推荐卡片查询");
        logger.info("cityId = {}, userId = {}, coordinate = {}, time = {}", cityId, userId, coordinate, time);

        ResponseDto<List<RecommendCardDto>> responseDto = new ResponseDto<List<RecommendCardDto>>();
        List<RecommendCardDto> dataList = recommendInfoService.getRecommendCards(cityId, userId, coordinate, time);

        if (dataList != null) {
            responseDto.setData(dataList);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;
    }
}
