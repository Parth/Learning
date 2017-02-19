public class mains {

	public static void main(String argp[]) {
		double timeBefore = System.currentTimeMillis();

		int sum = 0;

		for (int i = 0; i < 1000000000; i++) {
			sum *= i;
		}
		
		double timeAfter = System.currentTimeMillis();
		
		System.out.println("The opperation took: "+ (double) ((double)timeAfter - (double)timeBefore));
	}

}
