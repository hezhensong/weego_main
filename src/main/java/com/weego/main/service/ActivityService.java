package com.weego.main.service;

import com.weego.main.dto.ActivityBaseDto;
import com.weego.main.dto.ActivityDetailDto;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

public interface ActivityService {

    ActivityDetailDto getActivityDetail(String cityActivityId);

    List<ActivityBaseDto> getActivityList(String cityId);
    
     ModelAndView getSpecifiedActivity(String activityId);

}
