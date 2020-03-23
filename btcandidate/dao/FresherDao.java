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
import btcandidate.model.Fresher;
import btcandidate.until.DataBaseUtils;
import btcandidate.until.DateUtils;

/**
 * The Class FresherDao.
 */
public class FresherDao {

	/** The prepared statement. */
	private PreparedStatement preparedStatement;

	/** The result set. */
	private ResultSet resultSet;

	/** The conn. */
	private Connection conn;

	/** The Constant SELECT_ONE_FRESHER. */
	private final static String SELECT_ONE_FRESHER = "SELECT * FROM dbo.Fresher WHERE candidate_id=(?);";

	/** The Constant SELECT_ALL_FRESHER. */
	private final static String SELECT_ALL_FRESHER = "SELECT f.candidate_id,c.full_name,c.birth_day,c.phone,c.email,f.graduation_date,f.graduation_rank,f.education FROM dbo.Candidate c JOIN dbo.Fresher f ON f.candidate_id = c.candidate_id;";

	/** The candidate dao. */
	private CandidateDao candidateDao = new CandidateDao();
	private CertificatedDao certificatedDao = new CertificatedDao();

	/**
	 * Insert.
	 *
	 * @param fresher
	 *            the fresher
	 * @return true, if successful
	 */
	public boolean insert(Fresher fresher) {

		if (candidateDao.insert(fresher)) {
			Integer candidateId = fresher.getCandidateId();
			String graduationDate = DateUtils
					.dateToString(fresher.getGraduationDate());
			String graduationRank = fresher.getGraduationRank();
			String education = fresher.getEducation();
			try {
				conn = DataBaseUtils.getConnection();
				preparedStatement = DataBaseUtils.getPreStatement(conn,
						SELECT_ONE_FRESHER);
				preparedStatement.setString(1, null);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					resultSet.moveToInsertRow();
					resultSet.updateInt("candidate_id", candidateId);
					resultSet.updateString("graduation_date", graduationDate);
					resultSet.updateString("graduation_rank", graduationRank);
					resultSet.updateString("education", education);
					resultSet.insertRow();
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtils.closeConn(resultSet, preparedStatement, conn);
			}

		}
		return false;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<Fresher> getList() {
		List<Fresher> fresherList = new ArrayList<>();
		List<Certificated> certificatedList;
		Integer candidateId;

		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = DataBaseUtils.getPreStatement(conn,
					SELECT_ALL_FRESHER);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				candidateId = resultSet.getInt("candidate_id");
				certificatedList = certificatedDao.getList(candidateId);
				fresherList.add(new Fresher(candidateId,
						resultSet.getString("full_name"),
						DateUtils
								.stringToDate(resultSet.getString("birth_day")),
						resultSet.getString("phone"),
						resultSet.getString("email"), certificatedList,
						DateUtils.stringToDate(
								resultSet.getString("graduation_date")),
						resultSet.getString("graduation_rank"),
						resultSet.getString("education")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fresherList;
	}

	/**
	 * Update.
	 *
	 * @param fresher
	 *            the fresher
	 * @return true, if successful
	 */
	public boolean update(Fresher fresher) {
		if (candidateDao.update(fresher)) {
			Integer candidateId = fresher.getCandidateId();
			String graduationDate = DateUtils
					.dateToString(fresher.getGraduationDate());
			String graduationRank = fresher.getGraduationRank();
			String education = fresher.getEducation();
			try {
				conn = DataBaseUtils.getConnection();
				preparedStatement = DataBaseUtils.getPreStatement(conn,
						SELECT_ONE_FRESHER);
				preparedStatement.setInt(1, candidateId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					if (graduationDate != null) {
						resultSet.updateString("graduation_date",
								graduationDate);
					}
					if (graduationRank != null) {
						resultSet.updateString("graduation_rank",
								graduationRank);
					}
					if (education != null) {
						resultSet.updateString("education", education);
					}
					resultSet.updateRow();
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtils.closeConn(resultSet, preparedStatement, conn);
			}
		}
		return false;
	}

}
