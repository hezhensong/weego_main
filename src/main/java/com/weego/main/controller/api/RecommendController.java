package com.weego.main.controller.api;

import com.weego.main.dto.RecommendCardDto;
import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.dto.RecommendInfoDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.service.RecommendInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

/**
 * Created by liuniandxx on 16-3-14.
 */
@RestController
@RequestMapping("/api/v3/city")
public class RecommendController {
    private Logger logger = LogManager.getLogger(RecommendController.class);

    @Autowired
    private RecommendInfoService recommendInfoService;

    //动态推荐列表
    @RequestMapping(value="/recommendation/history", method = RequestMethod.GET)
    public ResponseDto<RecommendHistoryDto> getHistory(@RequestParam("cityId") String cityId) {
        logger.info("开始动态推荐列表查询");
        logger.info("cityId = {}", cityId);

        ResponseDto<RecommendHistoryDto> responseDto = new ResponseDto<RecommendHistoryDto>();
        RecommendHistoryDto recommendHistoryDto = recommendInfoService.getRecommendHistory(cityId);

        responseDto.setData(recommendHistoryDto);
        return responseDto;
    }

    //动态推荐卡片
    @RequestMapping(value="/recommendation/card", method = RequestMethod.GET)
    public RecommendCardDto getCard(@RequestParam("cityId") String cityId,
                                    @RequestParam("coordinate") String coordinate,
                                    @RequestParam("time") String time) {
        logger.info("开始动态推荐卡片查询");
        logger.info("cityId = {}, coordinate = {}, time = {}", cityId, coordinate, time);

        return recommendInfoService.getRecommendCards(cityId, coordinate, time);
    }
}
