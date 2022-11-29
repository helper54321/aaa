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


//ez az annot�ci� csak az�rt kell, hogy a lentebb haszn�lt annot�ci�k m�k�djenek (de lentebb kiv�ltjuk egy m�sikkal, ott a magyar�zat is)
//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMockTest {

	//ez a class majdnem ugyanaz mint a m�sik csak kicsit m�s megk�zel�t�ssel �rva (annot�ci�kat haszn�lunk)
	
	
	//A fentebbi @RunWith(MockitoJUnitRunner.class) helyett haszn�ljuk
	//Az�rt kell, mert 1 class csak egy @RunWith-et tartalmazhat, �gy ha t�bbet is haszn�lni akarn�nk nem m�k�dne
	//Viszont ezzel a megold�ssal kiv�ltjuk, �s ebb�l t�bb is lehet
	//Ahhoz hogy m�k�dj�n k�telez�en publicnak kell lennie!!!!! (minden rulenak)
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	//automatikusan l�trehoz egy Mock-ot, teh�t l�nyeg�ben a TodoService todoServiceMock = mock(TodoService.class)-t v�ltja ki
	@Mock
	TodoService todoServiceMock;
	
	//K�sz�t ebb�l egy p�ld�nyt �s megn�zi, hogy milyen dependency-k tal�lhat�ak a class-j�ban
	//Ebben az esetben a TodoBusinessImpl classnek 1 dependencyje van: TodoService
	//Miut�n ezt meg�llap�totta, megn�zi, hogy azok a dependencyk (ebben az esetben 1), szerepelnek-e itt @Mock-al ell�tva
	//Ha igen, akkor automatikusan �tadja a l�trehoz�shoz, teh�t a h�tt�rben ez t�rt�nik: TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	//Ez az annot�ci� automatikusan l�trehoz egy ArgumentCaptort a <> k�z�tt megadott t�pussal
	//Teh�t l�nyeg�ben a kor�bbi ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class); sort v�ltja ki
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
		
		//Ennek az eg�sz megk�zel�t�snek az a l�nyege, hogy felt�telekt�l f�gg�v� tessz�k hogy mi t�rt�njen
		//Teh�t pl 'A' esetben 'x'-et adjon vissza, 'B' esetben pedig 'y'-t
		//�gy enn�l a p�ld�n�l a when �s thenReturn p�r helyett given �s willReturn-t haszn�lunk
		//Tov�bb� az assertEquals helyett az assertThat-et haszn�lhatjuk (aj�nlott) (de csak BDD eset�n?)
		
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
		
		//Az ebben a met�dusban lentebb szerepl� p�ld�k leggyakrabban void-os met�dusok eset�n haszn�land�k (mivel ott nincs visszat�r�si �rt�k, �gy nincs mihez �sszehasonl�tani)
		
		//given
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
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
		verify(todoServiceMock, atLeast(1)).deleteTodo("Learn to Dance");
		
		//Itt pedig a never() param�terrel tudjuk megmondani, hogy azt n�zze meg, hogy nem-e lett megh�vva (teh�t itt akkor bukik el a teszt ha m�gis megh�v�dik)
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
		//Az ArgumentCaptor-ral meg tudjuk n�zni hogy milyen param�ter lett �tadva, �gy haszn�lhat� �sszehasonl�t�shoz
		
		//given
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
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
		
		
		//given
		
		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		//when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		
		//2.l�p�s (megmondjuk hogy 2x kell lefutnia a sikeres teszthez, �s annyi param�tert is fog elkapni)
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		//3.l�p�s (mivel ebben az esetben �t�rtuk a lista tartalm�t �s 2x is le kell futnia, �gy tudjuk elv�gezni az �sszehasonl�t�st)
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}
}
