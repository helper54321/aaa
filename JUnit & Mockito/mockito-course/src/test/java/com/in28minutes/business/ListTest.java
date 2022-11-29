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
		
		//Itt l�nyeg�ben azt csin�lja, hogy mindkett�t visszaadja, teh�t a lenti assertequals eset�n ha az els� esetben 2-vel, m�sodikn�l 3-al hasonl�tjuk akkor �tmegy a teszt
		when(listMock.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		
		when(listMock.get(0)).thenReturn("in28Minutes");
		
		assertEquals("in28Minutes", listMock.get(0));
		
		//Mivel ehhez nem mondtuk meg, hogy mit adjon vissza, �gy alap�rtelmezettk�nt null-t fog, �gy azzal hasonl�tva �tmegy a teszt
		assertEquals(null, listMock.get(1));
	}
	
	@Test
	public void letsMockListGet_UsingArgumentMatcher() {
		List listMock = mock(List.class);
		
		//Az anyInt-el megmondjuk, hogy b�rmilyen int param�tert adunk �t a get-nek, akkoris a megadott dolgot adja vissza
		//Ny�lv�n, nem csak az anyInt l�tezik (ha belen�z�nk a Mockito class-be, az kiterjeszti a Matchert, amiben megn�zhet�ek hogy mik vannak m�g)
		when(listMock.get(anyInt())).thenReturn("in28Minutes");
		
		assertEquals("in28Minutes", listMock.get(0));
		assertEquals("in28Minutes", listMock.get(1));
	}
	
	@Test(expected = RuntimeException.class)
	public void letsMockListGet_ThrowException() {
		List listMock = mock(List.class);
		
		//Itt megmondjuk, hogy a get megh�v�s�n�l dobjon egy kiv�telt, �s mivel a @test-n�l megadtuk hogy erre sz�m�tunk, �gy �tmegy a teszt
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
		//A listMock.get(0) ker�lhetne ide (az az elv�gzend� m�velet)
		String firstElement = listMock.get(0);
		
		//then
		assertThat(firstElement, is("in28Minutes"));
		
//		assertEquals("in28Minutes", listMock.get(0));
//		assertEquals(null, listMock.get(1));
	}

}
