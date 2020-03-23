/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class DataBaseUtils.
 */
public class DataBaseUtils {

	/** The Constant URL. */
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=CandidateUyn";

	/** The Constant ACCOUNT. */
	private static final String ACCOUNT = "sa";

	/** The Constant PASSWORD. */
	private static final String PASSWORD = "123456";

	/** The Constant CLASS_FORNAME. */
	private static final String CLASS_FORNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(CLASS_FORNAME);
			conn = DriverManager.getConnection(URL, ACCOUNT, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Close conn.
	 *
	 * @param resultSet
	 *            the result set
	 * @param preparedStatement
	 *            the prepared statement
	 * @param conn
	 *            the conn
	 */
	public static void closeConn(ResultSet resultSet,
			PreparedStatement preparedStatement, Connection conn) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the pre statement.
	 *
	 * @param conn
	 *            the conn
	 * @param sql
	 *            the sql
	 * @return the pre statement
	 */
	public static PreparedStatement getPreStatement(Connection conn,
			String sql) {
		try {
			return conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
