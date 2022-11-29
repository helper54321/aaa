package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSizeMethod() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
	}
	
	@Test
	public void letsMockListSizeMethod_ReturnMultipleValues() {
		List listMock = mock(List.class);
		
		//Itt lényegében azt csinálja, hogy mindkettõt visszaadja, tehát a lenti assertequals esetén ha az elsõ esetben 2-vel, másodiknál 3-al hasonlítjuk akkor átmegy a teszt
		when(listMock.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		
		when(listMock.get(0)).thenReturn("in28Minutes");
		
		assertEquals("in28Minutes", listMock.get(0));
		
		//Mivel ehhez nem mondtuk meg, hogy mit adjon vissza, így alapértelmezettként null-t fog, így azzal hasonlítva átmegy a teszt
		assertEquals(null, listMock.get(1));
	}
	
	@Test
	public void letsMockListGet_UsingArgumentMatcher() {
		List listMock = mock(List.class);
		
		//Az anyInt-el megmondjuk, hogy bármilyen int paramétert adunk át a get-nek, akkoris a megadott dolgot adja vissza
		//Nyílván, nem csak az anyInt létezik (ha belenézünk a Mockito class-be, az kiterjeszti a Matchert, amiben megnézhetõek hogy mik vannak még)
		when(listMock.get(anyInt())).thenReturn("in28Minutes");
		
		assertEquals("in28Minutes", listMock.get(0));
		assertEquals("in28Minutes", listMock.get(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void letsMockListGet_ThrowException() {
		List listMock = mock(List.class);
		
		//Itt megmondjuk, hogy a get meghívásánál dobjon egy kivételt, és mivel a @test-nél megadtuk hogy erre számítunk, így átmegy a teszt
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
	}
	
	@Test(expected = RuntimeException.class)
	public void letsMockListGet_mixingUp() {
		List listMock = mock(List.class);
		
		when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something"));
	}
	
	@Test
	public void letsMockListGet_usingBDD() {

		//given
		List<String> listMock = mock(List.class);
		
		given(listMock.get(0)).willReturn("in28Minutes");
		
		
		//when
		//A listMock.get(0) kerülhetne ide (az az elvégzendõ mûvelet)
		String firstElement = listMock.get(0);
		
		//then
		assertThat(firstElement, is("in28Minutes"));
		
//		assertEquals("in28Minutes", listMock.get(0));
//		assertEquals(null, listMock.get(1));
	}

}
