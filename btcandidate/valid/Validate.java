/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.valid;

import java.util.Date;

import btcandidate.exception.BirthDayException;
import btcandidate.exception.EmailException;
import btcandidate.until.DateUtils;

/**
 * The Class Validate.
 */
public class Validate {

	/** The Constant EMAIL_REGEX. */
	public static final String EMAIL_REGEX = "^.+@.+\\..+$";

	/** The Constant PHONE_REGEX. */
	public static final String PHONE_REGEX = "\\\\d{7,}";

	/** The Constant BIRTH_YEAR. */
	public static final int BIRTH_YEAR = 1990;

	/** The Constant DATE_REGEX. */
	private final static String DATE_REGEX = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

	/**
	 * Checks if is email.
	 *
	 * @param email
	 *            the email
	 * @return true, if is email
	 * @throws EmailException
	 *             the email exception
	 */
	public static boolean isEmail(String email) throws EmailException {
		if (email != null && email.matches(EMAIL_REGEX)) {
			return true;
		} else {
			throw new EmailException();
		}
	}

	/**
	 * Checks if is phone.
	 *
	 * @param phone
	 *            the phone
	 * @return true, if is phone
	 */
	public static boolean isPhone(String phone) {
		return (phone == null) ? false : phone.matches(PHONE_REGEX);
	}

	/**
	 * Checks if is birth day.
	 *
	 * @param birthDay
	 *            the birth day
	 * @return true, if is birth day
	 * @throws BirthDayException
	 *             the birth day exception
	 */
	public static boolean isBirthDay(Date birthDay) throws BirthDayException {
		if (DateUtils.getYear(birthDay) >= BIRTH_YEAR) {
			return true;
		} else {
			throw new BirthDayException();
		}
	}

	/**
	 * Checks if is date.
	 *
	 * @param date
	 *            the date
	 * @return true, if is date
	 */
	public static boolean isDate(String date) {
		return (date == null) ? false : date.matches(DATE_REGEX);
	}

}
