public class MontyHall {
	public static void main(String[] args) {
		int first_guess_success = 0;
		int second_guess = 0;
		int trials = 10000;

		for (int a = 0; a < trials; a++) {
			boolean[] doors = new boolean[3];

			int car = (int) (Math.random() * 3);

			for (int i = 0; i < doors.length; i++) {
				doors[i] = car == i;
			}

			int guess = (int) (Math.random() * 3);
			if (guess == car) {
				first_guess_success++;
			}

			if (guess != car) { 
				second_guess++;
			}
		}

		System.out.println(first_guess_success);
		System.out.println(second_guess);
	}
}
