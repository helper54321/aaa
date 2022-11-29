package com.in28minutes.powermock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.in28minutes.business.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;

@RunWith(PowerMockRunner.class) //Ahhoz hogy Mockito �s Powermock egy�ttesen haszn�lhat� legyen
@PrepareForTest(UtilityClass.class) //A static met�dust tartalmaz� class
public class MockingStaticMethodTest {
	
	//Ahhoz hogy Mockito �s Powermock egy�ttesen haszn�lhat� legyen ezekre van sz�ks�g:
	//1.Specific runner
	//2.Initialize UtilityClass.class (mert abban van a static method)
	//3.mock
	
	@Mock
	Dependency dependency;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		List<Integer> stats = Arrays.asList(1, 2, 3);
		
		when(dependency.retrieveAllStats()).thenReturn(stats);
		
		PowerMockito.mockStatic(UtilityClass.class);
		
		when(UtilityClass.staticMethod(6)).thenReturn(150);
		
		int result = systemUnderTest.methodCallingAStaticMethod();
		
		assertEquals(150, result);
		
		
		//Powermock eset�n �gy tudjuk ellen�rizni, hogy meg lett-e h�vva a bizonyos static method
		//Az 1. l�p�ssel jelezz�k, hogy figyelje az ut�na megh�vott statikus h�v�st, abb�l fogja tudni melyiket kell ellen�rizni
		PowerMockito.verifyStatic();
		UtilityClass.staticMethod(6);
		
	}
	
	
}
