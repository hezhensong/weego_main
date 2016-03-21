package com.weego.main.service.impl;

import com.weego.main.dao.CityDao;
import com.weego.main.dao.AreaDao;
import com.weego.main.dao.WeatherDao;
import com.weego.main.dao.WeatherTranslationDao;
import com.weego.main.dto.*;
import com.weego.main.model.*;
import com.weego.main.service.ActivityService;
import com.weego.main.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<CityListDto> getCityList() {
        List<CityListDto> cityListDtoList = new ArrayList<>();

        List<City> cityList = cityDao.getOnlineCity();
        for (City city : cityList) {
        }

        return cityListDtoList;
    }

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
            Map<String, Area> areaMap = areaDao.getArea();
            for (String continent : cityListContinentMap.keySet()) {
                CityListContinentDto cityListContinentDto = new CityListContinentDto();

                Area area = areaMap.get(continent);
                cityListContinentDto.setContinentName(area.getName());
                cityListContinentDto.setContinentNameEn(area.getNameEn());
                cityListContinentDto.setCityList(cityListContinentMap.get(continent));
                cityListContinentDtoList.add(cityListContinentDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityListContinentDtoList;
    }

    @Override
    public CityHomeDto getCityHome(String cityId) {
        CityHomeDto cityHomeDto = new CityHomeDto();

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

        for (WeatherForecast weatherForecast : weatherForecastList) {
            WeatherForecastDto weatherForecastDto = new WeatherForecastDto();

            weatherForecastDto.setDate(weatherForecast.getDate().toString());
            weatherForecastDto.setHigh(weatherForecast.getHigh() + "");
            weatherForecastDto.setLow(weatherForecast.getLow() + "");

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
        cityHomeDto.setPgc(cityHomePgcDto);

        cityHomePgcDto.setTitle("华裔名厨蔡明昊");
        cityHomePgcDto.setCoverImage("http://weegotest.b0.upaiyun.com/activities/iosimgs/56cc5cf22ddd9d3d17000022.jpeg");

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

    @Override
    public List<CityListContinentDto> getCityList1() {
        List<CityListContinentDto> cityListContinentDtoList = new ArrayList<>();

//        // get area 表
//        List<TreeAreaContinent> treeAreaContinentList = areaDao.getArea();
//        for (TreeAreaContinent treeAreaContinent : treeAreaContinentList) {
//
//            // set 每个大洲的中英文名
//            CityListContinentDto cityListContinentDto = new CityListContinentDto();
//            cityListContinentDto.setContinentName(treeAreaContinent.getName());
//            cityListContinentDto.setContinentNameEn(treeAreaContinent.getNameEn());
//
//            List<CityListCityDto> cityListCityDtoList = new ArrayList<>();
//
//            // 遍历每个国家
//            List<TreeAreaCountry> treeAreaCountryList = treeAreaContinent.getCountryList();
//            for (TreeAreaCountry treeAreaCountry : treeAreaCountryList) {
//
//                // 遍历每个城市
//                List<TreeAreaCity> treeAreaCityList = treeAreaCountry.getCityList();
//                for (TreeAreaCity treeAreaCity : treeAreaCityList) {
//
//                    CityListCityDto cityListCityDto = new CityListCityDto();
//                    cityListCityDto.setCityId(treeAreaCity.getId());
//                    cityListCityDto.setCityName(treeAreaCity.getName());
//                    cityListCityDto.setCityNameEn(treeAreaCity.getNameEn());
//                    cityListCityDtoList.add(cityListCityDto);
//                }
//            }
//            cityListContinentDto.setCityList(cityListCityDtoList);
//
//            cityListContinentDtoList.add(cityListContinentDto);
//        }

        return cityListContinentDtoList;
    }
}
