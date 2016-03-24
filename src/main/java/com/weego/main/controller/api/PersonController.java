package com.weego.main.controller.api;

import com.weego.main.constant.ErrorCode;
import com.weego.main.dto.PersonDto;
import com.weego.main.dto.ResponseDto;
import com.weego.main.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuniandxx on 16-3-21.
 */
@RestController
@RequestMapping("/api/v3/city")
public class PersonController {
    private Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseDto<PersonDto> getPersonDetail(@RequestParam("personId") String personId) {
        logger.info("查询人物详情信息");
        logger.info("personId = {}", personId);

        ResponseDto<PersonDto> responseDto = new ResponseDto<PersonDto>();
        PersonDto personDto = personService.getPersonDetail(personId);
        if(personDto != null) {
            responseDto.setData(personDto);
        } else {
            responseDto.setCodeMessage(ErrorCode.SERVICE_BLANK);
        }
        return responseDto;
    }
}
