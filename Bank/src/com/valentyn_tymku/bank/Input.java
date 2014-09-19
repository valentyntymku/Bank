package com.valentyn_tymku.bank;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Input {
	static List<Customer> custs = Storage.custs;//new ArrayList<Customer>();
	static List<Account> accs = Storage.accs;//new ArrayList<Account>();

	public static void addCustomer(String str) {
		List<String> arr = new ArrayList<String>();
		arr.addAll(Arrays.asList(str.split(Util.readDelim)));
		custs.add(new Customer(arr.get(0), arr.get(1), Long.parseLong(arr
				.get(2)), arr.get(3), arr.get(4), arr.get(5)));
	}

	public static void addAccount(String str) {
		List<String> arr = new ArrayList<String>();
		arr.addAll(Arrays.asList(str.split(Util.readDelim)));

		int custIndex = 0;
		for (Customer cust : custs) {
			if (cust.getIpn().toString().equals(arr.get(5)))
				custIndex = custs.indexOf(cust);
		}

		accs.add(new Account(Long.parseLong(arr.get(0)), arr.get(1), Currencies
				.valueOf(arr.get(2)), Double.parseDouble(arr.get(3)), Double
				.parseDouble(arr.get(4)), custs.get(custIndex)));
	}

	public static void readXML() {
		try {
			File file = new File("customers.xml");
			File file1 = new File("accounts.xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
			custs.add(customer);
			System.out.println(customer);

			jaxbContext = JAXBContext.newInstance(Account.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Account account = (Account) jaxbUnmarshaller.unmarshal(file1);
			accs.add(account);
			System.out.println(account);

		} catch (JAXBException exception) {
			Logger.getLogger(Application.class.getName()).log(Level.SEVERE,
					"marshallExample threw JAXBException", exception);
		}
	}

	public static void readTXT() throws IOException {
		// Read customers file
		BufferedReader custReader = new BufferedReader(new FileReader(
				"customers.txt"));
		String str;
		while ((str = custReader.readLine()) != null) {
			addCustomer(str);
		}

		// Read accounts file
		BufferedReader accReader = new BufferedReader(new FileReader(
				"accounts.txt"));

		while ((str = accReader.readLine()) != null) {
			addAccount(str);
		}
		for (Customer cust : custs) {
			System.out.println(cust.toString());
		}

		for (Account acc : accs) {
			System.out.println(acc.toString());
		}
	}

	public static void readConsole() throws IOException {
		// Runtime.getRuntime().exec("cls");
		System.out.println("Console input. What do you want to add?");
		System.out.println("1. Add a new Customer.");
		System.out.println("2. Add a new Account to existing cutomer.");
		System.out.print("Choose one: ");

		BufferedReader r1 = new BufferedReader(new InputStreamReader(System.in));
		String tIn = r1.readLine();

		switch (Integer.valueOf(tIn)) {
		case 1:
			System.out.println("Please input string with next format:");
			System.out.println("firstName|LastName|IPN|Address|Phone|Email");
			System.out.println("Example:");
			System.out
					.println("James|Hetfield|1212121212|Address1|+380992233444|jh@gmail.com");
			BufferedReader r2 = new BufferedReader(new InputStreamReader(
					System.in));
			addCustomer(r2.readLine());
			break;
		case 2:
			System.out.println("Please input string with next format:");
			System.out
					.println("Number|Name|Currency|debit|creditLimit|Customer IPN");
			System.out.println("Example:");
			System.out.println("951798684|acc1|USD|100.0|10.0|1212121212");
			BufferedReader r3 = new BufferedReader(new InputStreamReader(
					System.in));
			addAccount(r3.readLine());
			break;
		}
		for (Customer cust : custs) {
			System.out.println(cust.toString());
		}

		for (Account acc : accs) {
			System.out.println(acc.toString());
		}
	}

	public static void readSQL() {

	}

}