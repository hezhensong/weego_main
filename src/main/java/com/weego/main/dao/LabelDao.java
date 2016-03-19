package com.weego.main.dao;

import java.util.List;

import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Attraction;

@Repository
public class LabelDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public List<Attraction> getAttractionsByCityId(String cityId) {
		DBCollection collection = database.getCollection("label");
		JacksonDBCollection<Attraction, String> jackCollection = JacksonDBCollection
				.wrap(collection, Attraction.class, String.class);

		return jackCollection.find().toArray();
	}
}
