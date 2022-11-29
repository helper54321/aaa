package generics;

public class GenericAccountant<S, T> extends GenericEmployee<S>{
	
	private S val1;
	private T val2;
	
	public GenericAccountant() {
		super();
	}

	public GenericAccountant(S val1, T val2) {
		super(val1);
		this.val2 = val2;
	}

	public S getVal1() {
		return val1;
	}

	public void setVal1(S val1) {
		this.val1 = val1;
	}

	public T getVal2() {
		return val2;
	}

	public void setVal2(T val2) {
		this.val2 = val2;
	}

	@Override
	public String toString() {
		return "Accountant [val1=" + val1 + ", val2=" + val2 + "]";
	}
	
	
	
	

}
