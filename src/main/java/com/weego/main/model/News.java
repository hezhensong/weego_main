package com.weego.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongojack.Id;

import java.util.List;

/**
 * Created by liuniandxx on 16-3-24.
 */
public class News {

    @Id
    private ObjectId id;

    @JsonProperty("lead")
    private String lead;

    @JsonProperty("lead_text")
    private String leadText;

    @JsonProperty("news_content")
    private List<NewsContent> newsContentList;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getLeadText() {
        return leadText;
    }

    public void setLeadText(String leadText) {
        this.leadText = leadText;
    }

    public List<NewsContent> getNewsContentList() {
        return newsContentList;
    }

    public void setNewsContentList(List<NewsContent> newsContentList) {
        this.newsContentList = newsContentList;
    }
}
