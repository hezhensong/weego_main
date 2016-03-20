package com.weego.main.model;

import java.util.Date;

/**
 * Created  by Eric He on 2016-03-19 下午 05:25.
 * Modified by Eric He on 2016-03-19 下午 05:25.
 */
public class WeatherForecast {

    private Date date;
    private int high;
    private int low;
    private String description;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
