package com.weego.main.controller.api;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dao.TreeAreaDao;
import com.weego.main.dto.CityHomeDto;
import com.weego.main.dto.CityListContinentDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.model.TreeAreaContinent;
import com.weego.main.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/city")
public class CityController {
    private Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    CityService cityService;

    @Autowired
    TreeAreaDao treeAreaDao;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<List<CityListContinentDto>> getCityList() {

        ResponseDto<List<CityListContinentDto>> responseDto = new ResponseDto<>();
        List<CityListContinentDto> cityListContinentDtoList = cityService.getOnlineCityList();
        if (cityListContinentDtoList.size() == 0) {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        } else {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(cityService.getOnlineCityList());
        }

        return responseDto;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public CityHomeDto getCityHome(@RequestParam("cityId") String cityId) {

        logger.info("cityId = {}", cityId);
        return cityService.getCityHome(cityId);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeAreaContinent> testmain() {

        return treeAreaDao.getArea();
    }
}
