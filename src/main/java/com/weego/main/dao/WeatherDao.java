package com.weego.main.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.weego.main.model.Weather;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WeatherDao {
    private MongoDatabase database = MongoConnectionFactory.getDatabase();

    public List<Weather> getAllCityWeather() {
        List<Weather> weatherList = new ArrayList<>();

        MongoCollection<Document> collection = database.getCollection("weather");
        FindIterable<Document> iterable = collection.find();

        for (Document document : iterable) {
            Weather weather = new Weather();

            org.bson.types.ObjectId _id = (org.bson.types.ObjectId) document.get("_id");
            weather.setId(_id.toString());

            org.bson.types.ObjectId city_id = (org.bson.types.ObjectId) document.get("city_id");
            weather.setCityId(city_id.toString());

            String timestamp = (String) document.get("timestamp");
            weather.setTimestamp(timestamp);

            String yahooWeather = (String) document.get("yahoo_weather");
            weather.setYahooWeather(yahooWeather);

            weatherList.add(weather);
        }

        return weatherList;
    }
}
