package com.weego.main.dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.RecommendHistory;
import com.weego.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class RecommendHistoryDao {
    private Logger logger = LogManager.getLogger(RecommendHistoryDao.class);

    private DB database = MongoConnectionFactory.getDatabase();

    public RecommendHistory getRecommendHistoryById(String id) {
        DBCollection collection = database.getCollection("recommend_history");

        JacksonDBCollection<RecommendHistory, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                        RecommendHistory.class,
                        String.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        return jacksonDBCollection.findOne(query);
    }

    /**
     * 查询历史推荐记录
     * @param cityId   城市Id
     * @param userId   用户Id
     * @param time     查询时间（表示是那一天的推荐记录）
     * @return
     */
    public List<RecommendHistory> getRecommendHistoryByTime(String cityId, String userId, String time) {
        logger.info("cityId = {}, userId = {}, time = {}", cityId, userId, time);

        Date date = DateUtil.yyyyMMddToDate(time);
        logger.info("当前日期 date = {}, 毫秒时间 ms = {}", date, date.getTime());


        return null;
    }
}
