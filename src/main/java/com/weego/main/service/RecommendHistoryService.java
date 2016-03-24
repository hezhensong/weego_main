package com.weego.main.service;

import com.weego.main.dto.RecommendHistoryDto;

/**
 * Created by liuniandxx on 16-3-23.
 */
public interface RecommendHistoryService {
    RecommendHistoryDto getRecommendHistory(String cityId, String userId);
}
