package com.zoi4erom.bankAccount.businessLogic;

import java.util.ArrayList;
import java.util.List;

public final class AccountManager {
	private final List<Account> accountList = new ArrayList<>();
	public AccountManager() {
	}

	public boolean createAccount(String fullName, String passportID, String parol){
		for (Account account: accountList){
			if(account.getPassportID().equals(passportID)){
				return false;
			}
		}
		Account account = new Account(fullName, passportID, 0, parol);
		accountList.add(account);
		return true;
	}

	public List<Account> getAccountList() {
		return accountList;
	}
}
