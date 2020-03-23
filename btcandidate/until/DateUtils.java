/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import btcandidate.valid.Validate;

/**
 * The Class DateUtils.
 */
public class DateUtils {

	/** The Constant DATE_PATTERN. */
	private static final String DATE_PATTERN = "yyyy-mm-dd";

	/**
	 * String to date.
	 *
	 * @param date
	 *            the date
	 * @return the date
	 */
	public static Date stringToDate(String date) {
		try {
			return (!Validate.isDate(date))
					? null
					: new SimpleDateFormat(DATE_PATTERN).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Date to string.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String dateToString(Date date) {
		return (date == null)
				? null
				: new SimpleDateFormat(DATE_PATTERN).format(date);
	}

	/**
	 * Gets the year.
	 *
	 * @param date
	 *            the date
	 * @return the year
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		System.out.println(getYear(null));
	}
}
