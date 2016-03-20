package com.weego.main.service.impl;

import com.weego.main.dao.CityDao;
import com.weego.main.dao.AreaDao;
import com.weego.main.dao.WeatherDao;
import com.weego.main.dto.*;
import com.weego.main.model.*;
import com.weego.main.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private WeatherDao weatherDao;

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
                cityListCityDto.setCityId(city.getId());
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

        } catch (Exception e){
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
        cityHomeBaseInfoDto.setCityId(city.getId());
        cityHomeBaseInfoDto.setCityName(city.getName());
        cityHomeBaseInfoDto.setCityNameEn(city.getNameEn());
        cityHomeBaseInfoDto.setCoverImage(city.getCoverImage());

        // 获取城市Label列表
        CityHomeLabelDto cityHomeLabelDto = new CityHomeLabelDto();
        cityHomeDto.setLabels(cityHomeLabelDto);

        Map<String, List<CityLabel>> labelListMap = city.getLabelList();
        for (String type : labelListMap.keySet()) {

            if (type.equals("0")) {
                List<Map<String, String>> attractionLabelList = new ArrayList<>();

                List<CityLabel> cityLabelList = labelListMap.get("0");
                for (CityLabel cityLabel : cityLabelList) {
                    Map<String, String> attractionLabel = new HashMap<>();
                    attractionLabel.put("id", cityLabel.getId());
                    attractionLabel.put("title", cityLabel.getName());
                    attractionLabelList.add(attractionLabel);
                }
                cityHomeLabelDto.setAttractionLabel(attractionLabelList);

            } else if (type.equals("1")) {
                List<Map<String, String>> restaurantLabelList = new ArrayList<>();

                List<CityLabel> cityLabelList = labelListMap.get("1");
                for (CityLabel cityLabel : cityLabelList) {
                    Map<String, String> attractionLabel = new HashMap<>();
                    attractionLabel.put("id", cityLabel.getId());
                    attractionLabel.put("title", cityLabel.getName());
                    restaurantLabelList.add(attractionLabel);
                }
                cityHomeLabelDto.setRestaurantLabel(restaurantLabelList);

            } else if (type.equals("2")) {
                List<Map<String, String>> shoppingLabelList = new ArrayList<>();

                List<CityLabel> cityLabelList = labelListMap.get("2");
                for (CityLabel cityLabel : cityLabelList) {
                    Map<String, String> shoppingLabel = new HashMap<>();
                    shoppingLabel.put("id", cityLabel.getId());
                    shoppingLabel.put("title", cityLabel.getName());
                    shoppingLabelList.add(shoppingLabel);
                }
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
        weatherConditionDto.setDescription(weatherCondition.getDescription());
        weatherConditionDto.setTemperature(weatherCondition.getTemperature() + "");

        // 5天预告
        List<WeatherForecast> weatherForecastList = weather.getForecast();
        List<WeatherForecastDto> weatherForecastDtoList = new ArrayList<>();
        cityHomeWeatherDto.setForecast(weatherForecastDtoList);

        for (WeatherForecast weatherForecast : weatherForecastList) {
            WeatherForecastDto weatherForecastDto = new WeatherForecastDto();

            weatherForecastDto.setDate(weatherForecast.getDate().toString());
            weatherForecastDto.setHigh(weatherForecast.getHigh() + "");
            weatherForecastDto.setLow(weatherForecast.getLow() + "");
            weatherForecastDto.setDescription(weatherForecast.getDescription());

            weatherForecastDtoList.add(weatherForecastDto);
        }

        // 获取城市计划
        CityHomePlanDto cityHomePlanDto = new CityHomePlanDto();
        cityHomePlanDto.setHavePlan(false);
        cityHomePlanDto.setDateRange("");
        cityHomeDto.setPlan(cityHomePlanDto);

        // 获取城市活动
        CityHomeActivityDto cityHomeActivityDto = new CityHomeActivityDto();
        cityHomeDto.setCityActivity(cityHomeActivityDto);

        cityHomeActivityDto.setTitle("第十一届圣帕特里克系列音乐会");
        cityHomeActivityDto.setCoverImage("http://weegotest.b0.upaiyun.com/activities/iosimgs/56c6a57482b914445e000008.jpeg");

        // 获取城市PGC
        CityHomePgcDto cityHomePgcDto = new CityHomePgcDto();
        cityHomeDto.setPgc(cityHomePgcDto);

        cityHomePgcDto.setTitle("华裔名厨蔡明昊");
        cityHomePgcDto.setCoverImage("http://weegotest.b0.upaiyun.com/activities/iosimgs/56cc5cf22ddd9d3d17000022.jpeg");

        return cityHomeDto;
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
