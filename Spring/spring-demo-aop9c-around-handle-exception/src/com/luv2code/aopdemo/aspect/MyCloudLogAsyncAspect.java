package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1) //Kisebb szám esetén hamarabb hajtódik végre, mint a nagyobb számmal ellátott classek-ben megadott
public class MyCloudLogAsyncAspect {
	
	//Azért ilyen hosszú mert elõtte meg kell adni a package és classnevet is, végül a metódus (ha ugyanabban a class-ben van, akkor csak a metódus kell)
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()") //Itt hivatkozunk rá (a hozzátartozó pointcut helyettesítõdik be)
	public void logToCloudAsync() {
		System.out.println("\n====> Logging to Cloud in async fashion");
	}

}
