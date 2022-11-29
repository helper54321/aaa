package streamsAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) throws IOException {
		
		IntStream
			.range(1, 10) //1-9-ig �rja ki (teh�t a 10-et m�r nem tartalmazza)
			.forEach((x) -> System.out.print(x));
		System.out.println();
		
		
		IntStream
		.range(1, 10) //1-9-ig �rja ki (teh�t a 10-et m�r nem tartalmazza)
		.skip(5) //Az els� 5-�t kihagyja, teh�t csak 6-t�l 9-ig �rja
		.forEach((x) -> System.out.print(x));
		System.out.println();
		
		
		int val = IntStream
				.range(1,  5)
				.sum(); //�sszeadja
		System.out.println(val);
		
		
		Stream.of("Hello", "bottle", "Africa")
			.sorted()
			.findFirst()
			.ifPresent((x) -> System.out.println(x)); //Ha l�tezik x, �rja ki (ny�lv�n ha �res list�t rendezt�nk volna, akkor nem lenne mit ki�rni)
		System.out.println();
		
		String[] items = {"car", "computer", "toothpaste", "box", "pencil", "tent", "door", "toy"};
		Stream.of(items)
			.filter((x) -> x.startsWith("t")) //Csak az marad ami t-vel kezd�dik
			.sorted() //azt rendezz�k
			.forEach(x -> System.out.print(x + ", ")); //majd ki�rjuk
		System.out.println();
		
		
		Arrays.stream(new int[] {2, 4, 6, 8, 10})
			.map((x) -> x * x) //mindet n�gyzetre emeli
			.average() //�tlagol
			.ifPresent(n -> System.out.println(n)); //�s ha van mit, ki�rja
		System.out.println();
		
		
		List<String> listOfItems = Arrays.asList("Computer", "Toothpaste", "Box", "Pencil", "Car", "Tent");
		listOfItems.stream()
			.map(x -> x.toLowerCase())
			.filter(x -> x.startsWith("c"))
			.sorted()
			.forEach(x -> System.out.print(x + ", "));
		System.out.println();
		
		
		Stream<String> lines = Files.lines(Paths.get("files/wordFile.txt"));
		lines.sorted()
			.filter(l -> l.length() > 6)
			.forEach(x -> System.out.print(x + ", "));
		lines.close();
		System.out.println();
		
		
		List<String> words = Files.lines(Paths.get("files/wordFile.txt"))
				.filter(x -> x.contains("th"))
				.collect(Collectors.toList()); //�tkonvert�lja collection form�ba (alapb�l stream volna a t�pus)
		words.forEach(x -> System.out.print(x + ", "));
		System.out.println();
		
		
		Stream<String> rows = Files.lines(Paths.get("files/stockDataCsv.txt"));
		int rowCount = (int) rows
				.map(x -> x.split(","))
				.filter(x -> x.length > 3) //Azok a sorok, amikben 3-n�l t�bb elem van
				.count();
		System.out.println(rowCount + " good rows.");
		rows.close();
		System.out.println();
		
		
		Stream<String> rows2 = Files.lines(Paths.get("files/stockDataCsv.txt"));
		rows2.map(x -> x.split(","))
			.filter(x -> x.length > 3)
			.filter(x -> Integer.parseInt(x[1].trim()) > 15)
			.forEach(x -> System.out.println(x[0].trim() + " " + x[2].trim() + " " + x[3].trim()));
	}
}
