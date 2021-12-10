package lec22Pjt001;

public class ClassEx extends AbstractClassEx {

	public ClassEx() {
		System.out.println("ClassEx constructor");
	}
	
	public ClassEx(int i, String s) {
		super(i, s);
	}
	
	@Override
	public void fun2() {
		System.out.println(" -- fun2() START -- ");
	}

}
