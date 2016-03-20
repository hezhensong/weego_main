package com.weego.main.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private ActivityService cityActivityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<ActivityListDto> getActivityList() {

        ResponseDto<ActivityListDto> responseDto = new ResponseDto<>();
        ActivityListDto activityBaseList = cityActivityService.getActivityList();
        if (activityBaseList != null) {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(activityBaseList);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;

    }

    @RequestMapping(value = "/{cityActivityId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<ActivityDetailDto> getActivityDetail(@PathVariable("cityActivityId") String cityActivityId) {

        System.out.println(cityActivityId);
        ResponseDto<ActivityDetailDto> responseDto = new ResponseDto<>();

        ActivityDetailDto activityDetailDto = cityActivityService.getActivityDetail(cityActivityId);

        if (activityDetailDto != null) {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(activityDetailDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;

    }

}
