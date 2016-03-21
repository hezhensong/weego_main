package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.WeatherTranslation;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WeatherTranslationDao {

    private DB database = MongoConnectionFactory.getDatabase();

    public Map<String, String> getWeatherTranslation() {
        DBCollection collection = database.getCollection("weather_translation");

        JacksonDBCollection<WeatherTranslation, String> jackCollection =
                JacksonDBCollection.wrap(collection, WeatherTranslation.class, String.class);

        Map<String, String> WeatherTranslationMap = new HashMap<>();
        for (WeatherTranslation translation : jackCollection.find().toArray()) {
            WeatherTranslationMap.put(translation.getEnglish(), translation.getChinese());
        }

        return WeatherTranslationMap;
    }
}
