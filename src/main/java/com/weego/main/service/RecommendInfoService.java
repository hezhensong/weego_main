package com.weego.main.service;

import com.weego.main.dto.BaseCardDto;
import com.weego.main.dto.RecommendHistoryDto;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
public interface RecommendInfoService {
     List<BaseCardDto> getRecommendCards(String cityId, String coordinate, String time);
}
