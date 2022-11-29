package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;


//Az a class amelyiken bel�l haszn�lni fogjuk a konstruktort, �s nem maga a konstruktor classe
//Mi ugyan ArrayList konstruktort haszn�lunk, de azt a SystemUnderTest class-ben haszn�ljuk, �gy az kell ide 
@PrepareForTest(SystemUnderTest.class)
@RunWith(PowerMockRunner.class) //Ahhoz hogy Mockito �s Powermock egy�ttesen haszn�lhat� legyen
public class MockingConstructorTest {
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Mock
	ArrayList mockList;
	
	
	@Test
	public void testBadNames() throws Exception {
		
		when(mockList.size()).thenReturn(5);
		
		//Itt megmondjuk, hogy amikor elk�sz�l az �j ArrayList p�ld�ny mit adjon vissza
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
		
		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		
		assertEquals(5, size);
	}
	
	
}
