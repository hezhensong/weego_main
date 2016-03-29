package com.weego.main.service;

import com.weego.main.dto.RecommendCardDto;
import com.weego.main.dto.RecommendNewsDetailDto;

/**
 * Created  by liuniandxx on 16-3-16 下午 04:34.
 */
public interface RecommendCardService {
    RecommendCardDto getRecommendCards(String cityId, String userId, String coordinate, String time);

    RecommendNewsDetailDto getRecommendNewsById(String newsId);
}
