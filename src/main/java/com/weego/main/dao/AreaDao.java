package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Area;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class AreaDao {

    private DB database = MongoConnectionFactory.getDatabase();

    public Map<Integer, Area> getArea() {
        DBCollection collection = database.getCollection("area");

        JacksonDBCollection<Area, String> jackCollection =
                JacksonDBCollection.wrap(collection, Area.class, String.class);

        Map<Integer, Area> areaMap = new TreeMap<>();
        for (Area area : jackCollection.find().toArray()) {
            areaMap.put(area.getOrder(), area);
        }

        return areaMap;
    }
}
