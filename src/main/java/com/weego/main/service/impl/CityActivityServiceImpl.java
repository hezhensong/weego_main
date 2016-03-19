package com.weego.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.CityActivityDao;
import com.weego.main.dto.ActivityDetailDto;
import com.weego.main.dto.ActivityParagraphsDto;
import com.weego.main.dto.CityActivityHomeDto;
import com.weego.main.model.CityActivity;
import com.weego.main.model.CityActivityParagraphs;
import com.weego.main.service.CityActivityService;
import com.weego.main.util.DateUtil;

@Service("cityActivityService")
public class CityActivityServiceImpl implements CityActivityService {

    @Autowired
    private CityActivityDao cityActivityDao;

    public CityActivityHomeDto getCityActivityHome(String cityActivityId) {

        CityActivityHomeDto cityActivityHomeDto = new CityActivityHomeDto();

        ActivityDetailDto activityDetailDto = new ActivityDetailDto();
        Map<String, Object> data = new HashMap<>();

        CityActivity cityActivity = cityActivityDao.getSpecifiedCity(cityActivityId);

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
        // List<CityActivityParagraphs> cityActivityParagraphsList =
        // cityActivity.getParagraphs().get("paragraphs");
        List<CityActivityParagraphs> cityActivityParagraphsList = cityActivity.getParagraphs();

        List<ActivityParagraphsDto> activityParagraphsDtoList = new ArrayList<ActivityParagraphsDto>();

        if (cityActivityParagraphsList != null && !cityActivityParagraphsList.isEmpty()) {
            for (CityActivityParagraphs cityActivityParagraphs : cityActivityParagraphsList) {

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

        data.put("activityDetail", activityDetailDto);

        cityActivityHomeDto.setData(data);

        return cityActivityHomeDto;
    }

}
