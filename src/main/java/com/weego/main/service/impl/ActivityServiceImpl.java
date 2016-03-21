package com.weego.main.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.ActivityDao;
import com.weego.main.dto.ActivityBaseDto;
import com.weego.main.dto.ActivityDetailDto;
import com.weego.main.dto.ActivityListDto;
import com.weego.main.dto.ActivityParagraphsDto;
import com.weego.main.model.Activity;
import com.weego.main.model.ActivityParagraphs;
import com.weego.main.service.ActivityService;
import com.weego.main.util.DateUtil;

@Service("cityActivityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao cityActivityDao;

    public ActivityDetailDto getActivityDetail(String cityActivityId) {

        ActivityDetailDto activityDetailDto = new ActivityDetailDto();

        Activity cityActivity = cityActivityDao.getSpecifiedCity(cityActivityId);

        // 将数据库中的数据赋值给dto
        activityDetailDto.setId(cityActivity.getId());
        activityDetailDto.setActTime(cityActivity.getActTime());
        activityDetailDto.setActUrl(cityActivity.getActUrl());
        // 将时间格式转换成字符串
        String closeTime = DateUtil.formatyyyyMMdd(cityActivity.getCloseTime());
        System.out.println("看看时间格式" + closeTime);
        String openTime = DateUtil.formatyyyyMMdd(cityActivity.getOpenTime());
        activityDetailDto.setCloseTime(closeTime);
        activityDetailDto.setOpenTime(openTime);

        activityDetailDto.setDescription(cityActivity.getDescription());
        activityDetailDto.setDetailAddress(cityActivity.getDetailAddress());
        activityDetailDto.setId(cityActivity.getId());
        activityDetailDto.setImage(cityActivity.getImage());

        // 将coordinate拆成longitude和latitude
        String coordination = cityActivity.getCoordination();

        if (coordination != null && coordination.length() != 0) {
            String[] lonlat = coordination.split(",");
            activityDetailDto.setLongitude(lonlat[0]);
            activityDetailDto.setLatidute(lonlat[1]);
        }

        activityDetailDto.setOrderUrl(cityActivity.getOrderUrl());
        activityDetailDto.setTitle(cityActivity.getTitle());
        activityDetailDto.setType(cityActivity.getType());

        // 将数据库中activity表中的paragraphs数组转成相应的dto
        List<ActivityParagraphs> cityActivityParagraphsList = cityActivity.getParagraphs();

        List<ActivityParagraphsDto> activityParagraphsDtoList = new ArrayList<ActivityParagraphsDto>();

        if (cityActivityParagraphsList != null && !cityActivityParagraphsList.isEmpty()) {
            for (ActivityParagraphs cityActivityParagraphs : cityActivityParagraphsList) {

                ActivityParagraphsDto activityParagraphsDto = new ActivityParagraphsDto();

                activityParagraphsDto.setDetailDown(cityActivityParagraphs.getDetailDown());
                System.out.println(cityActivityParagraphs.getDetailDown());
                activityParagraphsDto.setDetailUp(cityActivityParagraphs.getDetailUp());
                activityParagraphsDto.setImageBrief(cityActivityParagraphs.getImageBrief());
                activityParagraphsDto.setImageTitle(cityActivityParagraphs.getImageTitle());
                activityParagraphsDto.setImageUrl(cityActivityParagraphs.getImageUrl());

                activityParagraphsDtoList.add(activityParagraphsDto);

            }

            activityDetailDto.setParagraphs(activityParagraphsDtoList);

        }

        return activityDetailDto;
    }

    @Override
    public ActivityListDto getActivityList() {

        ActivityListDto activityListDto = new ActivityListDto();

        List<Activity> activityList = cityActivityDao.getAllActivity();

        List<ActivityBaseDto> activityBaseDtoList = new ArrayList<ActivityBaseDto>();

        if (activityList != null && !activityList.isEmpty()) {
            for (int i = 0; i < activityList.size(); i++) {
                ActivityBaseDto activityBaseDto = new ActivityBaseDto();
                Activity cityActivity = activityList.get(i);
                // 开始赋值
                activityBaseDto.setId(cityActivity.getId());
                activityBaseDto.setDetailAddress(cityActivity.getDetailAddress());
                activityBaseDto.setTitle(cityActivity.getTitle());
                activityBaseDto.setType(cityActivity.getType());
                activityBaseDto.setImage(cityActivity.getImage());
                // 设置活动具体时间
                /**
                 * 前端acttime字段展示逻辑 1.时间格式：X月X日，需要跨年的显示x年
                 * 2.活动开始日期-今天<=7天，显示“即将开始” 活动开始日期<=今天<=活动结束日期，显示“进行中”
                 * 活动开始日期-今天>7天，显示“开始日期-结束日期”，不需要具体时间点
                 * 
                 */
                Date datenow = new Date();
                System.out.println("当前时间是" + datenow);
                Date openTime = cityActivity.getOpenTime();
                System.out.println("开始时间是s" + cityActivity.getOpenTime());
                Date closeTime = cityActivity.getCloseTime();
                System.out.println("结束时间是s" + cityActivity.getCloseTime());
                if (openTime != null && closeTime != null) {
                    // 计算活动开始时间与当前日期相差的天数
                    int opennow;
                    int nowclose;
                    try {
                        opennow = DateUtil.daysBetween(datenow, openTime);
                        System.out.println("看看相差几天opennow" + opennow);
                        nowclose = DateUtil.daysBetween(datenow, closeTime);
                        System.out.println("看看相差几天nowclose" + nowclose);
                        // 计算活动结束日期与当前日期相差的天数

                        if (opennow <= 7 & opennow > 0) {
                            activityBaseDto.setActTime("即将开始");
                        } else if (opennow <= 0 & nowclose > 0) {
                            activityBaseDto.setActTime("进行中");
                        } else if (opennow > 7) {
                            // 获取开始日期和结束日期的年份
                            int openYear = openTime.getYear();
                            int closeYear = closeTime.getYear();
                            String openMMdd = (openTime.getMonth() + 1) + "月" + openTime.getDate() + "日";
                            String closeMMdd = (closeTime.getMonth() + 1) + "月" + closeTime.getDate() + "日";
                            if (openYear == openYear) {
                                // 不是跨年，不显示年份
                                activityBaseDto.setActTime(openMMdd + "-" + closeMMdd);

                            } else {
                                // 跨年需要显示年月日
                                activityBaseDto.setActTime(openYear + "年" + openMMdd + "-" + closeYear + "年"
                                        + closeMMdd);
                            }
                        }

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                // 设置排序字段
                activityBaseDto.setSortTag(i + 1);

                activityBaseDtoList.add(activityBaseDto);
            }
        }
        activityListDto.setActivityList(activityBaseDtoList);
        return activityListDto;
    }


}