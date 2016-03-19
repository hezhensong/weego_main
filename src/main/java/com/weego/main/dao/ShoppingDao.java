package com.weego.main.dao;

import java.util.List;

import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Shopping;

@Repository
public class ShoppingDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public List<Shopping> getShoppingsByCityId(String cityId) {
		DBCollection collection = database.getCollection("shopping");

		JacksonDBCollection<Shopping, String> jackCollection = JacksonDBCollection
				.wrap(collection, Shopping.class, String.class);

		return jackCollection.find().toArray();
	}
}
