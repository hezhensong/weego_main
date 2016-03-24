package com.weego.main.service;

import com.weego.main.dto.PersonDto;
import com.weego.main.dto.ResponseDto;

/**
 * Created by liuniandxx on 16-3-21.
 */
public interface PersonService {
    /**
     * 获取人物详情
     * @param personId 人物Id
     * @return
     */
    PersonDto getPersonDetail(String personId);
}
