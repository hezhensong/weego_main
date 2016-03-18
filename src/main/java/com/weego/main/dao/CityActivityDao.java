package com.weego.main.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.CityActivity;

@Repository
public class CityActivityDao {

    private DB database = MongoConnectionFactory.getDatabase();

    public CityActivity getSpecifiedCity(String cityActivityId) {
        
        DBCollection collection = database.getCollection("activity");

        JacksonDBCollection<CityActivity, String> coll;
        coll = JacksonDBCollection.wrap(collection, CityActivity.class, String.class);

        System.out.println("Dao层开始获取cityActivity");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(cityActivityId));
        List<CityActivity> CityActivityList = coll.find(query).toArray();

        if (CityActivityList.size() > 0) {
            return CityActivityList.get(0);
        } else {
            return null;
        }

    }
}
