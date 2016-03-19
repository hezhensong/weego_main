package com.weego.main.dto;

import java.util.List;
import java.util.Map;

public class CityHomeLabelDto {

    private List<Map<String, String>> attractionLabel;
    private List<Map<String, String>> restaurantLabel;
    private List<Map<String, String>> shoppingLabel;

    public List<Map<String, String>> getAttractionLabel() {
        return attractionLabel;
    }

    public void setAttractionLabel(List<Map<String, String>> attractionLabel) {
        this.attractionLabel = attractionLabel;
    }

    public List<Map<String, String>> getRestaurantLabel() {
        return restaurantLabel;
    }

    public void setRestaurantLabel(List<Map<String, String>> restaurantLabel) {
        this.restaurantLabel = restaurantLabel;
    }

    public List<Map<String, String>> getShoppingLabel() {
        return shoppingLabel;
    }

    public void setShoppingLabel(List<Map<String, String>> shoppingLabel) {
        this.shoppingLabel = shoppingLabel;
    }
}
