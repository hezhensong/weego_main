package com.weego.main.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Pgc;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PgcDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public List<Pgc> getPgcList() {
        DBCollection collection = database.getCollection("pgc");
        JacksonDBCollection<Pgc, String> jackCollection = JacksonDBCollection.wrap(collection, Pgc.class, String.class);

        return jackCollection.find().toArray();
    }

    public List<Pgc> getPgcByCityId(String cityId) {
        DBCollection collection = database.getCollection("pgc");
        JacksonDBCollection<Pgc, String> jackCollection =
                JacksonDBCollection.wrap(collection, Pgc.class, String.class);

        BasicDBObject query = new BasicDBObject();
        query.put("city_id", new ObjectId(cityId));
        List<Pgc> pgcList = jackCollection.find(query).toArray();

        if (pgcList.size() > 0) {
            return pgcList;
        } else {
            return null;
        }
    }

    public Pgc getSpecifiedPgc(String pgcId) {
        DBCollection collection = database.getCollection("pgc");
        JacksonDBCollection<Pgc, String> jackCollection = JacksonDBCollection.wrap(collection, Pgc.class, String.class);

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(pgcId));
        List<Pgc> pgcList = jackCollection.find(query).toArray();

        if (pgcList.size() > 0) {
            return pgcList.get(0);
        } else {
            return null;
        }
    }
}
