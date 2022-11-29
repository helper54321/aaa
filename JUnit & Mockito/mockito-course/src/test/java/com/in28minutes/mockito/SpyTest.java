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
		
		//Ugyan hozz�adjuk, de ez a fentebb meghat�rozott size()-t nem befoly�solja, mert ott fixen megmondtuk hogy mindig annyit adjon vissza
		arrayListMock.add("Dummy");
		
		assertEquals(5, arrayListMock.size());
		
		
		
		//A spy annyiban k�l�nb�zik a mockt�l, hogy itt l�nyeg�ben t�nylegesen meg�rz�dnek az eredeti class tulajdons�gai
		//Teh�t a fenti p�ld�val ellent�tben itt a hozz�ad�s befoly�solja a m�retet
		//A lehet�s�g itt is adott a stub-ol�sra, �s ha azt tessz�k, akkor az lesz az �rv�nyes (l�nyeg�ben egy overridenak felel meg, de csak arra a met�dusra amire haszn�ljuk)
		//Ugyan hasznos lehet, m�gis ink�bb a mock-os megold�s aj�nlott
		List arrayListSpy = spy(ArrayList.class);
		
		assertEquals(0, arrayListSpy.size());
		arrayListSpy.add("Dummy");
		assertEquals(6, arrayListSpy.size());
		
		verify(arrayListSpy).add("Dummy");
		verify(arrayListSpy, never()).clear();
		
//		stub(arrayListSpy.size()).toReturn(5);
		
		//Ugyan hozz�adjuk, de ez a fentebb meghat�rozott size()-t nem befoly�solja, mert ott fixen megmondtuk hogy mindig annyit adjon vissza
		
	}

}
