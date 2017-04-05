package net.ukr.grygorenko_d;

public class AnnotatedClass {

	public AnnotatedClass() {
		super();
	}

	@MyAnnotation(a = 2, b = 5)
	public void calculate(int a, int b) {
		System.out.println("Result is " + a * b);

	}

}
