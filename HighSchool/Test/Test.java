public class Test {
	public static void main(String args[]) {
		System.out.println(product(5, 2));
	}

	public static int product(int m, int k) {
		if (m == 1) return k;
		return product(m-1, k) + k;
	}
}
