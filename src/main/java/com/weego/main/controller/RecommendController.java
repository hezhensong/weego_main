package com.weego.main.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-14.
 */
@RestController
@RequestMapping("/api/v3/city")
public class RecommendController {

    //动态推荐列表
    @RequestMapping(value="/recommendation/history/{cityId}",
                        method= RequestMethod.GET)
    public void getHistory(@PathVariable("cityId") String cityId) {

    }

    //动态推荐卡片
    @RequestMapping(value="/recommendation/card/{cityId}/{lon,lat}/{time}",
                        method = RequestMethod.GET)
    public void getCard(@PathVariable("cityId") String cityId,
                        @PathVariable("lon") String lon,
                        @PathVariable("lat") String lat,
                        @PathVariable("time") Date time) {

    }
}
