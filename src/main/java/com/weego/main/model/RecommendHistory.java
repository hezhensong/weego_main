package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class RecommendHistory {
    @Id
    private ObjectId id;

    @JsonProperty("city_id")
    private ObjectId cityId;

    @JsonProperty("user_id")
    private ObjectId userId;

    @JsonProperty("recommend_time")
    private Date recommendTime;

    @JsonProperty("recommend_content")
    private List<RecommendContent> recommendContentList;
}
