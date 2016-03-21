package com.weego.main.dto;

import java.util.List;

public class ActivityListDto extends BaseDto{

    private List<ActivityBaseDto> activityList;

    public List<ActivityBaseDto> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityBaseDto> activityList) {
        this.activityList = activityList;
    }

}
