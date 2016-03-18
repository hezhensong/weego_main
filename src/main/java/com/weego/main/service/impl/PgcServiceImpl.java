package com.weego.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.PgcDao;
import com.weego.main.dto.PgcListDto;
import com.weego.main.model.Pgc;
import com.weego.main.service.PgcService;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pgcService")
public class PgcServiceImpl implements PgcService {

    @Autowired
    private PgcDao pgcDao;

    @Override
    public List<Pgc> getPgcList() {
        return pgcDao.getOnlinePgc();
    }

    @Override
    public ModelAndView getSpecifiedPgc(String pgcId) {

        Pgc pgc = pgcDao.getSpecifiedPgc(pgcId);
        if (pgc == null) {
            return null;
        }

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
