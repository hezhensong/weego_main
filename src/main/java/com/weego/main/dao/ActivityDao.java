package com.weego.main.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Activity;

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

    public List<Activity> getAllActivity() {
        DBCollection collection = database.getCollection("activity");

        JacksonDBCollection<Activity, String> coll;
        coll = JacksonDBCollection.wrap(collection, Activity.class, String.class);
        //按照活动开始日期由近到远
        return coll.find().limit(10).sort(new BasicDBObject("start_time",-1)).toArray();
    }
}
