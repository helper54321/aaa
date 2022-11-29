package com.luv2code.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {

	//Paraméterként ez a szokásos eljárás, hogy a classnevet adjuk oda
	private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		//Lényegében ugyanaz mint a syso, csak más felületre ír (tehát inkább a háttérben különbözik)
		//Tehát nem system output stream, hanem logger output streamre ír
		myLogger.info("\nMain program: AroundDemoApp");
		myLogger.info("Calling getFortune");
		
		String data = theFortuneService.getFortune();
		
		myLogger.info("My fortune is: " + data);
		myLogger.info("Finished");
		
		
		context.close();
	}
}
