package lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda {

	public static void main(String[] args) {
		streamTypes();
	}
	
	
	//forEach (kiíratás)
	//filter  (az marad, ami teljesíti a feltételt, többi kiesik)
	//map	  (az elembõl egy módosított elem lesz)
	//sort	  (rendezi)
	//reduce  (egy tömb/listából egy értéket állít elõ, pl összad)
	//limit	  (azzal az értékkel bezárólag megtartja az elemeket, nagyobbakat törli)
	//max,min (maximum/minimum érték)
	//skip	  (az elsõ annyi elemet kihagyja, pl 3-as paraméter esetén az elsõ 3 elemet)
	
	//Stream.of(items) 		//Stringtömb esetén
	//Arrays.stream(items)	//Tömb esetén
	//items.stream()		//Collectionok esetén
	//Files.lines(Paths.get("files/wordFile.txt"))	//Fájlok esetén
	public static void higherOrderFunctions() {
		Integer[] numbers = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		List<Integer> arrayList = Arrays.asList(numbers);
		
		List<Integer> newList = arrayList.stream()
				.map(num -> num * 3)
				.filter(num -> num > 10)
				.collect(Collectors.toList()); //Listté konvertálja, alapból stream volna
		
		for(int num : newList) {
			System.out.print(num + " ");
		}
		
		System.out.println();
		
		int sum = arrayList.stream()
				.map(num -> num + 2)
				.reduce(0, (val, num) -> val + num); //0 a kezdõérték, val a kezdõérték jelenlegi értéke, num pedig maga az elem
		
		System.out.println(sum);
		
		//Megfordítja az elemeket
		arrayList.stream()
				.sorted((a, b) -> b - a)
				.forEach(num -> System.out.print(num + " "));
		
		System.out.println();
		
		arrayList.stream()
			.limit(5)
			.forEach(x -> System.out.print(x + " "));
		
		System.out.println();
		
		//maximumot adja vissza
		 System.out.println(arrayList.stream().max((a, b) -> a - b));
		 
		 arrayList.stream()
		 	.skip(3)
		 	.forEach(x -> System.out.print(x + " "));
	}
	
	
	
	
	
	public static void streamTypes() {
		int[] intArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String[] stringArray = {"elso", "masodik", "harmadik", "negyedik", "otodik"};
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		//Arrayes
		int val = Arrays.stream(intArray)
			.sum();
		System.out.println(val);
		
		Arrays.stream(stringArray)
			.sorted()
			.forEach(str -> System.out.println(str));
		
		
		//Listes
		
		list.stream()
			.sorted((a, b) -> b - a)
			.skip(3)
			.filter(x -> (x * x) > 25)
			.forEach(x -> System.out.print(x + " "));
		System.out.println();
		
		System.out.println(list);
		
		System.out.println("-------------------------");
		
		
		//Fájlos
		
		try {
			Files.lines(Paths.get("fajlok/teszteleshez.txt"))
				.filter(line -> line.length() > 5)
				.map(line -> line + " hosszabb mint 5")
				.sorted()
				.forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}