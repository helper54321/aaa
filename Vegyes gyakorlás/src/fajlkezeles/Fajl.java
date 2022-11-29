package fajlkezeles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Fajl {
	
	public static void main(String[] args) {
		files();
	}

	
	
	public static void files() {
		File file1 = new File("fajlok/teszteleshez.txt");
		File file2 = new File("fajlok/beleirni.txt");
		List<String> lines = new ArrayList<>();
		
		
		//Fájlból olvasás
		
		try(FileReader fr = new FileReader(file1); BufferedReader br = new BufferedReader(fr);){
			
			String line = br.readLine();
			lines.add(line);
			
			
			//Azért nem belül olvasom ki az új sort, mert akkor csak bent ellenõrzi, és a végére egy null-t is hozzáfûz
			while((line = br.readLine()) != null) {
				lines.add(line);
				System.out.println("A line: " + line);
			}
			
			lines.forEach(l -> System.out.println(l));
			
			
		}catch(Exception e) {
			System.out.println("Nem jó olvasás");
		}
		
		
		
		//Fájlba írás 
		//A FileWriter 2. paramétere opcionális, jelentése, hogy engedélyezem-e a hozzáfûzést, vagy felülírja (true miatt hozzáfûzi)
		
		try(FileWriter fw = new FileWriter(file2, true); BufferedWriter bw = new BufferedWriter(fw);){
			
			//Ha még nem létezik a fájl, létrehozza
			if (!file2.exists()) {
			     file2.createNewFile();
			  }
			
			for(String s : lines) {
				bw.write(s + "\n"); //Menjen új sorba, amúgy egybeírná az egészet
				
			}
			
		}catch(Exception e) {
			System.out.println("Nem jó írás");
		}
	}
}


