package com.weego.main.dto;

import com.weego.main.model.RecommendContent;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendInfoDto {
    private String id;

    private Integer type;

    private Date time;

    private RecommendContentDto content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public RecommendContentDto getContent() {
        return content;
    }

    public void setContent(RecommendContentDto content) {
        this.content = content;
    }
}
