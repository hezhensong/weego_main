package com.weego.main.dao;

import com.google.common.base.Strings;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.weego.main.model.Policy;
import com.weego.main.model.PolicyMap;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
@Repository
public class PolicyDao {
    private DB database = MongoConnectionFactory.getDatabase();

    public List<Policy> getPolicyByTime(String cityId, String time) {
        DBCollection collection = database.getCollection("policy");
        JacksonDBCollection<Policy, String> jacksonDBCollection = JacksonDBCollection.wrap(collection,
                Policy.class,
                String.class);

        long timedelta = Long.parseLong(time) * 1000;
        Date userDate = new Date(timedelta);
        List<Policy> policyList = jacksonDBCollection.find(DBQuery.and(
                                                        DBQuery.is("city_id", cityId),
                                                        DBQuery.is("status", true),
                                                        DBQuery.lessThanEquals("start_time", userDate),
                                                        DBQuery.greaterThanEquals("end_time", userDate)
                                                        )).toArray();
        return policyList;
    }

}
