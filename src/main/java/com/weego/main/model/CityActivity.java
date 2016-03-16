package com.weego.main.model;

import java.util.List;
import java.util.Map;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityActivity {

	@Id
	private String cityActivityId;

	@JsonProperty("cover_image")
	private String activityImage;

	@JsonProperty("type")
	private String activityType;

	@JsonProperty("title")
	private String activityTitle;

	@JsonProperty("detail_address")
	private String activityDeaddress;

	@JsonProperty("start_time")
	private String activityOpenTime;

	@JsonProperty("end_time")
	private String activityCloseTime;

	@JsonProperty("act_time")
	private String activityActtime;

	@JsonProperty("longitude")
	private String activityLongitude;

	@JsonProperty("latitude")
	private String activityLatidute;

	@JsonProperty("act_url")
	private String activityActurl;

	@JsonProperty("order_url")
	private String activityOrderurl;

	@JsonProperty("desc")
	private String activityDesc;

	@JsonProperty("images_desc")
	private Map<String, List<CityActivityImages>> images;

	public String getCityActivityId() {
		return cityActivityId;
	}

	public void setCityActivityId(String cityActivityId) {
		this.cityActivityId = cityActivityId;
	}

	public String getActivityImage() {
		return activityImage;
	}

	public void setActivityImage(String activityImage) {
		this.activityImage = activityImage;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityDeaddress() {
		return activityDeaddress;
	}

	public void setActivityDeaddress(String activityDeaddress) {
		this.activityDeaddress = activityDeaddress;
	}

	public String getActivityOpenTime() {
		return activityOpenTime;
	}

	public void setActivityOpenTime(String activityOpenTime) {
		this.activityOpenTime = activityOpenTime;
	}

	public String getActivityCloseTime() {
		return activityCloseTime;
	}

	public void setActivityCloseTime(String activityCloseTime) {
		this.activityCloseTime = activityCloseTime;
	}

	public String getActivityActtime() {
		return activityActtime;
	}

	public void setActivityActtime(String activityActtime) {
		this.activityActtime = activityActtime;
	}

	public String getActivityLongitude() {
		return activityLongitude;
	}

	public void setActivityLongitude(String activityLongitude) {
		this.activityLongitude = activityLongitude;
	}

	public String getActivityLatidute() {
		return activityLatidute;
	}

	public void setActivityLatidute(String activityLatidute) {
		this.activityLatidute = activityLatidute;
	}

	public String getActivityActurl() {
		return activityActurl;
	}

	public void setActivityActurl(String activityActurl) {
		this.activityActurl = activityActurl;
	}

	public String getActivityOrderurl() {
		return activityOrderurl;
	}

	public void setActivityOrderurl(String activityOrderurl) {
		this.activityOrderurl = activityOrderurl;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public Map<String, List<CityActivityImages>> getImages() {
		return images;
	}

	public void setImages(Map<String, List<CityActivityImages>> images) {
		this.images = images;
	}

}
