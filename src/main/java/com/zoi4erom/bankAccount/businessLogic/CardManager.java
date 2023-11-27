// CardManager.java
package com.zoi4erom.bankAccount.businessLogic;

import com.zoi4erom.bankAccount.dataLayer.TypeMoney;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public final class CardManager {
	private final List<BankCard> bankCards = new ArrayList<>();
	private final AccountManager accountManager;

	public CardManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public void addCard(String nameCard, TypeMoney cardType, String id) {
		String visualId = generatorRandomVisualId();
		BankCard bankCard = new BankCard(id, nameCard, cardType, visualId);
		bankCards.add(bankCard);
	}


	public boolean addMoney(double amount, BankCard selectedCard, String authorizedAccountID) {
		double coefficient = selectedCard.getCardType().getCoefficient();

		List<Account> accountList = accountManager.getAccountList();

		for (Account account : accountList) {
			if (account.getPassportID().equals(authorizedAccountID)) {
				account.setBalance(account.getBalance() + amount * coefficient);
				return true;
			}
		}
		return false;
	}

	public boolean buyProduct(double amount, String authorizedAccountID, String productName) {
		List<Account> accountList = accountManager.getAccountList();

		for (Account account : accountList) {
			if (account.getPassportID().equals(authorizedAccountID)) {
				if (account.getBalance() >= amount) {
					account.setBalance(account.getBalance() - amount);

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String dateAndTime = sdf.format(new Date());

					System.out.println("[" + account.getFullName() + "] – купив/ла " +
					    productName + " (1) " + amount + " грн. " +
					    dateAndTime + ". Залишилось на балансі – " +
					    account.getBalance() + " грн.");

					return true;
				}
			}
		}
		return false;
	}
	public BankCard getCardByIndex(int index, String authorizedAccountID) {
		int cardNumber = 1;
		for (BankCard bankCard : bankCards) {
			if (bankCard.getId().equals(authorizedAccountID)) {
				if (cardNumber == index) {
					return bankCard;
				}
				cardNumber++;
			}
		}
		return null;
	}

	public String generatorRandomVisualId() {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i <= 7; i++) {
			int a = random.nextInt(9);
			stringBuilder.append(a);
		}
		return stringBuilder.toString();
	}

	public static TypeMoney getTypeMoneyByName(String name) {
		for (TypeMoney typeMoney : TypeMoney.values()) {
			if (typeMoney.getName().equalsIgnoreCase(name)) {
				return typeMoney;
			}
		}
		return null;
	}

	public List<BankCard> getBankCards() {
		return bankCards;
	}
}
