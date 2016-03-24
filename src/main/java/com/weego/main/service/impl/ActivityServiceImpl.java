package com.weego.main.service.impl;

import com.weego.main.dao.ActivityDao;
import com.weego.main.dto.ActivityBaseDto;
import com.weego.main.dto.ActivityDetailDto;
import com.weego.main.dto.ActivityParagraphsDto;
import com.weego.main.model.Activity;
import com.weego.main.model.ActivityParagraphs;
import com.weego.main.service.ActivityService;
import com.weego.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("cityActivityService")
public class ActivityServiceImpl implements ActivityService {

    private Logger logger = LogManager.getLogger(ActivityServiceImpl.class);
    @Autowired
    private ActivityDao cityActivityDao;

    public ActivityDetailDto getActivityDetail(String cityActivityId) {

        logger.info("查询城市活动详细信息, cityActivityId = {}", cityActivityId);

        logger.info("-------查询城市活动详细信息开始----------");
        ActivityDetailDto activityDetailDto = new ActivityDetailDto();

        try {
            Activity cityActivity = cityActivityDao.getSpecifiedCity(cityActivityId);

            if (cityActivity != null) {
                // 将数据库中的数据赋值给dto
                activityDetailDto.setId(cityActivity.getId().toString());
                activityDetailDto.setActTime(cityActivity.getActTime());
                activityDetailDto.setActUrl(cityActivity.getActUrl());
                activityDetailDto.setDescription(cityActivity.getDescription());

                // 详情页的地址需要拼接
                if (cityActivity.getDetailAddress() != null && cityActivity.getAddress() != null
                        && !cityActivity.getDetailAddress().endsWith(cityActivity.getAddress())) {
                    activityDetailDto.setDetailAddress(cityActivity.getDetailAddress() + ", "
                            + cityActivity.getAddress());
                } else if (cityActivity.getDetailAddress() == null && cityActivity.getAddress() != null) {
                    activityDetailDto.setDetailAddress(cityActivity.getAddress());
                } else if (cityActivity.getDetailAddress() != null && cityActivity.getAddress() == null) {
                    activityDetailDto.setDetailAddress(cityActivity.getDetailAddress());
                } else if (cityActivity.getDetailAddress() != null && cityActivity.getAddress() != null
                        && cityActivity.getDetailAddress().endsWith(cityActivity.getAddress())) {
                    activityDetailDto.setDetailAddress(cityActivity.getAddress());
                }

                activityDetailDto.setId(cityActivity.getId().toString());
                activityDetailDto.setImage(cityActivity.getImage());

                // 将coordinate拆成longitude和latitude
                String coordination = cityActivity.getCoordination();

                if (coordination != null && coordination.length() != 0) {
                    String[] lonlat = coordination.split(",");
                    activityDetailDto.setLongitude(lonlat[0]);
                    activityDetailDto.setLatidute(lonlat[1]);
                }

                activityDetailDto.setOrderUrl(cityActivity.getOrderUrl());
                activityDetailDto.setTitle(cityActivity.getTitle().trim());
                activityDetailDto.setType(cityActivity.getType());

                // 将数据库中activity表中的paragraphs数组转成相应的dto
                List<ActivityParagraphs> cityActivityParagraphsList = cityActivity.getParagraphs();

                List<ActivityParagraphsDto> activityParagraphsDtoList = new ArrayList<ActivityParagraphsDto>();

                if (cityActivityParagraphsList != null && !cityActivityParagraphsList.isEmpty()) {
                    for (ActivityParagraphs cityActivityParagraphs : cityActivityParagraphsList) {

                        ActivityParagraphsDto activityParagraphsDto = new ActivityParagraphsDto();

                        activityParagraphsDto.setDetailDown(cityActivityParagraphs.getDetailDown());
                        activityParagraphsDto.setDetailUp(cityActivityParagraphs.getDetailUp());
                        activityParagraphsDto.setImageBrief(cityActivityParagraphs.getImageBrief());
                        activityParagraphsDto.setImageTitle(cityActivityParagraphs.getImageTitle());
                        activityParagraphsDto.setImageUrl(cityActivityParagraphs.getImageUrl());

                        activityParagraphsDtoList.add(activityParagraphsDto);

                    }

                    activityDetailDto.setParagraphs(activityParagraphsDtoList);
                }

            }
        } catch (Exception e) {
            logger.fatal("城市活动详情页接口获取数据失败 {}", e.getStackTrace());
            return null;
        }

        return activityDetailDto;

    }

