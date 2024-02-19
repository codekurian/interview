package com.equalexperts.response;

import java.util.List;

public class ShoppingCartResponse {

	/*
	 * Cart contains 2 x cornflakes Cart contains 1 x weetabix Subtotal = 15.02 Tax
	 * = 1.88 Total = 16.90
	 */
	private List<ProductInCart> products;

	private Double subtotal;
	private Double tax;
	private Double total;
	public List<ProductInCart> getProducts() {
		return products;
	}
	public void setProducts(List<ProductInCart> products) {
		this.products = products;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	
}
