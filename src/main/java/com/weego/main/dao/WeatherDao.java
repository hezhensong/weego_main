package com.weego.main.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Weather;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeatherDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public Weather getWeatherByCityId(String cityId) {
        DBCollection collection = database.getCollection("weather");

        JacksonDBCollection<Weather, String> coll;
        coll = JacksonDBCollection.wrap(collection, Weather.class, String.class);

        BasicDBObject query = new BasicDBObject();
        query.put("city_id", new ObjectId(cityId));
        List<Weather> cityList = coll.find(query).toArray();

        if (cityList != null && cityList.size() > 0) {
            return cityList.get(0);
        } else {
            return null;
        }
    }
}
