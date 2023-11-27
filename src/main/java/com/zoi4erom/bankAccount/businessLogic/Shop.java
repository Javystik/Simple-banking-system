package com.zoi4erom.bankAccount.businessLogic;

import com.zoi4erom.bankAccount.dataLayer.Products;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop {
	List<Product> productList = new ArrayList<>();

	public Shop(List<Product> productList) {
		this.productList = productList;
	}

	public Shop() {
		generateProductsAndPrice();
	}

	public double getProductCostByIndex(int index) {
		if (index >= 0 && index < productList.size()) {
			return productList.get(index).price;
		}
		return 0;
	}
	public void generateProductsAndPrice() {
		Random random = new Random();
		int id = 0;

		for (Products products : Products.values()) {
			int price = random.nextInt(150);
			productList.add(new Product(products.getName(), price, id));
			id++;
		}
	}
	public List<Product> getProductList() {
		return productList;
	}
}
