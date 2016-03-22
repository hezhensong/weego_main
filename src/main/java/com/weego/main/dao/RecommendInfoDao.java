package com.weego.main.dao;

import com.mongodb.*;
import com.weego.main.model.RecommendInfo;
import com.weego.main.util.DateUtil;
import org.bson.types.ObjectId;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
@Repository
public class RecommendInfoDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public List<RecommendInfo> getRecommendsAll() {
        DBCollection collection = database.getCollection("recommend_info");

        JacksonDBCollection<RecommendInfo, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                        RecommendInfo.class,
                        String.class);
        return jacksonDBCollection.find().toArray();
    }

    //获取特定的recommendDynamic
    public List<RecommendInfo> getRecommendsByCityId(String cityId) {
        DBCollection collection = database.getCollection("recommend_info");

        JacksonDBCollection<RecommendInfo, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                                        RecommendInfo.class,
                                        String.class);
        return jacksonDBCollection.find().is("city_id", new ObjectId(cityId)).toArray();
    }


    public List<RecommendInfo> getRecomendsSpecifyDay(String cityId, Date date) {
        DBCollection collection = database.getCollection("recommend_info");
            JacksonDBCollection<RecommendInfo, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                                        RecommendInfo.class,
                                        String.class);
        //将时间转化为UTC时间
        date = DateUtil.covertTimeToUTC(date);
        Date nextDay  = DateUtil.afterNDays(date, 1);

        DBObject orderBy = new BasicDBObject();
        orderBy.put("end_time", 1);
        return jacksonDBCollection.find(DBQuery.and(
                                            DBQuery.is("city_id", new ObjectId(cityId)),
                                            DBQuery.greaterThanEquals("end_time", date),
                                            DBQuery.lessThan("end_time", nextDay))
                                        ).sort(orderBy).toArray();
    }
}
