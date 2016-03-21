package com.weego.main.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.ActivityDetailDto;
import com.weego.main.dto.ActivityListDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.service.ActivityService;

@RestController
@RequestMapping("/api/v3/city/activity")
public class ActivityController {

    private Logger logger = LogManager.getLogger(ActivityController.class);

    @Autowired
    private ActivityService cityActivityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ActivityListDto getActivityList(@RequestParam("cityId") String cityId) {

        logger.info("开始城市活动列表查询");
        logger.info("cityId = {}", cityId);
        ActivityListDto activityBaseList = cityActivityService.getActivityList(cityId);
        logger.info("结束城市活动列表查询");
        return activityBaseList;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<ActivityDetailDto> getActivityDetail(@RequestParam("cityActivityId") String cityActivityId) {

        logger.info("开始城市活动详情查询");
        logger.info("cityActivityId = {}", cityActivityId);
        ResponseDto<ActivityDetailDto> responseDto = new ResponseDto<>();

        ActivityDetailDto activityDetailDto = cityActivityService.getActivityDetail(cityActivityId);

        if (activityDetailDto != null) {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(activityDetailDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        logger.info("结束城市活动详情查询");
        return responseDto;

    }
}
