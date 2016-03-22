package com.weego.main.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Person;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PeopleDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public List<Person> getPgcPersonList(String personId) {//查询person中的userName、headImage
        DBCollection collection = database.getCollection("person");
        JacksonDBCollection<Person, String> jackCollection = JacksonDBCollection.wrap(collection, Person.class, String.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(personId));
        List<Person> personPgc = jackCollection.find(query).toArray();

        return personPgc;
    }

    public Person getPersonById(String personId) {
        DBCollection collection = database.getCollection("person");
        JacksonDBCollection<Person, String> jackCollection = JacksonDBCollection.wrap(collection, Person.class, String.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(personId));
        Person person = jackCollection.findOne(query);
        return person;
    }
}
