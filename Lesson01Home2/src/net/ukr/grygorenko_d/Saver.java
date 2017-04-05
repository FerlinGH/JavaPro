package net.ukr.grygorenko_d;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Saver {
	public void process(Class<?>... classes) {
		for (Class<?> cls : classes) {
			if (cls.isAnnotationPresent(SaveTo.class)) {
				SaveTo clsAnn = cls.getAnnotation(SaveTo.class);
				String path = clsAnn.path();
				File file = new File(path);
				try {
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				TextContainer tx = createContainer(cls);

				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(Save.class)) {
						try {
							method.invoke(tx, file);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private TextContainer createContainer(Class<?> cls) {
		Class<?>[] paramTypes = new Class<?>[] { String.class };
		TextContainer tx = null;
		try {
			Constructor<?> cnstr = cls.getConstructor(paramTypes);
			tx = (TextContainer) cnstr.newInstance("Saved by annotations.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tx;
	}

}
