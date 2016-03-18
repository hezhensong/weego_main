package com.weego.main.service.impl;

import com.weego.main.dao.RecommendDynamicDao;
import com.weego.main.dto.RecommendHistoryDto;
import com.weego.main.model.RecommendDynamic;
import com.weego.main.service.RecommendDynamicService;
import com.weego.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 */
@Service("recommendDynamicService")
public class RecommendDynamicServiceImpl implements RecommendDynamicService {
    private Logger logger = LogManager.getLogger(RecommendDynamicServiceImpl.class);

    @Autowired
    private RecommendDynamicDao recommendDynamicDao;

    @Override
    public RecommendHistoryDto getRecommendHistory(String cityId) {
        RecommendHistoryDto historyDto = new RecommendHistoryDto();
        historyDto.setCode(0);
        historyDto.setMessage("success");
        logger.info("----查询当天的推荐记录----");
        Date today = DateUtil.yyyyMMdd(new Date());
        logger.info("today = {}", DateUtil.formatyyyyMMdd(today));
        return null;
    }
}
