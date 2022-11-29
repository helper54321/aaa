package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public boolean addSilly() {
		System.out.println(getClass() + ": Doing stuff: Adding a membership account\n");

		return true;
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": I'm going to sleep now\n");
	}
}
