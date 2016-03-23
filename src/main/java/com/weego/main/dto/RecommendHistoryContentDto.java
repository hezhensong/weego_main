package com.weego.main.dto;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendHistoryContentDto {

    private Integer type;

    private String time;

    private String coverImage;

    private String contentTitle;

    private String contentFirst;

    private String contentSecond;

    private String contentId;

    private String contentUrl;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentFirst() {
        return contentFirst;
    }

    public void setContentFirst(String contentFirst) {
        this.contentFirst = contentFirst;
    }

    public String getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(String contentSecond) {
        this.contentSecond = contentSecond;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
