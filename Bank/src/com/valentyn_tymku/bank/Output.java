package com.valentyn_tymku.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Output {

	private static List<Customer> custs = Storage.custs;
	private static List<Account> accs = Storage.accs;

	public static void writeConsole() {
		for (Customer cust : custs) {
			System.out.println(cust.toString());
		}

		for (Account acc : accs) {
			System.out.println(acc.toString());
		}
	}

	public static void writeTXT() throws IOException {
		BufferedWriter cw = new BufferedWriter(new FileWriter("customersW.txt"));
		BufferedWriter ca = new BufferedWriter(new FileWriter("accountsW.txt"));
		for (Customer cust : custs) {
			cw.write(cust.toString());
			cw.newLine();
		}

		for (Account acc : accs) {
			ca.write(acc.toString());
			cw.newLine();
		}

		cw.close();
		ca.close();
	}

	public static void writeXML() {
		
		  try { 
			  File fileCustomers = new File("customersW.xml"); 
			  File fileAccounts = new File("accountsW.xml");
			  for (Customer cust : custs) {
		  JAXBContext context = JAXBContext.newInstance(Customer.class);
		  Marshaller marshaller = context.createMarshaller();
		  marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		  marshaller.marshal(cust, fileCustomers);
			  }
		  
			  for (Account acc : accs) {
				  JAXBContext context = JAXBContext.newInstance(Account.class); 
				  Marshaller marshaller = context.createMarshaller();
				  marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				  marshaller.marshal(acc, fileAccounts);
			  }
			  
		  } catch (JAXBException exception) {
		  Logger.getLogger(Application.class.getName()).log(Level.SEVERE,
		  "marshallExample threw JAXBException", exception); } 
		 
	}

	public static void writeSQL() {

	}
}