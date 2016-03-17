package com.weego.main.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.CityActivityDao;
import com.weego.main.dto.CityActivityHomeDto;
import com.weego.main.model.CityActivity;
import com.weego.main.service.CityActivityService;

@Service("cityActivityService")
public class CityActivityServiceImpl implements CityActivityService {

	@Autowired
	private CityActivityDao cityActivityDao;

	@Override
	public CityActivityHomeDto getCityActivityHome(String cityActivityId) {

		CityActivityHomeDto cityActivityHomeDto = new CityActivityHomeDto();
		Map<String, Object> data = new HashMap<>();

		CityActivity cityActivity = cityActivityDao.getSpecifiedCity(cityActivityId);

		data.put("CityActivity", cityActivity);

		cityActivityHomeDto.setData(data);

		return cityActivityHomeDto;
	}

}
