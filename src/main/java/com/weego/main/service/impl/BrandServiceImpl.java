package com.weego.main.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.BrandDao;
import com.weego.main.dto.POISepcialBaseDto;
import com.weego.main.model.Brand;
import com.weego.main.service.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
	private static Logger logger = LogManager.getLogger(BrandServiceImpl.class);

	@Autowired
	BrandDao brandDao;

	@Override
	public POISepcialBaseDto getBrandById(String id) {
		POISepcialBaseDto poiSepcialBaseDto = new POISepcialBaseDto();
		try {
			Brand brand =  brandDao.getBrandById(id);
			if(brand != null) {
				poiSepcialBaseDto.setSpecialId(brand.getId());
				poiSepcialBaseDto.setCoverImage(brand.getCoverImage());
				poiSepcialBaseDto.setTitle(brand.getTitle());
				poiSepcialBaseDto.setDesc(brand.getDesc());
				poiSepcialBaseDto.setTag(brand.getTag());;
			}
		} catch (Exception e) {
			logger.info("购物品牌的详情页接口出错!");
			e.printStackTrace();
		}
		return poiSepcialBaseDto;
	}
	
	
}
