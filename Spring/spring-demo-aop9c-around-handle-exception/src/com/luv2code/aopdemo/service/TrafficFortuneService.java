package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	public String getFortune() {
		
		//elaltatjuk 5mp-re, hogy szimuláljuk az idõigényt
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "Expect heavy traffic this morning";
	}

	public String getFortune(boolean tripWire) {
		
		if(tripWire) {
			throw new RuntimeException("Major accident! Highway is closed!");
		}
		
		return getFortune();
	}
}
