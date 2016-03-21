package com.weego.main.service;

import com.weego.main.dto.PgcDetailDto;
import com.weego.main.dto.PgcListContinentDto;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface PgcService {


	List<PgcListContinentDto> getPgcList();

	ModelAndView getSpecifiedPgc(String pgcId);

	PgcDetailDto getPgcDetail(String pgcId);
}
