package com.weego.main.controller;

import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.service.RecommendDynamicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value="/recommendation/history",
                        method = RequestMethod.GET)
    public List<RecommendHistoryDto> getHistory(@RequestParam("cityId") String cityId) {
        logger.info("开始动态推荐列表查询");
        logger.info("cityId = {}", cityId);

        // TODO: 16-3-17
        return null;
    }

    //动态推荐卡片
    @RequestMapping(value="/recommendation/card",
                        method = RequestMethod.GET)
    public void getCard(@RequestParam("cityId") String cityId,
                        @RequestParam("coordinate") String coordinate,
                        @RequestParam("time") Date time) {

    }
}
