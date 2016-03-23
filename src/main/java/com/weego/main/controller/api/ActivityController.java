package com.weego.main.controller.api;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.ActivityBaseDto;
import com.weego.main.dto.ActivityDetailDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.service.ActivityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/city")
public class ActivityController {
    private Logger logger = LogManager.getLogger(ActivityController.class);

    @Autowired
    private ActivityService cityActivityService;

    @RequestMapping(value = "/activity/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<List<ActivityBaseDto>> getActivityList(@RequestParam("cityId") String cityId) {

        logger.info("开始城市活动列表查询");
        logger.info("cityId = {}", cityId);
        List<ActivityBaseDto> activityBaseList = cityActivityService.getActivityList(cityId);

        logger.info("结束城市活动列表查询");
        ResponseDto<List<ActivityBaseDto>> responseDto = new ResponseDto<>();
        if (activityBaseList != null && activityBaseList.size() > 0) {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(activityBaseList);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
            logger.fatal("城市活动列表数据为空");
        }

        return responseDto;
    }

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<ActivityDetailDto> getActivityDetail(@RequestParam("activityId") String activityId) {

        logger.info("开始城市活动详情查询");
        logger.info("activityId = {}", activityId);
        ResponseDto<ActivityDetailDto> responseDto = new ResponseDto<>();

        ActivityDetailDto activityDetailDto = cityActivityService.getActivityDetail(activityId);

        if (activityDetailDto != null) {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(activityDetailDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
            logger.fatal("城市活动详情数据为空");
        }
        logger.info("结束城市活动详情查询");
        return responseDto;

    }
}
