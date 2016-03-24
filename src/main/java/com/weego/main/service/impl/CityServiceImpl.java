package com.weego.main.service.impl;

import com.weego.main.dao.CityDao;
import com.weego.main.dao.AreaDao;
import com.weego.main.dao.WeatherDao;
import com.weego.main.dao.WeatherTranslationDao;
import com.weego.main.dto.*;
import com.weego.main.model.*;
import com.weego.main.service.ActivityService;
import com.weego.main.service.CityService;
import com.weego.main.service.PgcService;
import com.weego.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("cityService")
public class CityServiceImpl implements CityService {
    private static Logger logger = LogManager.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private WeatherDao weatherDao;

    @Autowired
    private WeatherTranslationDao weatherTranslationDao;

    @Autowired
    private ActivityService cityActivityService;

    @Autowired
    private PgcService pgcService;

    @Override
    public List<CityListContinentDto> getOnlineCityList() {
        List<CityListContinentDto> cityListContinentDtoList = new ArrayList<>();

        try {
            // get city 表
            List<City> cityList = cityDao.getOnlineCity();
            Map<String, List<CityListCityDto>> cityListContinentMap = new HashMap<>();
            for (City city : cityList) {

                CityListCityDto cityListCityDto = new CityListCityDto();
                cityListCityDto.setCityId(city.getId().toString());
                cityListCityDto.setCityName(city.getName());
                cityListCityDto.setCityNameEn(city.getNameEn());

                String continent = city.getArea().getContinent();
                if (cityListContinentMap.containsKey(continent)) {
                    cityListContinentMap.get(continent).add(cityListCityDto);
                } else {
                    List<CityListCityDto> cityListCityDtoList = new ArrayList<>();
                    cityListCityDtoList.add(cityListCityDto);
                    cityListContinentMap.put(continent, cityListCityDtoList);
                }
            }

            // get area 表, 查询城市所在大洲的信息
            Map<Integer, Area> areaMap = areaDao.getArea();
            for (Area area : areaMap.values()) {
                for (String continent : cityListContinentMap.keySet()) {
                    if (area.getName().equals(continent)) {

                        CityListContinentDto cityListContinentDto = new CityListContinentDto();
                        cityListContinentDto.setContinentName(area.getName());
                        cityListContinentDto.setContinentNameEn(area.getNameEn());
                        cityListContinentDto.setCityList(cityListContinentMap.get(continent));

                        cityListContinentDtoList.add(cityListContinentDto);
                    }
                }
            }

        } catch (Exception e) {
            logger.fatal("在线城市列表接口获取数据失败 {}", e.getStackTrace());
        }

        return cityListContinentDtoList;
    }

