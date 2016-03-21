package com.weego.main.dao;

import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Brand;

@Repository
public class BrandDao {
	private DB database = MongoConnectionFactory.getDatabase();

	public Brand getBrandById(String id) {
		DBCollection collection = database.getCollection("brand");
		JacksonDBCollection<Brand, String> jackCollection = JacksonDBCollection
				.wrap(collection, Brand.class, String.class);
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		return jackCollection.findOne(query);
	}
}
