package com.weego.main.controller;

import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.model.RecommendDynamic;
import com.weego.main.service.RecommendDynamicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-14.
 */
@RestController
@RequestMapping("/api/v3/city")
public class RecommendController {
    private Logger logger = LogManager.getLogger(RecommendController.class);

    @Autowired
    private RecommendDynamicService recommendDynamicService;

    //动态推荐列表
    @RequestMapping(value="/recommendation/history/{cityId}",
                        method= RequestMethod.GET)
    public List<RecommendHistoryDto> getHistory(@PathVariable("cityId") String cityId) {
        logger.info("开始动态推荐列表查询");
        logger.info("cityId = {}", cityId);

        List<RecommendHistoryDto> recommendHistoryDtos = new ArrayList<RecommendHistoryDto>();
        return recommendHistoryDtos;
    }

    //动态推荐卡片
    @RequestMapping(value="/recommendation/card/{cityId}/{coordinate}/{time}",
                        method = RequestMethod.GET)
    public void getCard(@PathVariable("cityId") String cityId,
                        @PathVariable("coordinate") String coordinate,
                        @PathVariable("time") Date time) {

    }

    //测试mongodb
    @RequestMapping("/recommendation/test/{cityId}")
    public List<RecommendDynamic> getSpecifyRecommendDynamic(
                        @PathVariable("cityId") String cityId) {
        logger.info("cityId = {}", cityId);
        logger.info("查询collections-> RecommendDynamic 成功!");
        return recommendDynamicService.getSpecityRecommendDynamic(cityId);
    }


}
