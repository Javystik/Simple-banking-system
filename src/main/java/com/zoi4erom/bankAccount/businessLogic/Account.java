package com.zoi4erom.bankAccount.businessLogic;

public class Account {
	private final String fullName; //ПІБ
	private final String passportID; //айді в паспорті
	private double balance;
	private final String parol;

	public Account(String fullName, String passportID, double balance, String parol) {
		this.fullName = fullName;
		this.passportID = passportID;
		this.balance = balance;
		this.parol = parol;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPassportID() {
		return passportID;
	}

	public String getParol() {
		return parol;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account{" +
		    "fullName='" + fullName + '\'' +
		    ", passportID=" + passportID +
		    ", balance=" + balance +
		    ", parol=" + parol +
		    ", famelyParol=" + '}';
	}
}
