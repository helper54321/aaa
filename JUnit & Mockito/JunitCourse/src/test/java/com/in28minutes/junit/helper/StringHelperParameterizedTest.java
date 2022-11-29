package com.in28minutes.junit.helper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//Ahhoz kell, hogy tudjunk parameterized tesztet használni
//Egy class csak egy parameterized tesztet tartalmazhat, ha több metódushoz is akarunk, akkor külön class kell hozzá
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
		//Az 1. paraméter az input, a 2. az elvárt output
		//A parameterized teszt úgy mûködik, hogy ezeket a paramétereket megkapja a fent létrehozott konstruktor és minden teszt esetnél a megfelelõt használja (tehát mindig új példányt hoz létre)
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