    @Override
    public List<ActivityBaseDto> getActivityList(String cityId) {
        logger.info("查询城市活动列表, cityId = {}", cityId);
        logger.info("---------查询城市活动列表开始----------");

        List<ActivityBaseDto> activityBaseDtoList = new ArrayList<>();

        try {
            List<Activity> activityList = cityActivityDao.getAllActivity(cityId);

            if (activityList != null && !activityList.isEmpty()) {
                for (int i = 0; i < activityList.size(); i++) {

                    ActivityBaseDto activityBaseDto = new ActivityBaseDto();
                    Activity cityActivity = activityList.get(i);

                    // 开始赋值
                    activityBaseDto.setId(cityActivity.getId().toString());
                    activityBaseDto.setAddress(cityActivity.getAddress());
                    activityBaseDto.setTitle(cityActivity.getTitle().trim());
                    activityBaseDto.setType(cityActivity.getType());
                    activityBaseDto.setImage(cityActivity.getImage());

                    // 设置活动具体时间
                    /**
                     * 前端acttime字段展示逻辑 1.时间格式：X月X日，需要跨年的显示x年
                     * 2.活动开始日期-今天<=7天，显示“即将开始” 活动开始日期<=今天<=活动结束日期，显示“进行中”
                     * 活动开始日期-今天>7天，显示“开始日期-结束日期”，不需要具体时间点
                     * 
                     */
                    Date dateNow = new Date();

                    Date openTime = cityActivity.getOpenTime();
                    System.out.println("openTime:" + openTime);
                    Date closeTime = cityActivity.getCloseTime();

                    logger.info("---------查询城市活动列表开始----------");
                    if (openTime != null && closeTime != null) {

                        // 计算活动开始时间与当前日期相差的天数
                        int openNow;
                        int nowClose;
                        try {
                            openNow = DateUtil.daysBetween(dateNow, openTime);
                            nowClose = DateUtil.daysBetween(dateNow, closeTime);
                            // 计算活动结束日期与当前日期相差的天数
                            if (openNow <= 7 & openNow > 0) {
                                activityBaseDto.setActTime("即将开始");
                            } else if (openNow <= 0 & nowClose > 0) {
                                activityBaseDto.setActTime("进行中");
                            } else if (openNow > 7) {
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
                            logger.fatal("时间转换错误 {}", e.getStackTrace());
                        }
                    }

                    activityBaseDtoList.add(activityBaseDto);
                }
            }
        } catch (Exception e) {
            logger.fatal("城市活动列表接口获取数据失败 {}", e.getStackTrace());
        }

        return activityBaseDtoList;
    }

    @Override
    public ModelAndView getSpecifiedActivity(String activityId) {

        Activity activity = cityActivityDao.getSpecifiedCity(activityId);
        if (activity == null) {
            return null;
        } else {
            ModelAndView mv = new ModelAndView("newactivity");
            mv.addObject("title",activity.getTitle());
            mv.addObject("time",activity.getActTime());
            mv.addObject("ip",activity.getDetailAddress());
            mv.addObject("bg",activity.getImage());
            mv.addObject("web",activity.getActUrl());
            mv.addObject("ticket",activity.getOrderUrl());
            mv.addObject("details",activity.getDescription());

            mv.addObject("paragraphs",activity.getParagraphs());
           
            return mv;
        }

    }
}
