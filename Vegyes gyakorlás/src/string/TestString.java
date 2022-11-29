package string;

public class TestString {

	public static void main(String[] args) {
		stringes();
		
		stingPelda1("aaacodebbb");
		stingPelda2("Hello", 3);
		stringPelda3("abXYabc", 3);
		stringPelda3b("abXYabc", 3);
	}
	

	//charAt
	//compareTo, compareToIgnoreCase
	//contains
	//startsWith, endsWith
	//equalsIgnoreCase
	//indexOf, lastIndexOf
	//isEmpty
	//replace, replaceFirst
	//substring
	//trim
	//valueOf
	public static void stringes() {
		
		String s1 = "This is a test string";
		String s2 = "This is a TEST string";
		String s3 = "Some string";
		
		System.out.println(s1.charAt(2));
		
		System.out.println(s1.compareTo(s3)); //Pozitív integer, ha a paraméteres elõrébb van abc szerint
		System.out.println(s3.compareTo(s1)); //Negatív integer, ha a paraméteres hátrébb van abc szerint
		System.out.println(s1.compareToIgnoreCase(s2)); //0, mivel igoráljuk a betûméretet (amúgy pozitív érték lenne, tehát a nagybetûs elõrébb van)
		
		System.out.println(s1.contains("test"));
		
		System.out.println(s1.startsWith("is", 5)); //2. paraméter nélkül az elejérõl nézi
		System.out.println(s1.endsWith("ing"));
		
		System.out.println(s1.equalsIgnoreCase(s2));
		
		System.out.println(s1.indexOf("s"));
		System.out.println(s1.indexOf("test")); //Honnantól kezdve indexét adja vissza (tehát a t betûét)
		System.out.println(s1.indexOf("s", 4)); //Honnan kezdi keresni
		System.out.println(s1.indexOf("test", 10)); //Honnan kezdi keresni
		System.out.println(s1.lastIndexOf("s")); //Ugyanaz mint a sima, csak a végérõl keresi (de az indexeket rendesen számolja, nem a végétõl)
		
		System.out.println(s1.isEmpty());
		
		System.out.println(s1.replace("s", "x")); //s-eket x-re cseréli (case sensitive)
		System.out.println(s1.replace("This", "Uj")); //Ugyanaz csak stringre
		System.out.println(s1.replaceFirst("is", "at")); //Csak a legelsõ találatot
		
		
		System.out.println(s1.substring(5)); //Megadott index-el kezdve végig
		System.out.println(s1.substring(5, 7)); //Végindex már nincs benne (tehát lényegében 2.paraméter - 1. paraméternyi karaktert ad vissza) (itt most 7 - 5 = 2-t)
		
		System.out.println(s1.trim()); //Elejérõl és végérõl levágja a white karaktereket (pl space)
		
		String ss = String.valueOf(25); //Stringgé konvertálja (nem csak int lehet a paraméter, lehet pl double, char, boolean is)
		
	}
	
	
	
	//Return the number of times that the string "code" appears anywhere in the given string, except we'll accept any letter for the 'd', so "cope" and "cooe" count.
	private static int stingPelda1(String str) {
		
		int count = 0;
		String substr = "co";
		
		for (int i = 0; i < str.length() - 3; i++) {
			if(str.startsWith(substr, i) && str.charAt(i + 3) == 'e') {
				count++;
			}
		}
		
		return count;
	}

	
	
	//Given a string and an int n, return a string made of n repetitions of the last n characters of the string. You may assume that n is between 0 and the length of the string, inclusive.
	private static String stingPelda2(String str, int n) {
		
		String ret = "";
		String substr = str.substring(str.length() - n);
		
		for (int i = 0; i < n; i++) {
			ret += substr;
		}
		
		
		return ret;
	}
	
	
	
	//Given a string, consider the prefix string made of the first N chars of the string. Does that prefix string appear somewhere else in the string? Assume that the string is not empty and that N is in the range 1..str.length().
	private static boolean stringPelda3(String str, int n) {
		
		String prefix = str.substring(0, n);
		
		for (int i = 1; i < str.length() - n + 1; i++) {
			if(str.startsWith(prefix, i)) return true;
		}
		
		return false;
	}
	
	
	
	//Given a string, consider the prefix string made of the first N chars of the string. Does that prefix string appear somewhere else in the string? Assume that the string is not empty and that N is in the range 1..str.length().
	private static boolean stringPelda3b(String str, int n) {
		String prefix = str.substring(0, n);
		String cutted = str.substring(1);
		
		return cutted.contains(prefix);
	}
	
	
}
