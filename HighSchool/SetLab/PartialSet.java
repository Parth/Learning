/**
* @Auther ParthMehrotra
*/

import java.util.Iterator;

public interface PartialSet {
	public boolean add(String str);
	public boolean contains(String str);
	public Iterator iterator();
	public boolean remove(String str);
	public int size();
}
