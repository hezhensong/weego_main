package com.weego.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shopping extends BasePOI {
	@JsonProperty("brand")
	private List<ShoppingBrand> shoppingBrands;

	public List<ShoppingBrand> getShoppingBrands() {
		return shoppingBrands;
	}

	public void setShoppingBrands(List<ShoppingBrand> shoppingBrands) {
		this.shoppingBrands = shoppingBrands;
	}

}
