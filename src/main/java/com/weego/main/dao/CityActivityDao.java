package com.weego.main.dao;

import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.CityActivity;

@Repository
public class CityActivityDao {

	private DB database = MongoConnectionFactory.getDatabase();

	public CityActivity getSpecifiedCity(String cityActivityId) {
		DBCollection collection = database.getCollection("activity");

		JacksonDBCollection<CityActivity, String> coll;
		coll = JacksonDBCollection.wrap(collection, CityActivity.class, String.class);

		// CityActivity cityActivity = coll.findOneById(cityActivityId);
		CityActivity cityActivity = coll.findOne();

		return cityActivity;

	}
}
