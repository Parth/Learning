public class Test {
	public int find(int g, int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == g) {
				return i;
			}
		}
		return -1;
	}
}
