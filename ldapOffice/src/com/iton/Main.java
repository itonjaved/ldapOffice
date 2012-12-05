package com.iton;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ldap3.xml");
		Test t=(Test) context.getBean("three");
		System.out.println("Enter User Details");
		Scanner sc=new Scanner(System.in);
	
	System.out.println("Enter Roleee");
	String role=sc.next();
	System.out.println("Enter Password");
	int psw=sc.nextInt();
	System.out.println("Enter Ofiice id");
	int office=sc.nextInt();
	
	t.insertLdapUser(role,psw,office);
		
	}
}
