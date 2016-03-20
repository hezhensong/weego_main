package com.weego.main.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Shopping;

@Repository
public class ShoppingDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public List<Shopping> getShoppingsByCityId(String cityId, String labelId) {
		DBCollection collection = database.getCollection("shopping");
		JacksonDBCollection<Shopping, String> jackCollection = JacksonDBCollection
				.wrap(collection, Shopping.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("city_id", new ObjectId(cityId));
		return jackCollection.find(query).toArray();
	}

	public Shopping getShoppingById(String id) {
		DBCollection collection = database.getCollection("shopping");
		JacksonDBCollection<Shopping, String> jackCollection = JacksonDBCollection
				.wrap(collection, Shopping.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		return jackCollection.findOne(query);
	}
}
