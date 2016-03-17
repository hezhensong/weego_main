package com.weego.main.controller.api;

import com.weego.main.dao.PgcDao;
import com.weego.main.model.Pgc;
import com.weego.main.service.impl.PgcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v3/city/pgc")
public class PgcController {

    @Autowired
    PgcServiceImpl pgcService;

    @Autowired
    PgcDao pgcDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Pgc> getPgcList() {
        return pgcService.getPgcList();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Pgc getPgcTest(@RequestParam("pgcId") String pgcId) {
        return pgcDao.getSpecifiedPgc(pgcId);
    }

}
