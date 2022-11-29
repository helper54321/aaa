package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	
	//A fő java classben létrehozott bean
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}
	
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello world, " + name);
	}
	
	
	//Ez az egész komment egy korábbi megoldáshoz tartozik
	//A locale azért kell, hogy i18n-et tudjuk használni
	//A @RequestHeader-el megmondjuk, hogy a locale a Request Header-ben lesz benne, milyen néven, és hogy nem kötelező (ha nincs megadva a default üzenet fut le, ami a sima messages.propertiesben van)
	//A return résznél pedig megmondjuk, hogy a properties fájl melyik részét jelenítse meg
	//Korábban így csináltuk, és a locales paraméternél csak a localet adtuk át: @RequestHeader(name="Accept-Language", required = false) Locale locale
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	
}
