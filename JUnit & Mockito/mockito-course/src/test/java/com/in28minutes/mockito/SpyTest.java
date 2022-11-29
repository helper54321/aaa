package com.in28minutes.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void test() {
		List arrayListMock = mock(ArrayList.class);
		
		assertEquals(0, arrayListMock.size());
		
		stub(arrayListMock.size()).toReturn(5);
		
		//Ugyan hozzáadjuk, de ez a fentebb meghatározott size()-t nem befolyásolja, mert ott fixen megmondtuk hogy mindig annyit adjon vissza
		arrayListMock.add("Dummy");
		
		assertEquals(5, arrayListMock.size());
		
		
		
		//A spy annyiban különbözik a mocktól, hogy itt lényegében ténylegesen megõrzõdnek az eredeti class tulajdonságai
		//Tehát a fenti példával ellentétben itt a hozzáadás befolyásolja a méretet
		//A lehetõség itt is adott a stub-olásra, és ha azt tesszük, akkor az lesz az érvényes (lényegében egy overridenak felel meg, de csak arra a metódusra amire használjuk)
		//Ugyan hasznos lehet, mégis inkább a mock-os megoldás ajánlott
		List arrayListSpy = spy(ArrayList.class);
		
		assertEquals(0, arrayListSpy.size());
		arrayListSpy.add("Dummy");
		assertEquals(6, arrayListSpy.size());
		
		verify(arrayListSpy).add("Dummy");
		verify(arrayListSpy, never()).clear();
		
//		stub(arrayListSpy.size()).toReturn(5);
		
		//Ugyan hozzáadjuk, de ez a fentebb meghatározott size()-t nem befolyásolja, mert ott fixen megmondtuk hogy mindig annyit adjon vissza
		
	}

}
