package com.weego.main.dto;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class RecommendContentDto {
    private String coverImage;

    private String recommendContentId;

    private String recommendContentTitle;

    private String recommendContentFirst;

    private String recommendContentSecond;

    private String recommendContentDesc;

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getRecommendContentId() {
        return recommendContentId;
    }

    public void setRecommendContentId(String recommendContentId) {
        this.recommendContentId = recommendContentId;
    }

    public String getRecommendContentTitle() {
        return recommendContentTitle;
    }

    public void setRecommendContentTitle(String recommendContentTitle) {
        this.recommendContentTitle = recommendContentTitle;
    }

    public String getRecommendContentFirst() {
        return recommendContentFirst;
    }

    public void setRecommendContentFirst(String recommendContentFirst) {
        this.recommendContentFirst = recommendContentFirst;
    }

    public String getRecommendContentSecond() {
        return recommendContentSecond;
    }

    public void setRecommendContentSecond(String recommendContentSecond) {
        this.recommendContentSecond = recommendContentSecond;
    }

    public String getRecommendContentDesc() {
        return recommendContentDesc;
    }

    public void setRecommendContentDesc(String recommendContentDesc) {
        this.recommendContentDesc = recommendContentDesc;
    }
}
