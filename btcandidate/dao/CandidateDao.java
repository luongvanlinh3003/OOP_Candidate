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

import btcandidate.model.Candidate;
import btcandidate.until.DataBaseUtils;
import btcandidate.until.DateUtils;

/**
 * The Class CandidateDao.
 */
public class CandidateDao {

	/** The prepared statement. */
	private PreparedStatement preparedStatement;

	/** The result set. */
	private ResultSet resultSet;

	/** The conn. */
	private Connection conn;

	/** The Constant SELECT_ONE_CANDIDATE. */
	private static final String SELECT_ONE_CANDIDATE = "SELECT * FROM dbo.Candidate WHERE candidate_id=(?);";

	/** The Constant INSERT_CANDIDATE_RETURN_PK. */
	private static final String INSERT_CANDIDATE_RETURN_PK = "INSERT  dbo.Candidate ( full_name, birth_day, phone, email ) OUTPUT  Inserted.candidate_id VALUES  ( (?), (?), (?), (?));";

	/**
	 * Insert.
	 *
	 * @param candidate
	 *            the candidate
	 * @return true, if successful
	 */
	public boolean insert(Candidate candidate) {
		CertificatedDao certificatedDao = new CertificatedDao();
		Integer candidateId;
		String fullName = candidate.getFullName();
		String birthDay = DateUtils.dateToString(candidate.getBirthDay());
		String phone = candidate.getPhone();
		String email = candidate.getEmail();
		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = conn
					.prepareStatement(INSERT_CANDIDATE_RETURN_PK);
			preparedStatement.setString(1, fullName);
			preparedStatement.setString(2, birthDay);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, email);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			candidateId = resultSet.getInt("candidate_id");
			candidate.setCandidateId(candidateId);
			certificatedDao.insert(candidate.getCertificateds(), candidateId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtils.closeConn(resultSet, preparedStatement, conn);
		}
		return false;
	}

	/**
	 * Delete.
	 *
	 * @param candidateId
	 *            the candidate id
	 * @return true, if successful
	 */
	public boolean delete(String candidateId) {
		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = DataBaseUtils.getPreStatement(conn,
					SELECT_ONE_CANDIDATE);
			preparedStatement.setString(1, candidateId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				resultSet.first();
				resultSet.deleteRow();
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
	 * Update.
	 *
	 * @param candidate
	 *            the candidate
	 * @return true, if successful
	 */
	public boolean update(Candidate candidate) {
		Integer candidateId = candidate.getCandidateId();
		String fullName = candidate.getFullName();
		String birthDay = DateUtils.dateToString(candidate.getBirthDay());
		String phone = candidate.getPhone();
		String email = candidate.getEmail();
		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = DataBaseUtils.getPreStatement(conn,
					SELECT_ONE_CANDIDATE);
			preparedStatement.setInt(1, candidateId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (fullName != null) {
					resultSet.updateString("full_name", fullName);
				}
				if (birthDay != null) {
					resultSet.updateString("birth_day", birthDay);
				}
				if (phone != null) {
					resultSet.updateString("phone", phone);
				}
				if (email != null) {
					resultSet.updateString("email", email);
				}
				resultSet.updateRow();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtils.closeConn(resultSet, preparedStatement, conn);
		}
		return false;
	}

}
