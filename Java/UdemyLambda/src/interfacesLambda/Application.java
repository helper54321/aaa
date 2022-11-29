package interfacesLambda;

public class Application {

	public static void main(String[] args) {
		
		Human tom = new Human();
		tom.walk();
		
		walker(tom);
		
		Robot wale = new Robot();
		wale.walk();
		
		walker(wale);
		
		walker(new Walkable() {

			@Override
			public void walk() {
				System.out.println("Custom object walking");
				
			}
			
		});
		
		
		walker(() -> System.out.println("AA object walking"));
		
		//El is menthetj�k �rt�kk�nt (ny�lv�n ebben a p�ld�ban nem sok �rtelme van)
		//Annyi a felt�tele, hogy funkcion�lis interface-nek kell lennie amibe elmentj�k (csak 1 absztrakt met�dusa van)
		//�s ny�lv�n ann�l a funkcion�lis interface-n�l a met�dus param�ternek egyeznie kell ezzel
		ALambdaInterface aBlockOfCode = () -> System.out.println("AA object walking");
		aBlockOfCode.something();
		
		//Att�l, fogy egy funkcion�lis interface-t adok �t, a t�pusa m�g nem fog megegyezni, �gy term�szetesen ezt nem fogadja el
		//walker(aBlockOfCode);
		
		Walkable aBlockOfCode2 = () -> System.out.println("AA object walking");
		walker(aBlockOfCode2); //Itt m�r a t�pus is ok�, �gy elfogadja
		
		ALambdaInterface helloVar = () -> System.out.println("Hello there");
		
		Calculate sumVar = (a, b) -> a + b;
		System.out.println(sumVar.compute(4, 6));
		
		//Ilyenkor m�r ki kell �rni a return-t, mert an�lk�l nem egy�rtelm�
		Calculate nonZeroDivider = (a, b) -> {
			if(a == 0) {
				return 0;
			}
			return a / b;
		};
		
		System.out.println(nonZeroDivider.compute(42, 8));
		
	   StringWorker reverser = str -> {
			String result = "";
			for (int i = str.length() - 1; i >= 0; i--) {
				result += str.charAt(i);
			}
			
			return result;
		};
		
		//Generikus interface-val
		MyGenericInterface<String> reverser2 = str -> {
			String result = "";
			for (int i = str.length() - 1; i >= 0; i--) {
				result += str.charAt(i);
			}
			
			return result;
		};
		
		System.out.println(reverser.work("alma"));
		System.out.println(reverser2.work("k�rte"));
		
		Fact f = num -> {
			int result = 1;
			for(int i = 1; i <= num; i++) {
				result *= i;
			}
			
			return result;
		};
		
		//Generikus interface-val
		MyGenericInterface<Integer> f2 = num -> {
			int result = 1;
			for(int i = 1; i <= num; i++) {
				result *= i;
			}
			
			return result;
		};
		
		System.out.println(f.factorial(5));
		System.out.println(f2.work(10));
		
		

		
		
	}
	
	public static void walker(Walkable walkableEntity) {
		walkableEntity.walk();
	}
	
	public void sayHello() {
		System.out.println("Hello there");
	}
	
	public int sum(int arg1, int arg2) {
		return arg1 + arg2;
	}
	
	public int nonZeroDivide(int a, int b) {
		if(a == 0) {
			return 0;
		}
		return a / b;
	}
	
	public String reverse(String str) {
		String result = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			result += str.charAt(i);
		}
		
		return result;
	}
	
	public int factorial(int num) {
		int result = 1;
		for(int i = 1; i <= num; i++) {
			result *= i;
		}
		
		return result;
	}
	
	
	interface Calculate{
		public int compute(int a, int b);
	}
	
	interface StringWorker{
		public String work(String s);
	}
	
	interface Fact{
		public int factorial(int num);
	}
	
	interface MyGenericInterface<T>{
		public T work(T s);
	}
}
