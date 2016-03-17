package com.weego.main.service;

import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.model.RecommendDynamic;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
public interface RecommendDynamicService {
     RecommendHistoryDto getRecommendHistory(String cityId);
}
