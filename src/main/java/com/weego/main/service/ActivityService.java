package com.weego.main.service;

import com.weego.main.dto.ActivityBaseDto;
import com.weego.main.dto.ActivityDetailDto;

import java.util.List;

public interface ActivityService {

    ActivityDetailDto getActivityDetail(String cityActivityId);

    List<ActivityBaseDto> getActivityList(String cityId);

}
