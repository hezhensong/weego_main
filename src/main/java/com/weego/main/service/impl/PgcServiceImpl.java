package com.weego.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weego.main.dao.PgcDao;
import com.weego.main.dto.PgcListDto;
import com.weego.main.model.Pgc;
import com.weego.main.service.PgcService;

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
//		List<PgcListDto> pgcListDtoList = new ArrayList<>();
//
//		List<Pgc> pgcList = pgcDao.getOnlineCity();
//		for (Pgc pgc : pgcList) {
//		}
//
//		return pgcListDtoList;
        return pgcDao.getOnlinePgc();
    }

}
