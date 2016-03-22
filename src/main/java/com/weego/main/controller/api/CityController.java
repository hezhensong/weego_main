package com.weego.main.controller.api;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.CityHomeDto;
import com.weego.main.dto.CityListContinentDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.service.CityService;
import com.weego.main.service.PgcService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/city")
public class CityController {
    private static Logger logger = LogManager.getLogger(CityController.class);

    @Autowired
    CityService cityService;

    @Autowired
    PgcService pgcService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<CityHomeDto> getCityHome(@RequestParam("cityId") String cityId) {
        logger.info("cityId=[{}]", cityId);

        ResponseDto<CityHomeDto> responseDto = new ResponseDto<>();
        CityHomeDto cityHomeDto = cityService.getCityHome(cityId);

        if (cityHomeDto == null) {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
            logger.fatal("城市首页数据为空");
        } else {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(cityHomeDto);
        }

        return responseDto;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<List<CityListContinentDto>> getCityList() {
        logger.info("user={}", "test");

        ResponseDto<List<CityListContinentDto>> responseDto = new ResponseDto<>();
        List<CityListContinentDto> cityListContinentDtoList = cityService.getOnlineCityList();

        if (cityListContinentDtoList.size() == 0) {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
            logger.fatal("城市列表数据为空");

        } else {
            responseDto.setCodeMessage(ErrorCode.SUCCESS);
            responseDto.setData(cityListContinentDtoList);
        }

        return responseDto;
    }
}
