package com.weego.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shopping extends BasePOI {
	@JsonProperty("brand")
	private List<ShoppingBrand> shoppingBrands;

	@JsonProperty("master_label")
	private List<BasePOILabel> masterLabel;

	public List<ShoppingBrand> getShoppingBrands() {
		return shoppingBrands;
	}

	public void setShoppingBrands(List<ShoppingBrand> shoppingBrands) {
		this.shoppingBrands = shoppingBrands;
	}

	public List<BasePOILabel> getMasterLabel() {
		return masterLabel;
	}

	public void setMasterLabel(List<BasePOILabel> masterLabel) {
		this.masterLabel = masterLabel;
	}

}
