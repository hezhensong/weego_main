package com.weego.main.service;

import com.weego.main.dto.RecommendCardDto;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
public interface RecommendCardService {
     List<RecommendCardDto> getRecommendCards(String cityId, String coordinate, String time);
}
