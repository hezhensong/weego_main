package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Pgc;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PgcDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public List<Pgc> getOnlinePgc() {
        DBCollection collection = database.getCollection("pgc");

        JacksonDBCollection<Pgc, String> jackCollection = JacksonDBCollection.wrap(collection, Pgc.class, String.class);

        return jackCollection.find().toArray();
    }

}
