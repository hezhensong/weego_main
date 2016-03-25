package com.weego.main.dto;

import java.util.List;

/**
 * Created  by hezhensong on 2016-03-25 下午 04:04.
 */
public class RecommendNewsDetailDto {

    private String lead;

    private String leadText;

    private List<RecommendNewsDetailContentDto> contents;

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

    public List<RecommendNewsDetailContentDto> getContents() {
        return contents;
    }

    public void setContents(List<RecommendNewsDetailContentDto> contents) {
        this.contents = contents;
    }
}
