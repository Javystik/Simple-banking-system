package com.zoi4erom.bankAccount.userInterface;

import com.zoi4erom.bankAccount.businessLogic.*;
import com.zoi4erom.bankAccount.dataLayer.TypeMoney;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
	private static final Scanner scanner = new Scanner(System.in);
	private static final AccountManager accountCreater = new AccountManager();
	private static final CardManager cardManager = new CardManager(accountCreater);
	private static final List<Account> accounts = accountCreater.getAccountList();
	private static final List<BankCard> bankCards = cardManager.getBankCards();
	private static int turn = 1;

	private static final Shop shop = new Shop();
	private String authorizedAccountID;
	public void authMenu() { // меню реєстрації та авторизації
		int choice;
		do {
			System.out.println("Головне меню банку");
			System.out.println("1: Авторизація");
			System.out.println("2: Реєстрація");
			System.out.println("0: Вихід");
			System.out.print("Вибір: ");
			choice = scanner.nextInt();

			switch (choice) {
				case 1:
					authorizedAccount();
					break;
				case 2:
					linkToCreateAccount();
					break;
				case 0:
					System.out.println("Вихід з програми.");
					break;
				default:
					System.out.println("Невірний вибір. Будь ласка, введіть ще раз.");
					break;
			}
		} while (choice != 0);
	}

	public void mainMenu() { // головне меню
		int choice;
		do {
			System.out.println("Головне меню котячого банку!");
			System.out.println("1: Перегляд інформації про обліковий запис");
			System.out.println("2: Перегляд інформації про картки");
			System.out.println("3: Створення нової картки");
			System.out.println("4: Поповнення балансу облікового запису");
			System.out.println("5: Магазин товарів");
			System.out.println("6: Придбання товару");
			System.out.println("0: Перехід в меню реєстрації та входу");
			System.out.print("Вибір: ");
			choice = scanner.nextInt();

			switch (choice) {
				case 1:
					displayAllAcc();
					break;
				case 2:
					displayAllCard();
					break;
				case 3:
					linkToCreateCard();
					break;
				case 4:
					addMoney();
					break;
				case 5:
					shopProductsDisplay();
					break;
				case 6:
					buyProduct();
					break;
				case 0:
					System.out.println("Вихід з програми.");
					break;
				default:
					System.out.println("Невірний вибір. Будь ласка, введіть ще раз.");
					break;
			}
		} while (choice != 0);
	}
	public void shopProductsDisplay() {
		List<Product> productList = shop.getProductList();

		int productsPerRow = 3;

		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);

			System.out.printf("[%d] %-20s %d%n", product.getId(), product.getName(), product.getPrice());

			if ((i + 1) % productsPerRow == 0) {
				System.out.println();
			}
		}
	}

	public void buyProduct() {
		shopProductsDisplay();
		System.out.print("Виберіть товар за індексом для покупки: ");
		int productIndex = scanner.nextInt();

		double productCost = shop.getProductCostByIndex(productIndex);

		if (cardManager.buyProduct(productCost, authorizedAccountID, shop.getProductList().get(productIndex).getName())) {
			System.out.println("Успішне придбання продукту!");
		} else {
			System.out.println("Помилка! Недостатньо коштів або неправильний індекс продукту!");
		}
	}
	public void addMoney(){
		System.out.print("Впишіть суму поповнення: ");
		int money = scanner.nextInt();
		System.out.println("Оберіть потрібну картку з списку: ");

		displayAllCard();

		int indexCard = scanner.nextInt();
		if(cardManager.addMoney(money, cardManager.getCardByIndex(indexCard, authorizedAccountID), authorizedAccountID)){
			System.out.println("Аккаунт успішно поповнено!");
		}else{
			System.out.println("Помилка.");
		}
	}
	public void authorizedAccount() { // авторизація
		Person person = new Person(accountCreater);

		scanner.nextLine();

		System.out.print("Введіть свій id-паспорту: ");
		String userPassportID = scanner.nextLine();

		System.out.print("Введіть пароль для облікового запису: ");
		String userParol = scanner.nextLine();

		if (person.authorized(userPassportID, userParol)) {
			System.out.println("Аккаунт знайдено! Успішний вхід");
			authorizedAccountID = userPassportID;
			mainMenu();
		} else {
			System.out.println("Аккаунт не знайдено! Повторіть спробу та перевірте дані.");
		}
	}
	public void displayAllAcc() { // показ аккаунта
		for (Account account : accounts) {
			if (authorizedAccountID.equals(account.getPassportID())) {
				System.out.println("ПІБ: " + account.getFullName()
				    + ", ID: " + account.getPassportID()
				    + ", баланс: " + account.getBalance() + " грн.");
			}
		}
	}
	public void displayAllCard() { //показ карт які привязані до аккаунта
		if (bankCards.isEmpty()){
			System.out.println("На вашому обліковому записі ще не знайдено картки.");
		}
		for (BankCard bankCard : bankCards) {
			if (authorizedAccountID.equals(bankCard.getId())) {
				System.out.println(turn + " : Карта: " + bankCard.getNameCard()
				    + ", тип оплати: " + bankCard.getCardType().getName() + " ID: " + bankCard.getVisualId());
			}
			turn++;
		}
	}
	public void linkToCreateAccount() { // створення облікового запису
		System.out.println("Для створення облікового запису вкажіть: ");

		scanner.nextLine();

		System.out.print("Введіть своє ПІБ: ");
		String fullName = scanner.nextLine();

		System.out.print("Введіть свій паспорт-ID: ");
		String passportID = scanner.nextLine();

		System.out.print("Введіть пароль: ");
		String parol = scanner.nextLine();

		if (accountCreater.createAccount(fullName, passportID, parol)) {
			authorizedAccountID = passportID;
			System.out.println("Обліковий запис успішно створенний!");
			mainMenu();
		} else {
			System.out.println("Не вдалося додати карту. "
			    + "Обліковий запис з введеним ID паспорту вже існує.");
		}
	}
	public void linkToCreateCard() { // створення карти
		scanner.nextLine();

		System.out.println("Для створення картки для облікового запису вкажіть: ");
		System.out.print("Введіть ім'я для карточки: ");
		String cardName = scanner.nextLine();

		System.out.println("Введіть потрібну валюту з списку(великими): ");
		for (TypeMoney typeMoney : TypeMoney.values()) {
			System.out.println(typeMoney);
		}

		System.out.print("Ваш вибір: ");
		String typeMoneyName = scanner.nextLine();

		TypeMoney cardType = CardManager.getTypeMoneyByName(typeMoneyName);

		if (cardType != null) {
			cardManager.addCard(cardName, cardType, authorizedAccountID);
			System.out.println("Карта успішно додана!");
		} else {
			System.out.println("Введено невірний тип карточки.");
		}
	}
}