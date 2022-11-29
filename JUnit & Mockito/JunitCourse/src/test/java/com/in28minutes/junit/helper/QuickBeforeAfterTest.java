package com.in28minutes.junit.helper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuickBeforeAfterTest {
	
	//A BeforeClassel ellátott metódus mindig static kell legyen!!!!!!
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Before class (only 1x at the beginning)");
	}
	
	@Before
	public void setup() {
		System.out.println("Before test (before every test)");
	}

	@Test
	public void test1() {
		System.out.println("Test1 executed");
	}
	
	@Test
	public void test2() {
		System.out.println("Test2 executed");
	}
	
	@After
	public void tearDown() {
		System.out.println("After test (after every test)");
	}
	
	//Az AfterClassel ellátott metódus mindig static kell legyen!!!!!!
	@AfterClass
	public static void afterClass() {
		System.out.println("After class (only 1x at the end)");
	}

}
