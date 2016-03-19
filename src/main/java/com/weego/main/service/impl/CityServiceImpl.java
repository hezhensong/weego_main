package com.weego.main.service.impl;

import com.weego.main.dao.CityDao;
import com.weego.main.dao.TreeAreaDao;
import com.weego.main.dto.*;
import com.weego.main.model.City;
import com.weego.main.model.TreeAreaCity;
import com.weego.main.model.TreeAreaContinent;
import com.weego.main.model.TreeAreaCountry;
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
    private TreeAreaDao treeAreaDao;

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

        // get city 表
        Map<String, List<CityListCityDto>> cityListContinentMap = new HashMap<>();
        List<City> cityList = cityDao.getOnlineCity();
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
        List<TreeAreaContinent> treeAreaContinentList = treeAreaDao.getArea();
        for (String continent : cityListContinentMap.keySet()) {
            for (TreeAreaContinent treeAreaContinent : treeAreaContinentList) {

                if (treeAreaContinent.getName().equals(continent)) {
                    CityListContinentDto cityListContinentDto = new CityListContinentDto();
                    cityListContinentDto.setContinentName(continent);
                    cityListContinentDto.setContinentNameEn(treeAreaContinent.getNameEn());
                    cityListContinentDto.setCityList(cityListContinentMap.get(continent));

                    cityListContinentDtoList.add(cityListContinentDto);
                }
            }
        }

        return cityListContinentDtoList;
    }

    @Override
    public CityHomeDto getCityHome(String cityId) {
        CityHomeDto cityHomeDto = new CityHomeDto();
        Map<String, Object> data = new HashMap<>();

        CityBaseInfoDto cityBaseInfoDto = new CityBaseInfoDto();
        City city = cityDao.getSpecifiedCity(cityId);
        cityBaseInfoDto.setCoverImage("还没找到呢。");

        data.put("baseInfo", cityBaseInfoDto);

        cityHomeDto.setData(data);
        return cityHomeDto;
    }

    @Override
    public List<CityListContinentDto> getCityList1() {
        List<CityListContinentDto> cityListContinentDtoList = new ArrayList<>();

        // get area 表
        List<TreeAreaContinent> treeAreaContinentList = treeAreaDao.getArea();
        for (TreeAreaContinent treeAreaContinent : treeAreaContinentList) {

            // set 每个大洲的中英文名
            CityListContinentDto cityListContinentDto = new CityListContinentDto();
            cityListContinentDto.setContinentName(treeAreaContinent.getName());
            cityListContinentDto.setContinentNameEn(treeAreaContinent.getNameEn());

            List<CityListCityDto> cityListCityDtoList = new ArrayList<>();

            // 遍历每个国家
            List<TreeAreaCountry> treeAreaCountryList = treeAreaContinent.getCountryList();
            for (TreeAreaCountry treeAreaCountry : treeAreaCountryList) {

                // 遍历每个城市
                List<TreeAreaCity> treeAreaCityList = treeAreaCountry.getCityList();
                for (TreeAreaCity treeAreaCity : treeAreaCityList) {

                    CityListCityDto cityListCityDto = new CityListCityDto();
                    cityListCityDto.setCityId(treeAreaCity.getId());
                    cityListCityDto.setCityName(treeAreaCity.getName());
                    cityListCityDto.setCityNameEn(treeAreaCity.getNameEn());
                    cityListCityDtoList.add(cityListCityDto);
                }
            }
            cityListContinentDto.setCityList(cityListCityDtoList);

            cityListContinentDtoList.add(cityListContinentDto);
        }

        return cityListContinentDtoList;
    }
}
