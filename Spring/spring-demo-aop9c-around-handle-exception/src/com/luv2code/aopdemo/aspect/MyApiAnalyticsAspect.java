package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3) //Kisebb sz�m eset�n hamarabb hajt�dik v�gre, mint a nagyobb sz�mmal ell�tott classek-ben megadott
public class MyApiAnalyticsAspect {

	//Az�rt ilyen hossz� mert el�tte meg kell adni a package �s classnevet is, v�g�l a met�dus (ha ugyanabban a class-ben van, akkor csak a met�dus kell)
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()") //Itt hivatkozunk r� (a hozz�tartoz� pointcut helyettes�t�dik be)
	public void performApiAnalytics() {
		System.out.println("\n====> Performing API Analytics");
	}
}
