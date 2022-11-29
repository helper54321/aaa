package com.in28minutes.business;

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
import org.mockito.runners.MockitoJUnitRunner;

import com.in28minutes.data.api.TodoService;


//ez az annotáció csak azért kell, hogy a lentebb használt annotációk mûködjenek (de lentebb kiváltjuk egy másikkal, ott a magyarázat is)
//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMockTest {

	//ez a class majdnem ugyanaz mint a másik csak kicsit más megközelítéssel írva (annotációkat használunk)
	
	
	//A fentebbi @RunWith(MockitoJUnitRunner.class) helyett használjuk
	//Azért kell, mert 1 class csak egy @RunWith-et tartalmazhat, így ha többet is használni akarnánk nem mûködne
	//Viszont ezzel a megoldással kiváltjuk, és ebbõl több is lehet
	//Ahhoz hogy mûködjön kötelezõen publicnak kell lennie!!!!! (minden rulenak)
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	//automatikusan létrehoz egy Mock-ot, tehát lényegében a TodoService todoServiceMock = mock(TodoService.class)-t váltja ki
	@Mock
	TodoService todoServiceMock;
	
	//Készít ebbõl egy példányt és megnézi, hogy milyen dependency-k találhatóak a class-jában
	//Ebben az esetben a TodoBusinessImpl classnek 1 dependencyje van: TodoService
	//Miután ezt megállapította, megnézi, hogy azok a dependencyk (ebben az esetben 1), szerepelnek-e itt @Mock-al ellátva
	//Ha igen, akkor automatikusan átadja a létrehozáshoz, tehát a háttérben ez történik: TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	//Ez az annotáció automatikusan létrehoz egy ArgumentCaptort a <> között megadott típussal
	//Tehát lényegében a korábbi ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class); sort váltja ki
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
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
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
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
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
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
		verify(todoServiceMock, atLeast(1)).deleteTodo("Learn to Dance");
		
		//Itt pedig a never() paraméterrel tudjuk megmondani, hogy azt nézze meg, hogy nem-e lett meghívva (tehát itt akkor bukik el a teszt ha mégis meghívódik)
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
		//Az ArgumentCaptor-ral meg tudjuk nézni hogy milyen paraméter lett átadva, így használható összehasonlításhoz
		
		//given
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
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
		
		
		//given
		
		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		
		//2.lépés (megmondjuk hogy 2x kell lefutnia a sikeres teszthez, és annyi paramétert is fog elkapni)
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		//3.lépés (mivel ebben az esetben átírtuk a lista tartalmát és 2x is le kell futnia, így tudjuk elvégezni az összehasonlítást)
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}
}
