package com.weego.main.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Restaurant;

@Repository
public class RestaurantDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public List<Restaurant> getRestaurantsByCityId(String cityId, String labelId) {
		DBCollection collection = database.getCollection("restaurant");

		JacksonDBCollection<Restaurant, String> jackCollection = JacksonDBCollection
				.wrap(collection, Restaurant.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("city_id", new ObjectId(cityId));
		return jackCollection.find(query).toArray();
	}
	
	public Restaurant getRestaurantById(String id) {
		DBCollection collection = database.getCollection("restaurant");
		JacksonDBCollection<Restaurant, String> jackCollection = JacksonDBCollection
				.wrap(collection, Restaurant.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		return jackCollection.findOne(query);
	}
	
	public List<Restaurant> getRestaurantsByCityIdAndCoordination(String cityId, String coordination) {
		DBCollection collection = database.getCollection("restaurant");

		JacksonDBCollection<Restaurant, String> jackCollection = JacksonDBCollection
				.wrap(collection, Restaurant.class, String.class);
		BasicDBObject query = new BasicDBObject();
		query.put("city_id", new ObjectId(cityId));
//		query.put("", "");   经纬度的处理
		return jackCollection.find(query).toArray();
	}
}
