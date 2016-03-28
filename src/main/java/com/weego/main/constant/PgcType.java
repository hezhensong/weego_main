package com.weego.main.constant;

/**
 * Created by liuniandxx on 16-3-28.
 */
public enum PgcType {
    CELEBRITY(1, "名人推荐"), THINGS(2, "物品推荐"), PROFESSIONAL(3, "专业推荐");

    PgcType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;


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
}
