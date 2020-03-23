/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.main;

import java.util.Scanner;

/**
 * The Class ManagerApp.
 */
public class ManagerApp {

	/** The scanner. */
	public static Scanner scanner = new Scanner(System.in);

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			new MenuControl().run();
		} catch (Exception e) {
			System.err.println(
					"The system has encountered an unexpected problem, sincerely sorry !!!");
		}
	}

}
