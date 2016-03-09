package com.weego.main.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.weego.main.model.City;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CityDao {
    private MongoDatabase database = MongoConnectionFactory.getDatabase();

    public List<City> getOnlineCity() {
        MongoCollection<Document> collection = database.getCollection("city");
        FindIterable<Document> iterable = collection.find(new Document("is_show", true));

        return mapper(iterable);
    }

    public City getSpecifiedCity(String cityId) {
        MongoCollection<Document> collection = database.getCollection("city");
        FindIterable<Document> iterable = collection.find(new Document("_id", new org.bson.types.ObjectId(cityId)));

        List<City> cityList = mapper(iterable);
        if (cityList.size() > 0) {
            return cityList.get(0);
        } else {
            return null;
        }
    }

    private List<City> mapper(FindIterable<Document> iterable) {
        List<City> cityList = new ArrayList<>();

        for (Document document : iterable) {
            City city = new City();

            org.bson.types.ObjectId _id = (org.bson.types.ObjectId) document.get("_id");
            city.setId(_id.toString());

            String cityName = (String) document.get("city_name");
            city.setCityName(cityName);

            String cityNameEn = (String) document.get("city_name_en");
            city.setCityNameEn(cityNameEn);

            String cityNamePy = (String) document.get("city_name_py");
            city.setCityNamePy(cityNamePy);

            boolean isShow = (boolean) document.get("is_show");
            city.setShow(isShow);

            cityList.add(city);
        }

        return cityList;
    }
}
