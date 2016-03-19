package com.weego.main.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.People;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PeopleDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public List<People> getListPgcPerson(String personId) {//查询person中的userName、headImage
        DBCollection collection = database.getCollection("people");
        JacksonDBCollection<People, String> jackCollection = JacksonDBCollection.wrap(collection, People.class, String.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(personId));
        List<People> personPgc = jackCollection.find(query).toArray();

        return personPgc;
    }


}
