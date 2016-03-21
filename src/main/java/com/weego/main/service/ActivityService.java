package com.weego.main.service;

import com.weego.main.dto.ActivityDetailDto;
import com.weego.main.dto.ActivityListDto;

public interface ActivityService {

    ActivityDetailDto getActivityDetail(String cityActivityId);

    ActivityListDto getActivityList(String cityId);

}
