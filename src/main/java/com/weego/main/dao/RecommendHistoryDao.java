package com.weego.main.dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.weego.main.model.City;
import com.weego.main.model.RecommendHistory;
import com.weego.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class RecommendHistoryDao {
    private Logger logger = LogManager.getLogger(RecommendHistoryDao.class);

    private DB database = MongoConnectionFactory.getDatabase();

    @Autowired
    private CityDao cityDao;

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
     * @return         time当前用户userId在城市cityId的推荐记录
     */
    public List<RecommendHistory> getRecommendHistoryByTime(String cityId, String userId, String time) {
        logger.info("cityId = {}, userId = {}, time = {}", cityId, userId, time);

        City city = cityDao.getSpecifiedCity(cityId);
        String cityTimeZone = city.getTimezone();
        logger.info("cityTimeZone = {}", cityTimeZone);

        Date date = DateUtil.yyyyMMddToDate(time, cityTimeZone);
        logger.info("当前日期 date = {}, 毫秒时间 ms = {}", date, date.getTime());
        Date nextDay = DateUtil.afterNDays(date, 1);
        logger.info("下一天 nextDay = {}, 毫秒时间 = {}",nextDay, nextDay.getTime());

        DBCollection collection = database.getCollection("recommend_history");

        JacksonDBCollection<RecommendHistory, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                        RecommendHistory.class,
                        String.class);
        DBObject orderBy = new BasicDBObject();
        orderBy.put("recommend_time", -1);
        return jacksonDBCollection.find(DBQuery.and(
                DBQuery.is("city_id", new ObjectId(cityId)),
                DBQuery.is("user_id", new ObjectId(userId)),
                DBQuery.greaterThanEquals("recommend_time", date),
                DBQuery.lessThan("recommend_time", nextDay)
                )).sort(orderBy).toArray();
    }
}
