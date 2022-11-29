package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

	//Itt létrehozzuk 1x, és bárhol bárhányszor felhasználhatjuk (kb mint 1 változó esetén)
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	//Package összes gettere esetén
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	public void getter() {}
	
	//Package összes settere esetén
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	public void setter() {}
	
	//Több fentebb létrehozott pointcut kombinálásával készítünk egyet (a logikai operátorok ugyanúgy mûködnek mint if esetén)
	//Ennek jelentése, hogy a package összes metódusa esetén, kivéve getter és settereknél
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}
}
