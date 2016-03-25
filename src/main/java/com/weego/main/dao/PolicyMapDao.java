package com.weego.main.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.PolicyMap;
import org.bson.types.ObjectId;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

/**
 * Created by liuniandxx on 16-3-24.
 */
@Repository
public class PolicyMapDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public  PolicyMap getPolicyMap(String policyId, int type, String contentId) {
        DBCollection collection = database.getCollection("recommend_history");

        JacksonDBCollection<PolicyMap, String> jacksonDBCollection = JacksonDBCollection.wrap(collection,
                                                                                                PolicyMap.class,
                                                                                                String.class);
        return jacksonDBCollection.findOne(DBQuery.and(
                                            DBQuery.is("policy_id", new ObjectId(policyId)),
                                            DBQuery.is("type", type),
                                            DBQuery.is("content_id", new ObjectId(contentId))
                                            ));
    }
}
