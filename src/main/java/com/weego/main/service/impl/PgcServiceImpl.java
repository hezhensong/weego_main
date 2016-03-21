package com.weego.main.service.impl;

import com.weego.main.constant.RecommendType;
import com.weego.main.dao.PeopleDao;
import com.weego.main.dto.*;
import com.weego.main.model.Person;
import com.weego.main.model.PgcPoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.PgcDao;
import com.weego.main.model.Pgc;
import com.weego.main.service.PgcService;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

@Service("pgcService")
public class PgcServiceImpl implements PgcService {

    @Autowired
    private PgcDao pgcDao;

    @Autowired
    private PeopleDao peopleDao;

    @Override
    public PgcListDto getPgcList() {

        PgcListDto pgcListDto = new PgcListDto();

        List<PgcListContinentDto> pgcListContinentDto = new ArrayList<>();

        List<Pgc> pgcList = pgcDao.getPgcDaoList();

        for (Pgc pgc : pgcList) {
            PgcListContinentDto pgcDtoList = new PgcListContinentDto();

            if (pgc.getPerson() != null) {
                List<Person> pgcPersonList = peopleDao.getPgcPersonList(pgc.getPerson());
                for (Person person : pgcPersonList) {
                    pgcDtoList.setUserName(person.getUserName());//人物名称
                    pgcDtoList.setHeadImage(person.getHeadImage());//人物头像
                }
            } else {
                pgcDtoList.setUserName("");//人物名称
                pgcDtoList.setHeadImage("");//人物头像
            }

            pgcDtoList.setPgcId(pgc.getPgcId());

            pgcDtoList.setTitle(pgc.getTitle());

            pgcDtoList.setCoverImage(pgc.getCoverImage());

            pgcListContinentDto.add(pgcDtoList);

        }

        pgcListDto.setData(pgcListContinentDto);

        return pgcListDto;
    }

    @Override
    public ModelAndView getSpecifiedPgc(String pgcId) {

        Pgc pgc = pgcDao.getSpecifiedPgc(pgcId);
        if (pgc == null) {
            return null;
        } else {

            ModelAndView mv = new ModelAndView("PGC");
            mv.addObject("author", "未名");
            mv.addObject("resource_1", "Weego公众号");
            mv.addObject("article", "weego基于旅行者的兴趣和意图依托强大的数据库的智能计算，帮助用户在简单旅行准备的同时，成为一个旅行中的实时向导。");

            mv.addObject("head", "../resource/img/pgc/2.jpg");
            mv.addObject("pic_3", "../resource/img/pgc/2.jpg");

            mv.addObject("title_1", "主标题1");
            mv.addObject("article_1", "马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合。");
            mv.addObject("pic_4", "../resource/img/pgc/1.jpg");
            mv.addObject("resource_2", "");

            mv.addObject("title_2", "主标题2");
            mv.addObject("article_1", "马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合马尔代夫不适合。");
            mv.addObject("pic_5", "../resource/img/pgc/2.jpg");
            mv.addObject("resource_3", "");

            mv.addObject("title_4", "");
            mv.addObject("logo", "../resource/img/pgc/3.jpg");

            return mv;
        }

    }

    @Override
    public PgcDetailDto getPgcDetail(String pgcId) {
        PgcDetailDto pgcDetailDto = new PgcDetailDto();

        Pgc pgc = pgcDao.getSpecifiedPgc(pgcId);
        pgcDetailDto.setCoverImage(pgc.getCoverImage());
        pgcDetailDto.setTitle(pgc.getTitle());

        if (Strings.isNullOrEmpty(pgc.getPerson())) {
            PgcPeopleDto pgcPeopleDto = new PgcPeopleDto();
            pgcPeopleDto.setId("");
            pgcPeopleDto.setHeadImage("");
            pgcPeopleDto.setUserName("");
            pgcPeopleDto.setJobDesc("");

            pgcDetailDto.setPgcPeople(pgcPeopleDto);
        } else {
            Person person = peopleDao.getPersonById(pgc.getPerson().trim());
            PgcPeopleDto pgcPeopleDto = new PgcPeopleDto();
            pgcPeopleDto.setId(person.getPersonId());
            pgcPeopleDto.setHeadImage(person.getHeadImage());
            pgcPeopleDto.setUserName(person.getUserName());
            pgcPeopleDto.setJobDesc(person.getJobDesc());

            pgcDetailDto.setPgcPeople(pgcPeopleDto);
        }


        pgcDetailDto.setIntroduction(pgc.getIntroduction());

        List<PgcPoi> pgcPois = pgc.getPoiList();
        List<BasePoiDto> pgcPoiDtos = new ArrayList<BasePoiDto>();
        if(pgcPois != null && pgcPois.size() > 0) {
            for(PgcPoi elem : pgcPois) {
                if(isSpecifyType(elem.getType())) {
                    PgcPoiDto pgcPoiDto = new PgcPoiDto();
                    pgcPoiDto.setId(elem.getId());
                    pgcPoiDto.setType(elem.getType());
                    pgcPoiDto.setPoiDes(elem.getPoiDesc());
                    pgcPoiDto.setPoiImage(elem.getPoiImage());
                    pgcPoiDto.setName(Strings.nullToEmpty(elem.getName()));

                    pgcPoiDtos.add(pgcPoiDto);
                } else {
                    BasePoiDto poiDto = new BasePoiDto();
                    poiDto.setType(elem.getType());
                    poiDto.setId(elem.getId());
                    poiDto.setPoiDes(elem.getPoiDesc());
                    poiDto.setPoiImage(elem.getPoiImage());

                    pgcPoiDtos.add(poiDto);
                }

            }
        }
        pgcDetailDto.setPgcPoi(pgcPoiDtos);
        return pgcDetailDto;
    }

    private Boolean isSpecifyType(String type) {
        if(Strings.isNullOrEmpty(type)) {
            return false;
        }

        Integer intType = Integer.parseInt(type.trim());
        if(intType.equals(RecommendType.ATTRACTION.getType())
                || intType.equals(RecommendType.RESTAURANT.getType())
                || intType.equals(RecommendType.SHOPPING.getType())
                || intType.equals(RecommendType.SHOPPINGCIRCLE.getType())) {
            return true;
        }

        return false;

    }
}