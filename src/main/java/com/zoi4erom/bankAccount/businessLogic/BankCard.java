package com.zoi4erom.bankAccount.businessLogic;

import com.zoi4erom.bankAccount.dataLayer.TypeMoney;

public class BankCard {
	private String id;
	private String nameCard;
	private TypeMoney cardType;
	private String visualId;

	public BankCard(String id, String nameCard, TypeMoney cardType, String visualId) {
		this.id = id;
		this.nameCard = nameCard;
		this.cardType = cardType;
		this.visualId = visualId;
	}

	public BankCard() {
	}

	public String getId() {
		return id;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeMoney getCardType() {
		return cardType;
	}

	public void setCardType(TypeMoney cardType) {
		this.cardType = cardType;
	}

	public String getVisualId() {
		return visualId;
	}

	public void setVisualId(String visualId) {
		this.visualId = visualId;
	}
}