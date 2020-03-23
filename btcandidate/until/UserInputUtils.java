/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.until;

import java.util.Date;
import java.util.Scanner;

import btcandidate.exception.BirthDayException;
import btcandidate.exception.EmailException;
import btcandidate.valid.Validate;

/**
 * The Class UserInputUtils.
 */
public class UserInputUtils {

	/** The scanner. */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Input type int.
	 *
	 * @return the int
	 */
	public static int inputTypeInt() {
		String input;
		do {
			input = scanner.nextLine();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("TYPE INT !!!");
				System.out.print("Input again: ");
			}
		} while (true);

	}

	/**
	 * Input type date.
	 *
	 * @return the date
	 */
	public static Date inputTypeDate() {
		String input;
		do {
			input = scanner.nextLine();
			try {
				if (Validate.isDate(input)) {
					return DateUtils.stringToDate(input);
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("TYPE DATE (yyyy-mm-dd) !!!");
				System.out.print("Input again: ");
			}
		} while (true);
	}

	/**
	 * Input type birth day.
	 *
	 * @return the date
	 */
	public static Date inputTypeBirthDay() {
		Date date;
		do {
			try {
				date = inputTypeDate();
				if (Validate.isBirthDay(date)) {
					return date;
				}
			} catch (BirthDayException e) {
				System.out.println(e);
				System.out.println("BIRTH YEAR >= 1990 !!!");
				System.out.print("Input again: ");
			}

		} while (true);
	}

	/**
	 * Input type email.
	 *
	 * @return the string
	 */
	public static String inputTypeEmail() {
		String input;
		do {
			input = scanner.nextLine();
			try {
				if (Validate.isEmail(input)) {
					return input;
				} else {
					throw new EmailException();
				}
			} catch (EmailException e) {
				System.out.println(e);
				System.out.println("TYPE EMAIL !!!");
				System.out.print("Input again: ");
			}
		} while (true);
	}

	/**
	 * Input type string.
	 *
	 * @return the string
	 */
	public static String inputTypeString() {
		return scanner.nextLine();
	}
}
