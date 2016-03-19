package com.weego.main.service;

import com.weego.main.dto.PgcListDto;
import org.springframework.web.servlet.ModelAndView;

public interface PgcService {


	PgcListDto getPgcList();

	ModelAndView getSpecifiedPgc(String pgcId);
}
