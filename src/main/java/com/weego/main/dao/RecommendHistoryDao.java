package com.weego.main.dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.RecommendHistory;
import org.bson.types.ObjectId;
import org.mongojack.JacksonDBCollection;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class RecommendHistoryDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public RecommendHistory getRecommendHistoryById(String id) {
        DBCollection collection = database.getCollection("recommend_history");

        JacksonDBCollection<RecommendHistory, String> jacksonDBCollection =
                JacksonDBCollection.wrap(collection,
                        RecommendHistory.class,
                        String.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        return jacksonDBCollection.findOne(query);
    }

    public List<RecommendHistory> getRecommendHistoryByTime(String cityId, String time) {
        return null;
    }
}
