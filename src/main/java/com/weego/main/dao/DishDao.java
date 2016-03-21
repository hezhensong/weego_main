package com.weego.main.dao;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Dish;

@Repository
public class DishDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public Dish getDishById(String id) {
		DBCollection collection = database.getCollection("dish");
		JacksonDBCollection<Dish, String> jackCollection = JacksonDBCollection
				.wrap(collection, Dish.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		return jackCollection.findOne(query);
	}
}
