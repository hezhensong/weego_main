package com.weego.main.controller.api;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dao.PgcDao;
import com.weego.main.dto.PgcDetailDto;
import com.weego.main.dto.PgcListPgcDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.model.Pgc;
import com.weego.main.service.PgcService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/city")
public class PgcController {
    private Logger logger = LogManager.getLogger(PgcController.class);

    @Autowired
    PgcService pgcService;

    @Autowired
    PgcDao pgcDao;

    @RequestMapping(value = "/pgc/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<List<PgcListPgcDto>> getPgcList(@RequestParam("cityId") String cityId) {
        ResponseDto<List<PgcListPgcDto>> responseDto = new ResponseDto<>();

        List<PgcListPgcDto> pgcListContinentDtoList = pgcService.getPgcList(cityId);
        if(pgcListContinentDtoList != null) {
            responseDto.setData(pgcListContinentDtoList);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;
    }

    @RequestMapping(value = "/pgc/test", method = RequestMethod.GET)
    @ResponseBody
    public Pgc getPgcTest(@RequestParam("pgcId") String pgcId) {
        return pgcDao.getSpecifiedPgc(pgcId);
    }


    @RequestMapping(value = "/pgc", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto<PgcDetailDto> getPgcDetail(@RequestParam("pgcId") String pgcId) {
        logger.info("查询Pgc页面详情");
        logger.info("pgcId = {}", pgcId);

        ResponseDto<PgcDetailDto> responseDto = new ResponseDto<PgcDetailDto>();
        PgcDetailDto pgcDetailDto = pgcService.getPgcDetail(pgcId);

        if(pgcDetailDto != null) {
            responseDto.setData(pgcDetailDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;
    }
}
