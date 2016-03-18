package com.weego.main.model;

import java.util.List;

public class Shopping extends BasePOI {
	private List<ShoppingBrand> shoppingBrands;

	public List<ShoppingBrand> getShoppingBrands() {
		return shoppingBrands;
	}

	public void setShoppingBrands(List<ShoppingBrand> shoppingBrands) {
		this.shoppingBrands = shoppingBrands;
	}

}
