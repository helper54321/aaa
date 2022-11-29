package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

	//Itt l�trehozzuk 1x, �s b�rhol b�rh�nyszor felhaszn�lhatjuk (kb mint 1 v�ltoz� eset�n)
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	//Package �sszes gettere eset�n
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	public void getter() {}
	
	//Package �sszes settere eset�n
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	public void setter() {}
	
	//T�bb fentebb l�trehozott pointcut kombin�l�s�val k�sz�t�nk egyet (a logikai oper�torok ugyan�gy m�k�dnek mint if eset�n)
	//Ennek jelent�se, hogy a package �sszes met�dusa eset�n, kiv�ve getter �s setterekn�l
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}
}
