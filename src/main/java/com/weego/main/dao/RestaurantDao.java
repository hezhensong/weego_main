package com.weego.main.dao;

import java.util.List;

import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Restaurant;

@Repository
public class RestaurantDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public List<Restaurant> getRestaurantsByCityId(String cityId) {
		DBCollection collection = database.getCollection("restaurant");

		JacksonDBCollection<Restaurant, String> jackCollection = JacksonDBCollection
				.wrap(collection, Restaurant.class, String.class);

		return jackCollection.find().toArray();
	}
}
