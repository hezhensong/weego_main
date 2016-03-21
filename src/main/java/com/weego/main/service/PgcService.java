package com.weego.main.service;

import com.weego.main.dto.PgcDetailDto;
import com.weego.main.dto.PgcListDto;
import com.weego.main.dto.PgcPoiDto;
import org.springframework.web.servlet.ModelAndView;

public interface PgcService {


	PgcListDto getPgcList();

	ModelAndView getSpecifiedPgc(String pgcId);

	PgcDetailDto getPgcDetail(String pgcId);
}
