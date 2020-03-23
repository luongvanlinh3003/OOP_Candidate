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
import btcandidate.model.Intern;
import btcandidate.until.DataBaseUtils;
import btcandidate.until.DateUtils;

/**
 * The Class InternDao.
 */
public class InternDao {

	/** The prepared statement. */
	private PreparedStatement preparedStatement;

	/** The result set. */
	private ResultSet resultSet;

	/** The conn. */
	private Connection conn;

	/** The Constant SELECT_ONE_INTERN. */
	private final static String SELECT_ONE_INTERN = "SELECT * FROM dbo.Intern WHERE candidate_id=(?);";

	/** The Constant SELECT_ALL_INTERN. */
	private final static String SELECT_ALL_INTERN = "SELECT i.candidate_id,c.full_name,c.birth_day,c.phone,c.email,i.majors,i.semester,i.university_name FROM dbo.Candidate c JOIN dbo.Intern i ON i.candidate_id = c.candidate_id;";

	/** The candidate dao. */
	private CandidateDao candidateDao = new CandidateDao();

	private CertificatedDao certificatedDao = new CertificatedDao();

	/**
	 * Insert.
	 *
	 * @param intern
	 *            the intern
	 * @return true, if successful
	 */
	public boolean insert(Intern intern) {
		if (candidateDao.insert(intern)) {
			Integer candidateId = intern.getCandidateId();
			String majors = intern.getMajors();
			Integer semester = intern.getSemester();
			String universityName = intern.getUniversityName();
			try {
				conn = DataBaseUtils.getConnection();
				preparedStatement = DataBaseUtils.getPreStatement(conn,
						SELECT_ONE_INTERN);
				preparedStatement.setString(1, null);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					resultSet.moveToInsertRow();
					resultSet.updateInt("candidate_id", candidateId);
					resultSet.updateString("majors", majors);
					resultSet.updateInt("semester", semester);
					resultSet.updateString("university_name", universityName);
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
	public List<Intern> getList() {
		List<Intern> internList = new ArrayList<>();
		List<Certificated> certificatedList;
		Integer candidateId;

		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = DataBaseUtils.getPreStatement(conn,
					SELECT_ALL_INTERN);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				candidateId = resultSet.getInt("candidate_id");
				certificatedList = certificatedDao.getList(candidateId);
				internList.add(new Intern(candidateId,
						resultSet.getString("full_name"),
						DateUtils
								.stringToDate(resultSet.getString("birth_day")),
						resultSet.getString("phone"),
						resultSet.getString("email"), certificatedList,
						resultSet.getString("majors"),
						resultSet.getInt("semester"),
						resultSet.getString("university_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return internList;
	}

	/**
	 * Update.
	 *
	 * @param intern
	 *            the intern
	 * @return true, if successful
	 */
	public boolean update(Intern intern) {
		if (candidateDao.update(intern)) {
			Integer candidateId = intern.getCandidateId();
			String majors = intern.getMajors();
			Integer semester = intern.getSemester();
			String universityName = intern.getUniversityName();
			try {
				conn = DataBaseUtils.getConnection();
				preparedStatement = DataBaseUtils.getPreStatement(conn,
						SELECT_ONE_INTERN);
				preparedStatement.setInt(1, candidateId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					if (majors != null) {
						resultSet.updateString("majors", majors);
					}
					if (semester != null) {
						resultSet.updateInt("semester", semester);
					}
					if (universityName != null) {
						resultSet.updateString("university_name",
								universityName);
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
