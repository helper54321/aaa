package com.in28minutes.junit.helper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//Ahhoz kell, hogy tudjunk parameterized tesztet haszn�lni
//Egy class csak egy parameterized tesztet tartalmazhat, ha t�bb met�dushoz is akarunk, akkor k�l�n class kell hozz�
@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

	StringHelper helper  = new StringHelper();
	
	private String input;
	private String expectedOutput;
	
	public StringHelperParameterizedTest(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	@Parameters
	public static Collection<String[]> testConditions() {
		//Az 1. param�ter az input, a 2. az elv�rt output
		//A parameterized teszt �gy m�k�dik, hogy ezeket a param�tereket megkapja a fent l�trehozott konstruktor �s minden teszt esetn�l a megfelel�t haszn�lja (teh�t mindig �j p�ld�nyt hoz l�tre)
		String expectedOutputs [][] = {
				{"AACD", "CD"},
				{"ACD", "CD"} };
		
		return Arrays.asList(expectedOutputs);
	}
	
	@Test
	public void testTruncateAInFirst2Positions_AinFirst2Positions() {
		assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));
	}
	
}
