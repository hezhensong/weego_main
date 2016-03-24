package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-23.
 */
public class PolicyType {

    @JsonProperty("attraction")
    List<ObjectId> attractionList;

    @JsonProperty("restaurant")
    List<ObjectId> restaurantList;

    @JsonProperty("shopping")
    List<ObjectId> shoppingList;

    @JsonProperty("shopping_cirlcle")
    List<ObjectId> shoppingCircleList;

    @JsonProperty("activity")
    List<ObjectId> activityList;

    @JsonProperty("pgc")
    List<ObjectId> pgcList;

    @JsonProperty("news")
    List<ObjectId> newsList;

    public List<ObjectId> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(List<ObjectId> attractionList) {
        this.attractionList = attractionList;
    }

    public List<ObjectId> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<ObjectId> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<ObjectId> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ObjectId> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<ObjectId> getShoppingCircleList() {
        return shoppingCircleList;
    }

    public void setShoppingCircleList(List<ObjectId> shoppingCircleList) {
        this.shoppingCircleList = shoppingCircleList;
    }

    public List<ObjectId> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ObjectId> activityList) {
        this.activityList = activityList;
    }

    public List<ObjectId> getPgcList() {
        return pgcList;
    }

    public void setPgcList(List<ObjectId> pgcList) {
        this.pgcList = pgcList;
    }

    public List<ObjectId> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<ObjectId> newsList) {
        this.newsList = newsList;
    }
}
