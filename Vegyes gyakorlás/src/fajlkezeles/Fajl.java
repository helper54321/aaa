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
		
		
		//F�jlb�l olvas�s
		
		try(FileReader fr = new FileReader(file1); BufferedReader br = new BufferedReader(fr);){
			
			String line = br.readLine();
			lines.add(line);
			
			
			//Az�rt nem bel�l olvasom ki az �j sort, mert akkor csak bent ellen�rzi, �s a v�g�re egy null-t is hozz�f�z
			while((line = br.readLine()) != null) {
				lines.add(line);
				System.out.println("A line: " + line);
			}
			
			lines.forEach(l -> System.out.println(l));
			
			
		}catch(Exception e) {
			System.out.println("Nem j� olvas�s");
		}
		
		
		
		//F�jlba �r�s 
		//A FileWriter 2. param�tere opcion�lis, jelent�se, hogy enged�lyezem-e a hozz�f�z�st, vagy fel�l�rja (true miatt hozz�f�zi)
		
		try(FileWriter fw = new FileWriter(file2, true); BufferedWriter bw = new BufferedWriter(fw);){
			
			//Ha m�g nem l�tezik a f�jl, l�trehozza
			if (!file2.exists()) {
			     file2.createNewFile();
			  }
			
			for(String s : lines) {
				bw.write(s + "\n"); //Menjen �j sorba, am�gy egybe�rn� az eg�szet
				
			}
			
		}catch(Exception e) {
			System.out.println("Nem j� �r�s");
		}
	}
}


