/*
 * —оздать аннотацию, котора€ принимает параметры дл€ метода. Ќаписать код, 
 * который вызовет метод, помеченный этой аннотацией, и передаст параметры 
 * аннотации в вызываемый метод.
 */
package net.ukr.grygorenko_d;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
		Class<?> cls = AnnotatedClass.class;
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(MyAnnotation.class)) {
				System.out.println("Method \"" + method.getName() + "\" is annotated.");
				MyAnnotation myAnn = method.getAnnotation(MyAnnotation.class);
				AnnotatedClass ac = new AnnotatedClass();
				try {
					method.invoke(ac, myAnn.a(), myAnn.b());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
