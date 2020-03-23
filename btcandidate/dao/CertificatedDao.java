/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import btcandidate.model.Certificated;
import btcandidate.until.DataBaseUtils;
import btcandidate.until.DateUtils;

/**
 * The Class CertificatedDao.
 */
public class CertificatedDao {

	/** The prepared statement. */
	private PreparedStatement preparedStatement;

	/** The result set. */
	private ResultSet resultSet;

	/** The conn. */
	private Connection conn;

	/** The Constant SELECT_ONE_CERTIFICATED. */
	private static final String SELECT_ONE_CERTIFICATED = "SELECT * FROM dbo.Certificated WHERE candidate_id=(?);";

	/**
	 * Insert.
	 *
	 * @param certificatedList
	 *            the certificated list
	 * @param candidateId
	 *            the candidate id
	 * @return true, if successful
	 */
	public boolean insert(List<Certificated> certificatedList,
			Integer candidateId) {
		if (certificatedList == null || certificatedList.isEmpty()
				|| candidateId == null) {
			return false;
		}
		String certificatedName;
		String certificatedRank;
		String certificatedDate;
		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = DataBaseUtils.getPreStatement(conn,
					SELECT_ONE_CERTIFICATED);
			preparedStatement.setString(1, null);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				for (Certificated certificated : certificatedList) {
					certificatedName = certificated.getCertificateName();
					certificatedRank = certificated.getCertificateRank();
					certificatedDate = DateUtils
							.dateToString(certificated.getCertificateDate());
					resultSet.moveToInsertRow();
					resultSet.updateInt("candidate_id", candidateId);
					resultSet.updateString("certificated_name",
							certificatedName);
					resultSet.updateString("certificated_rank",
							certificatedRank);
					resultSet.updateString("certificated_date",
							certificatedDate);
					resultSet.insertRow();
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtils.closeConn(resultSet, preparedStatement, conn);
		}
		return false;
	}

	/**
	 * Gets the list.
	 *
	 * @param candidateId
	 *            the candidate id
	 * @return the list
	 */
	public List<Certificated> getList(Integer candidateId) {
		List<Certificated> certificatedList = new ArrayList<>();
		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = DataBaseUtils.getPreStatement(conn,
					SELECT_ONE_CERTIFICATED);
			preparedStatement.setInt(1, candidateId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				certificatedList.add(new Certificated(
						resultSet.getInt("certificated_id"),
						resultSet.getString("certificated_name"),
						resultSet.getString("certificated_rank"),
						DateUtils.stringToDate(
								resultSet.getString("certificated_date"))));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtils.closeConn(resultSet, preparedStatement, conn);
		}
		return certificatedList;
	}

}
