package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.in28minutes.data.api.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		
		//Ennek az eg�sz megk�zel�t�snek az a l�nyege, hogy felt�telekt�l f�gg�v� tessz�k hogy mi t�rt�njen
		//Teh�t pl 'A' esetben 'x'-et adjon vissza, 'B' esetben pedig 'y'-t
		//�gy enn�l a p�ld�n�l a when �s thenReturn p�r helyett given �s willReturn-t haszn�lunk
		//Tov�bb� az assertEquals helyett az assertThat-et haszn�lhatjuk (aj�nlott) (de csak BDD eset�n?)
		
		//given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//when
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		//then
//		assertEquals(2, filteredTodos.size());
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {
		
		//Az ebben a met�dusban lentebb szerepl� p�ld�k leggyakrabban void-os met�dusok eset�n haszn�land�k (mivel ott nincs visszat�r�si �rt�k, �gy nincs mihez �sszehasonl�tani)
		
		//given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		//Megn�zz�k, hogy az adott Mockon az adott met�dus az adott param�terrel meg lett-e h�vva
		//A times(1) r�sz nem k�telez�, ha megadjuk akkor azt n�zi hogy pontosan h�nyszor lett megh�vva, ha nem adjuk meg akkor csak azt hogy meg lett-e
		verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		
		//Ugyanaz mint a fentebbi sor, csak BDD szintaktik�val (�gy is haszn�lhat�)
		then(todoServiceMock).should().deleteTodo("Learn to Dance");
		
		//Ez is hasonl�, itt azt mondjuk meg hogy legal�bb 1x megh�vja, t�bb lehet, csak kevesebb nem
		verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
		
		//Itt pedig azt mondjuk, hogy legal�bb 5x legyen megh�vva
		verify(todoServiceMock, atLeast(5)).deleteTodo("Learn to Dance");
		
		//Itt pedig a never() param�terrel tudjuk megmondani, hogy azt n�zze meg, hogy nem-e lett megh�vva (teh�t itt akkor bukik el a teszt ha m�gis megh�v�dik)
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
		//Az ArgumentCaptor-ral meg tudjuk n�zni hogy milyen param�ter lett �tadva, �gy haszn�lhat� �sszehasonl�t�shoz
		
		//1.l�p�s (A String.class az�rt kell, mert megmondjuk, hogy olyan param�terre sz�m�tson)
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		
		//given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		
		//2.l�p�s
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		
		//3.l�p�s
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() {
		
		//Az ArgumentCaptor-ral meg tudjuk n�zni hogy milyen param�ter lett �tadva, �gy haszn�lhat� �sszehasonl�t�shoz
		
		//1.l�p�s (A String.class az�rt kell, mert megmondjuk, hogy olyan param�terre sz�m�tson)
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		
		//given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		
		//2.l�p�s (megmondjuk hogy 2x kell lefutnia a sikeres teszthez, �s annyi param�tert is fog elkapni)
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		//3.l�p�s (mivel ebben az esetben �t�rtuk a lista tartalm�t �s 2x is le kell futnia, �gy tudjuk elv�gezni az �sszehasonl�t�st)
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}
}
