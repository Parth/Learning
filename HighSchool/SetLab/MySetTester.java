/**
* @Author ParthMehrotra
*/

public class MySetTester {
	public static void main(String args[]) {
		//First let's add a bunch of things to the set

		MySet setToTest = new MySet();
		setToTest.add("A");
		setToTest.add("B");
		setToTest.add("C");
		setToTest.add("D");
		setToTest.add("D");
		setToTest.add("B");
		setToTest.add("B");
		setToTest.add("A");
		setToTest.add("E");
		setToTest.add("F");
		setToTest.add("G");
		setToTest.add("G");
		setToTest.add("F");

		//Should be just the alphabet
		System.out.println(setToTest);
		//Should be true
		System.out.println(setToTest.contains("A"));
		//Should be true
		System.out.println(setToTest.remove("A"));
		//Should be false
		System.out.println(setToTest.remove("A"));
		//Should be false;
		System.out.println(setToTest.contains("A"));
		//Nice
	}
}
