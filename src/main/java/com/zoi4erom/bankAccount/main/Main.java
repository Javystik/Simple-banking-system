package com.zoi4erom.bankAccount.main;

import com.zoi4erom.bankAccount.userInterface.UserInterface;

public class Main {

	public static void main(String[] args) {
		UserInterface userInterface = new UserInterface();
		userInterface.authMenu();
	}
}