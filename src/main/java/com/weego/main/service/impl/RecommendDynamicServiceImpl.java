package com.weego.main.service.impl;

import com.weego.main.dao.RecommendDynamicDao;
import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.model.RecommendDynamic;
import com.weego.main.service.RecommendDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
@Service("recommendDynamicService")
public class RecommendDynamicServiceImpl implements RecommendDynamicService {

    @Autowired
    private RecommendDynamicDao recommendDynamicDao;

    @Override
    public List<RecommendHistoryDto> getSpecityRecommendDynamic(String cityId) {
        // TODO: 16-3-17
        return null;
    }
}
