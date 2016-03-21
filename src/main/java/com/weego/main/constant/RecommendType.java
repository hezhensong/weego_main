package com.weego.main.constant;

/**
 * Created by liuniandxx on 16-3-19.
 */
public enum RecommendType {
    ATTRACTION("景点", 0), RESTAURANT("餐厅", 1), SHOPPING("购物", 2),
    SHOPPINGCIRCLE("购物圈", 3), ACTIVITY("城市活动", 4), PGC("pgc", 5),
    NEWS("新闻", 6);

    private String desc;
    private Integer type;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private RecommendType(String desc, Integer type) {
        this.desc = desc;
        this.type = type;
    }

}
