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
		
		myLogger.info("Before rész");
		
		//itt futtatjuk le a célmetódust, tehát a fentebbi rész lényegében a @Before-nak megfelelõ, ami lentebb jön pedig az @After rész
		Object result = null;
		
		
		//Itt ls is kezeltük a kivételt, így a fõ program nem is fog róla tudni hogy volt
//		try {
//			result = theProceedingJoinPoint.proceed();
//		} catch (Throwable e) {
//			myLogger.warning(e.getMessage());
//			
//			result = "Major accident! But no worries, your private AOP helicopter is on the way!";
//		}
		
		//Logoltuk a kivételt, de utána továbbdobjuk a fõprogramnak, hogy ott legyen végül lekezelve (tehát itt nem lesz)
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Throwable e) {
			myLogger.warning(e.getMessage());
			
			throw e; //továbbdobjuk a hívó programnak
		}
		
		long end = System.currentTimeMillis();
		long duration = end - begin;
		
		myLogger.info("\n======>Duration: " + duration / 1000.0 + " seconds"); //hogy ne ms-be írja ki
		
		myLogger.info("After rész");
		
		return result;
	}
	
	
	//A metódus után mindenképpen lefut (függetlenül attól, hogy sikeres volt-e vagy kivételt dobott, tehát kb mint kivételnél a finally)
	//Viszont ez fut le utoljára, az AfterReturning / AfterThrowing hamarabb fog
	//Az afternek nincs hozzáférése a dobott kivételhez, ha azt is akarjuk, akkor AfterThrowing kell!!!
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n========>>>  Executing @After (finally) on method: " + method);
	}
	
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theEx") //A dobott kivétel értéke
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theEx) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n========>>>  Executing @AfterThrowing on method: " + method);
		
		myLogger.info("\n========>>>  The exception is: " + theEx);
		
		
	}
	
	
	
	
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result") //A pointcut-nál megadott metódus visszatérési értékét tartalmazza (bárhogy hívhatnám itt)
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) { //A returning résznél megadott néven kell rá hivatkozni
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
			//ugyanaz mint a syso, de itt a string formáját kell átadni paraméterként, azért a tostring
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				myLogger.info("Account name: " + theAccount.getName());
				myLogger.info("Account level: " + theAccount.getLevel());
			}
		}
		
		
	}
}
