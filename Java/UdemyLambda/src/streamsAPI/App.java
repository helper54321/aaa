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
			.range(1, 10) //1-9-ig írja ki (tehát a 10-et már nem tartalmazza)
			.forEach((x) -> System.out.print(x));
		System.out.println();
		
		
		IntStream
		.range(1, 10) //1-9-ig írja ki (tehát a 10-et már nem tartalmazza)
		.skip(5) //Az elsõ 5-öt kihagyja, tehát csak 6-tól 9-ig írja
		.forEach((x) -> System.out.print(x));
		System.out.println();
		
		
		int val = IntStream
				.range(1,  5)
				.sum(); //Összeadja
		System.out.println(val);
		
		
		Stream.of("Hello", "bottle", "Africa")
			.sorted()
			.findFirst()
			.ifPresent((x) -> System.out.println(x)); //Ha létezik x, írja ki (nyílván ha üres listát rendeztünk volna, akkor nem lenne mit kiírni)
		System.out.println();
		
		String[] items = {"car", "computer", "toothpaste", "box", "pencil", "tent", "door", "toy"};
		Stream.of(items)
			.filter((x) -> x.startsWith("t")) //Csak az marad ami t-vel kezdõdik
			.sorted() //azt rendezzük
			.forEach(x -> System.out.print(x + ", ")); //majd kiírjuk
		System.out.println();
		
		
		Arrays.stream(new int[] {2, 4, 6, 8, 10})
			.map((x) -> x * x) //mindet négyzetre emeli
			.average() //átlagol
			.ifPresent(n -> System.out.println(n)); //és ha van mit, kiírja
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
				.collect(Collectors.toList()); //Átkonvertálja collection formába (alapból stream volna a típus)
		words.forEach(x -> System.out.print(x + ", "));
		System.out.println();
		
		
		Stream<String> rows = Files.lines(Paths.get("files/stockDataCsv.txt"));
		int rowCount = (int) rows
				.map(x -> x.split(","))
				.filter(x -> x.length > 3) //Azok a sorok, amikben 3-nál több elem van
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
