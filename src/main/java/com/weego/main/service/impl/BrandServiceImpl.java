package com.weego.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.BrandDao;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.model.Brand;
import com.weego.main.service.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	BrandDao brandDao;

	@Override
	public POISepcialBaseDto getBrandById(String id) {
		POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
		Brand brand =  brandDao.getBrandById(id);
		if(brand != null) {
			poiSepcialBaseDto.setSpecialId(brand.getId());
			poiSepcialBaseDto.setCoverImage(brand.getCoverImage());
			poiSepcialBaseDto.setTitle(brand.getTitle());
			poiSepcialBaseDto.setDesc(brand.getDesc());
			poiSepcialBaseDto.setTag(brand.getTag());;
		}
		return poiSepcialBaseDto;
	}
	
	
}
