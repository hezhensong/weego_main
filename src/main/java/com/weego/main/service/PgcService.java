package com.weego.main.service;

import com.weego.main.model.Pgc;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface PgcService {

	List<Pgc> getPgcList();

	ModelAndView getSpecifiedPgc(String pgcId);
}
