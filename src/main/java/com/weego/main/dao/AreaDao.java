package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Area;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AreaDao {

    private DB database = MongoConnectionFactory.getDatabase();

    public Map<String, Area> getArea() {
        DBCollection collection = database.getCollection("area");

        JacksonDBCollection<Area, String> jackCollection =
                JacksonDBCollection.wrap(collection, Area.class, String.class);

        Map<String, Area> areaMap = new HashMap<>();
        for (Area area : jackCollection.find().toArray()) {
            areaMap.put(area.getName(), area);
        }

        return areaMap;
    }
}
