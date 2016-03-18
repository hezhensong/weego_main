package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.TreeAreaContinent;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreeAreaDao {

    private DB database = MongoConnectionFactory.getDatabase();

    public List<TreeAreaContinent> getArea() {
        DBCollection collection = database.getCollection("area");

        JacksonDBCollection<TreeAreaContinent, String> jackCollection =
                JacksonDBCollection.wrap(collection, TreeAreaContinent.class, String.class);

        return jackCollection.find().toArray();
    }
}
