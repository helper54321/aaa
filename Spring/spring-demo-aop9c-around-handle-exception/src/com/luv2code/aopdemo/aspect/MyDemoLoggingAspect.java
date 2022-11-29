package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private static Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n========>>>  Executing @Around on method: " + method);
		
		long begin = System.currentTimeMillis();
		
		myLogger.info("Before r�sz");
		
		//itt futtatjuk le a c�lmet�dust, teh�t a fentebbi r�sz l�nyeg�ben a @Before-nak megfelel�, ami lentebb j�n pedig az @After r�sz
		Object result = null;
		
		
		//Itt ls is kezelt�k a kiv�telt, �gy a f� program nem is fog r�la tudni hogy volt
//		try {
//			result = theProceedingJoinPoint.proceed();
//		} catch (Throwable e) {
//			myLogger.warning(e.getMessage());
//			
//			result = "Major accident! But no worries, your private AOP helicopter is on the way!";
//		}
		
		//Logoltuk a kiv�telt, de ut�na tov�bbdobjuk a f�programnak, hogy ott legyen v�g�l lekezelve (teh�t itt nem lesz)
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Throwable e) {
			myLogger.warning(e.getMessage());
			
			throw e; //tov�bbdobjuk a h�v� programnak
		}
		
		long end = System.currentTimeMillis();
		long duration = end - begin;
		
		myLogger.info("\n======>Duration: " + duration / 1000.0 + " seconds"); //hogy ne ms-be �rja ki
		
		myLogger.info("After r�sz");
		
		return result;
	}
	
	
	//A met�dus ut�n mindenk�ppen lefut (f�ggetlen�l att�l, hogy sikeres volt-e vagy kiv�telt dobott, teh�t kb mint kiv�teln�l a finally)
	//Viszont ez fut le utolj�ra, az AfterReturning / AfterThrowing hamarabb fog
	//Az afternek nincs hozz�f�r�se a dobott kiv�telhez, ha azt is akarjuk, akkor AfterThrowing kell!!!
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n========>>>  Executing @After (finally) on method: " + method);
	}
	
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theEx") //A dobott kiv�tel �rt�ke
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theEx) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n========>>>  Executing @AfterThrowing on method: " + method);
		
		myLogger.info("\n========>>>  The exception is: " + theEx);
		
		
	}
	
	
	
	
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result") //A pointcut-n�l megadott met�dus visszat�r�si �rt�k�t tartalmazza (b�rhogy h�vhatn�m itt)
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) { //A returning r�szn�l megadott n�ven kell r� hivatkozni
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n========>>>  Executing @AfterReturning on method: " + method);
		
		myLogger.info("\n========>>>  Result is: " + result);
		
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n========>>>  Result is: " + result);
	}
	

	private void convertAccountNamesToUpperCase(List<Account> result) {
		if(result.size() > 0) {
			for(Account tempAccount : result) {
				tempAccount.setName(tempAccount.getName().toUpperCase());
			}
		}
		
	}


	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinpoint) {
		myLogger.info("\n====> Executing @Before advice on method");
		
		MethodSignature methodSig = (MethodSignature) theJoinpoint.getSignature();
		
		myLogger.info("Method: " + methodSig);
		
		Object[] args = theJoinpoint.getArgs();
		
		for(Object tempArg : args) {
			//ugyanaz mint a syso, de itt a string form�j�t kell �tadni param�terk�nt, az�rt a tostring
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				myLogger.info("Account name: " + theAccount.getName());
				myLogger.info("Account level: " + theAccount.getLevel());
			}
		}
		
		
	}
}
