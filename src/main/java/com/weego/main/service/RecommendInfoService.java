package com.weego.main.service;

import com.weego.main.dto.RecommendCardDto;
import com.weego.main.dto.RecommendHistoryDto;

/**
 * Created by liuniandxx on 16-3-16.
 */
public interface RecommendInfoService {
     RecommendHistoryDto getRecommendHistory(String cityId);

     RecommendCardDto getRecommendCards(String cityId, String coordinate, String time);
}
