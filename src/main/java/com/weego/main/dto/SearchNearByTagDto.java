package com.weego.main.dto;

import java.util.List;

public class SearchNearByTagDto {
	private String id;
	private String tag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj instanceof SearchNearByTagDto) {
			SearchNearByTagDto tempObj = (SearchNearByTagDto) obj;
			if(tempObj.id.equals(this.id) && tempObj.tag.equals(this.tag)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode() + this.tag.hashCode();
	}
	
	public static void main(String[] args) {
		SearchNearByTagDto o1 = new SearchNearByTagDto();
		o1.setId("1");
		o1.setTag("a");
		
		SearchNearByTagDto o2 = new SearchNearByTagDto();
		o2.setId("1");
		o2.setTag("a");
		
	    System.out.println(o1.equals(o2));
	}

}
