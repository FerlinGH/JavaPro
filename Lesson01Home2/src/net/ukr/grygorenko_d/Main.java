/*
 * Ќаписать класс TextContainer, который содержит в себе строку. — помощью 
 * механизма аннотаций указать 1) в какой файл должен сохранитьс€ текст 
 * 2) метод, который выполнит сохранение. Ќаписать класс Saver,
который сохранит поле класса TextContainer в указанный файл.
 *
 */

package net.ukr.grygorenko_d;

public class Main {

	public static void main(String[] args) {
		Saver saver = new Saver();
		saver.process(TextContainer.class);

	}

}
