package com.weego.main.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.City;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CityDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public List<City> getOnlineCity() {
        DBCollection collection = database.getCollection("city");

        JacksonDBCollection<City, String> jackCollection =
                JacksonDBCollection.wrap(collection, City.class, String.class);

        return jackCollection.find().is("is_show", true).toArray();
    }

    public City getSpecifiedCity(String cityId) {
        DBCollection collection = database.getCollection("city");

        JacksonDBCollection<City, String> coll;
        coll = JacksonDBCollection.wrap(collection, City.class, String.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(cityId));
        List<City> cityList = coll.find(query).toArray();

        if (cityList != null && cityList.size() > 0) {
            return cityList.get(0);
        } else {
            return null;
        }
    }
}
