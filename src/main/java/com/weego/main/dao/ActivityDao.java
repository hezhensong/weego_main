package com.weego.main.dao;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.weego.main.model.Activity;
import com.weego.main.util.DateUtil;

@Repository
public class ActivityDao {

    private DB database = MongoConnectionFactory.getDatabase();

    public Activity getSpecifiedCity(String cityActivityId) {
        DBCollection collection = database.getCollection("activity");

        JacksonDBCollection<Activity, String> coll;
        coll = JacksonDBCollection.wrap(collection, Activity.class, String.class);

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(cityActivityId));
        List<Activity> CityActivityList = coll.find(query).toArray();

        if (CityActivityList.size() > 0) {
            return CityActivityList.get(0);
        } else {
            return null;
        }
    }

    public List<Activity> getAllActivity(String cityId) {
        DBCollection collection = database.getCollection("activity");

        JacksonDBCollection<Activity, String> coll;
        coll = JacksonDBCollection.wrap(collection, Activity.class, String.class);

        // 获取当前时间并转成数据库时间
        Date date = new Date();
        Date dateNow = DateUtil.covertTimeToUTC(date);
        
       // 获取已经开始30天之内的活动
        Date dateThirty = DateUtil.afterNDays(date, -30);
        DBObject orderBy = new BasicDBObject();
        orderBy.put("start_time", 1);

        // 按照活动开始日期由近到远.
        return coll.find(DBQuery.and(
                DBQuery.is("city_id", new ObjectId(cityId)),
                DBQuery.greaterThanEquals("end_time", dateNow),
                DBQuery.greaterThanEquals("start_time", dateThirty)
        )).sort(orderBy).toArray();
    }
}
