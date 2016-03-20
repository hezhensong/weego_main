package com.weego.main.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Attraction;

@Repository
public class AttractionDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public List<Attraction> getAttractionsByCityId(String cityId, String labelId) {
		DBCollection collection = database.getCollection("attraction");
		JacksonDBCollection<Attraction, String> jackCollection = JacksonDBCollection
				.wrap(collection, Attraction.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("city_id", new ObjectId(cityId));
		query.put("master_label._id",new ObjectId(labelId));
		return jackCollection.find(query).toArray();
	}
	
	public Attraction getAttractionById(String id) {
		DBCollection collection = database.getCollection("attraction");
		JacksonDBCollection<Attraction, String> jackCollection = JacksonDBCollection
				.wrap(collection, Attraction.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		return jackCollection.findOne(query);
	}

}
