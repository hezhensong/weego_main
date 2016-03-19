package com.weego.main.dto;

/**
 * Created by liuniandxx on 16-3-19.
 */
public class RestaurantCardDto extends BaseCardDto {
    private String restaurantName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
