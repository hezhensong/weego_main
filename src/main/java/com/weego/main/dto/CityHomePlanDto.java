package com.weego.main.dto;


public class CityHomePlanDto {

    private boolean havePlan;
    private String dateRange;

    public boolean isHavePlan() {
        return havePlan;
    }

    public void setHavePlan(boolean havePlan) {
        this.havePlan = havePlan;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
}
