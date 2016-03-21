package com.weego.main.dao;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Spot;

@Repository
public class SpotDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public Spot getSpotById(String id) {
		DBCollection collection = database.getCollection("spot");
		JacksonDBCollection<Spot, String> jackCollection = JacksonDBCollection
				.wrap(collection, Spot.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		return jackCollection.findOne(query);
	}
}
