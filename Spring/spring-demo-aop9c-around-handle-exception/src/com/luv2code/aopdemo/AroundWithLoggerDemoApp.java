package com.luv2code.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {

	//Param�terk�nt ez a szok�sos elj�r�s, hogy a classnevet adjuk oda
	private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		//L�nyeg�ben ugyanaz mint a syso, csak m�s fel�letre �r (teh�t ink�bb a h�tt�rben k�l�nb�zik)
		//Teh�t nem system output stream, hanem logger output streamre �r
		myLogger.info("\nMain program: AroundDemoApp");
		myLogger.info("Calling getFortune");
		
		String data = theFortuneService.getFortune();
		
		myLogger.info("My fortune is: " + data);
		myLogger.info("Finished");
		
		
		context.close();
	}
}
