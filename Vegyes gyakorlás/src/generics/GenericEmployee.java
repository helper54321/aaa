package generics;

public class GenericEmployee <S>{
	
	private S val;
	

	public GenericEmployee() {
		super();
	}
	
	public GenericEmployee(S val) {
		super();
		this.val = val;
	}

	public S getVal() {
		return val;
	}

	public void setVal(S val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "Employee [val=" + val + "]";
	}
	
	

}
