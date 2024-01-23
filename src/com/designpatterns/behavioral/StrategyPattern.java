package com.designpatterns.behavioral;

/**
 * A Strategy pattern is useful when, 1. you need to make a single task 2. and
 * multiple ways to address the task eg. Online Payment(Credit card, Netbanking,
 * Paypal etc), Writing Files(Text, Excel, PDF etc)
 */
public class StrategyPattern {

	public static void main(String[] args) {
		WritingContext PdfWritingContext = new WritingContext(new PdfWriter());
		PdfWritingContext.executeWriting();
	}

}

/**
 * With a Strategy pattern, we can alter the behavior of a class at run-time.
 * Eg. decide which type of printing or payment to be made at runtime based on
 * the choice of the user
 */
interface WritingStrategy {
	public void print();
}

/**
 * A Text writer implementing the WritingStrategy interface. It writes data as
 * text.
 */
class TextWriter implements WritingStrategy {

	@Override
	public void print() {
		System.out.println("Printing text...");
	}

}

/**
 * An Excel writer implementing the WritingStrategy interface. It writes data as
 * Excel.
 */
class ExcelWriter implements WritingStrategy {

	@Override
	public void print() {
		System.out.println("Printing excel...");
	}

}

/**
 * A PDF writer implementing the WritingStrategy interface. It writes data as
 * PDF.
 */
class PdfWriter implements WritingStrategy {

	@Override
	public void print() {
		System.out.println("Printing PDF...");
	}

}

/**
 * The class that does the actual writing. It takes a Strategy  interface argument and exposes a method which in-turn calls
 * the print() method in the strategy interface.
 * At runtime, we can define any type of writer (Text, Excel or PDF) and get it printed without altering any code.
 * This is possible because the Strategy interface stands as a base instance for all its sub-extensions
 */
class WritingContext {
	
	private WritingStrategy writingStrategy;

	protected WritingContext(WritingStrategy writingStrategy) {
		super();
		this.writingStrategy = writingStrategy;
	}
	
	public void executeWriting() {
		writingStrategy.print();
	}
}
