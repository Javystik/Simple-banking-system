package com.zoi4erom.bankAccount.businessLogic;

import com.zoi4erom.bankAccount.dataLayer.Products;

public class Product {
	String name;
	int price;
	int id;

	public Product(String name, int price, int id) {
		this.name = name;
		this.price = price;
		this.id = id;
	}

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
