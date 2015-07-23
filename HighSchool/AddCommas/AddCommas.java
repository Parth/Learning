/**
*	@author:	Parth Mehrotra
*	@assignment:	Chapter 12 Graded Lab
*/

public class AddCommas {
	/**
	* @param args	A string containing either a positive integer to add commas to, 
	*		or the word help, which documents the ussage of the program.
	*
	* The precondition stated in the book was for the method, not the program,
	* so I just handled a couple of errors just to be on the safe side.
	*/
	public static void main(String[] args) {
		// Check if the user entered anything
		if (args.length == 0) {
			displayError(NO_INPUT_MESSAGE);
		}

		// Incase they don't know how to use the program
		if (args[0].equalsIgnoreCase("help")) {
			displayError(HELP);
		}

		// Make sure it is a number, and that it is a positive number.
		try {
			long a = Long.parseLong(args[0]); // 	The only bad thing about using this, is that the input number
							  //	must be less than Long.MAX_VALUE
			if (a < 0) {
				displayError(NOT_A_POSITIVE_NUMBER_MESSAGE);
			}
		} catch (NumberFormatException e) {
			displayError(NOT_A_NUMBER_MESSAGE);
		}

		// Great, this is valid data, let's continue.
		System.out.println(insertCommas(args[0]));
	}

	/**
	* Method takes a positive integer and recursively adds commas to it, then returns the product
	*
	*
	* @param arg	A String that contains a positive integer
	* @return 	A positive integer that contains commas.
	*/
	public static String insertCommas(String arg) {
		if (arg.length() <= 3) {
			return arg;
		} else {
			return insertCommas(arg.substring(0, arg.length()-3)) + "," + arg.substring(arg.length()-3, arg.length());
		}
	}
	
	/**
	* Displays a fatal error then exits
	* @param arg	A String that will be displayed before the program terminates
	*/
	private static void displayError(String arg) {
		System.out.println(arg);
		System.exit(0);
	}

	private static final String NO_INPUT_MESSAGE =			"You did not enter anything at all, for help type AddCommas help";
	private static final String NOT_A_NUMBER_MESSAGE =		"You did not give a number";
	private static final String NOT_A_POSITIVE_NUMBER_MESSAGE =	"Number must be positive";
	private static final String HELP =				"You must enter a positive integer. For example:\njava AddCommas 123456";
}
