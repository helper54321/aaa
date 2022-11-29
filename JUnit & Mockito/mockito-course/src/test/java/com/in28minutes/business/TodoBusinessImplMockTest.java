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
		
		//Ennek az egész megközelítésnek az a lényege, hogy feltételektõl függõvé tesszük hogy mi történjen
		//Tehát pl 'A' esetben 'x'-et adjon vissza, 'B' esetben pedig 'y'-t
		//Így ennél a példánál a when és thenReturn pár helyett given és willReturn-t használunk
		//Továbbá az assertEquals helyett az assertThat-et használhatjuk (ajánlott) (de csak BDD esetén?)
		
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
		
		//Az ebben a metódusban lentebb szereplõ példák leggyakrabban void-os metódusok esetén használandók (mivel ott nincs visszatérési érték, így nincs mihez összehasonlítani)
		
		//given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		//Megnézzük, hogy az adott Mockon az adott metódus az adott paraméterrel meg lett-e hívva
		//A times(1) rész nem kötelezõ, ha megadjuk akkor azt nézi hogy pontosan hányszor lett meghívva, ha nem adjuk meg akkor csak azt hogy meg lett-e
		verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		
		//Ugyanaz mint a fentebbi sor, csak BDD szintaktikával (így is használható)
		then(todoServiceMock).should().deleteTodo("Learn to Dance");
		
		//Ez is hasonló, itt azt mondjuk meg hogy legalább 1x meghívja, több lehet, csak kevesebb nem
		verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
		
		//Itt pedig azt mondjuk, hogy legalább 5x legyen meghívva
		verify(todoServiceMock, atLeast(5)).deleteTodo("Learn to Dance");
		
		//Itt pedig a never() paraméterrel tudjuk megmondani, hogy azt nézze meg, hogy nem-e lett meghívva (tehát itt akkor bukik el a teszt ha mégis meghívódik)
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
		//Az ArgumentCaptor-ral meg tudjuk nézni hogy milyen paraméter lett átadva, így használható összehasonlításhoz
		
		//1.lépés (A String.class azért kell, mert megmondjuk, hogy olyan paraméterre számítson)
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		
		//given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		
		//2.lépés
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		
		//3.lépés
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() {
		
		//Az ArgumentCaptor-ral meg tudjuk nézni hogy milyen paraméter lett átadva, így használható összehasonlításhoz
		
		//1.lépés (A String.class azért kell, mert megmondjuk, hogy olyan paraméterre számítson)
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		
		//given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		
		//2.lépés (megmondjuk hogy 2x kell lefutnia a sikeres teszthez, és annyi paramétert is fog elkapni)
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		//3.lépés (mivel ebben az esetben átírtuk a lista tartalmát és 2x is le kell futnia, így tudjuk elvégezni az összehasonlítást)
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}
}