    @Override
    public CityHomeDto getCityHome(String cityId) {
        CityHomeDto cityHomeDto = new CityHomeDto();

        try {
            // 获取城市基本信息
            CityHomeBaseInfoDto cityHomeBaseInfoDto = new CityHomeBaseInfoDto();
            cityHomeDto.setBaseInfo(cityHomeBaseInfoDto);

            City city = cityDao.getSpecifiedCity(cityId);
            cityHomeBaseInfoDto.setCityId(city.getId().toString());
            cityHomeBaseInfoDto.setCityName(city.getName());
            cityHomeBaseInfoDto.setCityNameEn(city.getNameEn());
            cityHomeBaseInfoDto.setCoverImage(city.getCoverImage());

            // 获取城市Label列表
            CityHomeLabelDto cityHomeLabelDto = new CityHomeLabelDto();
            cityHomeDto.setLabels(cityHomeLabelDto);

            Map<String, List<CityLabel>> labelListMap = city.getLabelList();
            for (String type : labelListMap.keySet()) {

                if (type.equals("0")) {
                    List<Map<String, String>> attractionLabelList = convertCityLabelListToMap(labelListMap.get("0"));
                    cityHomeLabelDto.setAttractionLabel(attractionLabelList);

                } else if (type.equals("1")) {
                    List<Map<String, String>> restaurantLabelList = convertCityLabelListToMap(labelListMap.get("1"));
                    cityHomeLabelDto.setRestaurantLabel(restaurantLabelList);

                } else if (type.equals("2")) {
                    List<Map<String, String>> shoppingLabelList = convertCityLabelListToMap(labelListMap.get("2"));
                    cityHomeLabelDto.setShoppingLabel(shoppingLabelList);
                }
            }

            // 获取城市天气信息
            CityHomeWeatherDto cityHomeWeatherDto = new CityHomeWeatherDto();
            cityHomeDto.setWeather(cityHomeWeatherDto);

            Weather weather = weatherDao.getWeatherByCityId(cityId);

            // 当天天气
            WeatherCondition weatherCondition = weather.getCondition();
            WeatherConditionDto weatherConditionDto = new WeatherConditionDto();
            cityHomeWeatherDto.setCondition(weatherConditionDto);

            weatherConditionDto.setUpdateTime(weatherCondition.getUpdate_time().toString());
            weatherConditionDto.setHigh(weatherCondition.getHigh() + "");
            weatherConditionDto.setLow(weatherCondition.getLow() + "");
            weatherConditionDto.setSunrise(weatherCondition.getSunrise());
            weatherConditionDto.setSunset(weatherCondition.getSunset());
            weatherConditionDto.setTemperature(weatherCondition.getTemperature() + "");

            String description = weatherCondition.getDescription().toLowerCase();
            Map<String, String> translationMap = weatherTranslationDao.getWeatherTranslation();
            if (translationMap.keySet().contains(description)) {
                weatherConditionDto.setDescription(translationMap.get(description));
            } else {
                weatherConditionDto.setDescription("未知");
                logger.error("天气描述未知：{}", description);
            }

            // 5天预告
            List<WeatherForecast> weatherForecastList = weather.getForecast();
            List<WeatherForecastDto> weatherForecastDtoList = new ArrayList<>();
            cityHomeWeatherDto.setForecast(weatherForecastDtoList);

            int order = 1;
            for (WeatherForecast weatherForecast : weatherForecastList) {
                WeatherForecastDto weatherForecastDto = new WeatherForecastDto();

                String date = DateUtil.formatMM_dd(weatherForecast.getDate());
                weatherForecastDto.setDate(date);

                // 第一天、第二天 转换为今天、明天
                String day;
                if (order == 1) {
                    day = "今天";
                    ++order;
                } else if (order == 2) {
                    day = "明天";
                    ++order;
                } else {
                    day = DateUtil.formatDay(weatherForecast.getDate());
                }
                weatherForecastDto.setDay(day);

                weatherForecastDto.setHigh(String.valueOf(weatherForecast.getHigh()));
                weatherForecastDto.setLow(String.valueOf(weatherForecast.getLow()));

                description = weatherForecast.getDescription().toLowerCase();
                if (translationMap.keySet().contains(description)) {
                    weatherForecastDto.setDescription(translationMap.get(description));
                } else {
                    weatherForecastDto.setDescription("未知");
                    logger.error("天气描述未知：{}", description);
                }

                weatherForecastDtoList.add(weatherForecastDto);
            }

            // 获取城市计划 TODO 行程计划单独做
            CityHomePlanDto cityHomePlanDto = new CityHomePlanDto();
            cityHomePlanDto.setHavePlan(false);
            cityHomePlanDto.setDateRange("");
            cityHomeDto.setPlan(cityHomePlanDto);

            // 获取城市活动
            CityHomeActivityDto cityHomeActivityDto = new CityHomeActivityDto();
            List<ActivityBaseDto> activityBaseList = cityActivityService.getActivityList(cityId);
            if (activityBaseList.size() > 0) {
                ActivityBaseDto activityBaseDto = activityBaseList.get(0);
                cityHomeActivityDto.setTitle(activityBaseDto.getTitle());
                cityHomeActivityDto.setCoverImage(activityBaseDto.getImage());
                cityHomeDto.setCityActivity(cityHomeActivityDto);
            }

            // 获取城市PGC
            CityHomePgcDto cityHomePgcDto = new CityHomePgcDto();
            List<PgcListPgcDto> pgcListPgcDtoList = pgcService.getPgcList(cityId);
            if (pgcListPgcDtoList.size() > 0) {
                PgcListPgcDto pgcListPgcDto = pgcListPgcDtoList.get(0);
                cityHomePgcDto.setTitle(pgcListPgcDto.getTitle());
                cityHomePgcDto.setCoverImage(pgcListPgcDto.getCoverImage());
                cityHomeDto.setPgc(cityHomePgcDto);
            }

        } catch (Exception e) {
            logger.fatal("城市首页接口获取数据失败 {}", e.getStackTrace());
            return null;
        }

        return cityHomeDto;
    }

    private List<Map<String, String>> convertCityLabelListToMap(List<CityLabel> cityLabelList) {
        List<Map<String, String>> attractionLabelList = new ArrayList<>();

        for (CityLabel cityLabel : cityLabelList) {
            Map<String, String> attractionLabel = new HashMap<>();
            attractionLabel.put("id", cityLabel.getId());
            attractionLabel.put("title", cityLabel.getName());
            attractionLabelList.add(attractionLabel);
        }
        return attractionLabelList;
    }
}
