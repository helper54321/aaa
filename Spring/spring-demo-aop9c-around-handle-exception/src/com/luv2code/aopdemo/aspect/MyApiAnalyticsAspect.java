package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3) //Kisebb szám esetén hamarabb hajtódik végre, mint a nagyobb számmal ellátott classek-ben megadott
public class MyApiAnalyticsAspect {

	//Azért ilyen hosszú mert elõtte meg kell adni a package és classnevet is, végül a metódus (ha ugyanabban a class-ben van, akkor csak a metódus kell)
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()") //Itt hivatkozunk rá (a hozzátartozó pointcut helyettesítõdik be)
	public void performApiAnalytics() {
		System.out.println("\n====> Performing API Analytics");
	}
}
