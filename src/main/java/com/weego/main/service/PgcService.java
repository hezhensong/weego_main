package com.weego.main.service;

import com.weego.main.dto.PgcDetailDto;
import com.weego.main.dto.PgcListDto;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface PgcService {


	List<PgcListDto> getPgcList(String cityId);

	ModelAndView getSpecifiedPgc(String pgcId);

	PgcDetailDto getPgcDetail(String pgcId);
}
