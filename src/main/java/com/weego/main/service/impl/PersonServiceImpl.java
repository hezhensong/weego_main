package com.weego.main.service.impl;

import com.weego.main.dao.PeopleDao;
import com.weego.main.dto.PersonDto;
import com.weego.main.dto.PersonSimpleIntroduceDto;
import com.weego.main.model.PeopleSimpleIntroduce;
import com.weego.main.model.Person;
import com.weego.main.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuniandxx on 16-3-21.
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PeopleDao peopleDao;

    @Override
    public PersonDto getPersonDetail(String personId) {
        PersonDto personDto = new PersonDto();
        Person person = peopleDao.getPersonById(personId);


        Map<String, Object> data = new HashMap<String, Object>();

        if(person != null) {
            data.put("headImage", person.getHeadImage());
            data.put("userName", person.getUserName());
            data.put("jobDesc", person.getJobDesc());

            List<PersonSimpleIntroduceDto> intros = new ArrayList<PersonSimpleIntroduceDto>();
            List<PeopleSimpleIntroduce> simpleIntroduces = person.getSimpleIntroduce();

            if(simpleIntroduces != null && simpleIntroduces.size() > 0) {
                for(PeopleSimpleIntroduce elem : simpleIntroduces) {
                    PersonSimpleIntroduceDto introduceDto = new PersonSimpleIntroduceDto();
                    introduceDto.setImage(elem.getImage());
                    introduceDto.setTitle(elem.getTitle());
                    introduceDto.setDesc(elem.getDesc());

                    intros.add(introduceDto);
                }
            }
            data.put("simpleIntroduce", intros);
        }
        return personDto;
    }
}
