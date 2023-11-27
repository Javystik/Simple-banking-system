package com.zoi4erom.bankAccount.dataLayer;

public enum TypeMoney {
	GOLDENPAW("GOLDENPAW", 1),
	SILVERTAIL("SILVERTAIL", 10),
	BRONZEWHISKER("BRONZEWHISKER", 100);

	private String name;
	private double coefficient;

	TypeMoney(String name, double coefficient) {
		this.name = name;
		this.coefficient = coefficient;
	}

	public String getName() {
		return name;
	}

	public double getCoefficient() {
		return coefficient;
	}
}