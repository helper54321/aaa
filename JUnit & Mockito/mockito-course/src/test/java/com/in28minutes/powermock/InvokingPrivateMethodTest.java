package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class) //Ahhoz hogy Mockito és Powermock együttesen használható legyen
public class InvokingPrivateMethodTest {
	
	
	@Mock
	Dependency dependency;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() throws Exception {
		
		List<Integer> stats = Arrays.asList(1, 2, 3);
		
		when(dependency.retrieveAllStats()).thenReturn(stats);
		
		//Mivel private metódust hagyományos nem tududnk meghívni, ezért powermockkal így oldható meg
		//Az 1. paraméter a class, a 2. pedig annak a privát metódusa
		long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");
		
		assertEquals(6, result);
		
	}
	
	
}
