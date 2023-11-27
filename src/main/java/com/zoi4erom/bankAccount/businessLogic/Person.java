package com.zoi4erom.bankAccount.businessLogic;

import java.util.List;

public class Person {
	private final AccountManager accountCreater;

	public Person(AccountManager accountCreater) {
		this.accountCreater = accountCreater;
	}

	public boolean authorized(String passportID, String parol) {
		List<Account> accountList = accountCreater.getAccountList();

		for (Account account : accountList) {
			if (account.getPassportID().equals(passportID) && account.getParol().equals(parol)) {
				return true;
			}
		}
		return false;
	}
}
