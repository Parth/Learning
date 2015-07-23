/**
* @Author: ParthMehrotra
*/

import java.util.Iterator;
import java.util.ArrayList;

public class MySet implements PartialSet {

	private ArrayList<String> strings;
	private int size;

	public MySet() {
		strings = new ArrayList<String>();
		size = 0;
	}

	public boolean add(String str) {
		
		if (!this.contains(str)) {
			size++;
			strings.add(str);
		}
		
		return contains(str);
	}

	public boolean contains(String str) {
		boolean isItInThisArray = false;

		for (int i = 0; i < this.size(); i++) {
			if (strings.get(i).equals(str.toString())) {
				isItInThisArray = true;
			}
		}

		return isItInThisArray;
	}

	public Iterator iterator() {
		return strings.iterator();
	}

	public boolean remove(String str) {
		size--;
		return strings.remove(str);
	}

	public int size() {
		return size;
	}

	public String toString() {
		return strings.toString();
	}

}
