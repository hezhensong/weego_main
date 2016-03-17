package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.RecommendDynamic;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-16.
 */
@Repository
public class RecommendDynamicDao {
    private DB database = MongoConnectionFactory.getDatabase();

    //获取特定的recommendDynamic
    public List<RecommendDynamic> getRecommendsByCityId(String cityId) {
        DBCollection collection = database.getCollection("recommendDynamic");

        JacksonDBCollection<RecommendDynamic, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                                        RecommendDynamic.class,
                                        String.class);
        return jacksonDBCollection.find().is("city_id", cityId).toArray();
    }


    public List<RecommendDynamic> getRecomendsSpecifyDay(String cityId, Date date) {
        DBCollection collection = database.getCollection("recommendDynamic");

            JacksonDBCollection<RecommendDynamic, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                                        RecommendDynamic.class,
                                        String.class);
        return jacksonDBCollection.find(DBQuery.and(
                                            DBQuery.is("city_id", cityId),
                                            DBQuery.is("recommend_end_date", date)
                                        )).toArray();
    }
}
