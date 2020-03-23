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
import btcandidate.model.Experience;
import btcandidate.until.DataBaseUtils;
import btcandidate.until.DateUtils;

/**
 * The Class ExperienceDao.
 */
public class ExperienceDao {

	/** The prepared statement. */
	private PreparedStatement preparedStatement;

	/** The result set. */
	private ResultSet resultSet;

	/** The conn. */
	private Connection conn;

	/** The Constant SELECT_ONE_EXPERIENCE. */
	private final static String SELECT_ONE_EXPERIENCE = "SELECT * FROM dbo.Experience WHERE candidate_id=(?);";

	/** The Constant SELECT_ALL_EXPERIENCE. */
	private final static String SELECT_ALL_EXPERIENCE = "SELECT e.candidate_id,full_name,birth_day,phone,email,exp_in_year,pro_skill FROM dbo.Candidate c JOIN dbo.Experience e ON e.candidate_id = c.candidate_id;";

	/** The candidate dao. */
	private CandidateDao candidateDao = new CandidateDao();

	/** The certificated dao. */
	private CertificatedDao certificatedDao = new CertificatedDao();

	/**
	 * Insert.
	 *
	 * @param experience
	 *            the experience
	 * @return true, if successful
	 */
	public boolean insert(Experience experience) {
		if (candidateDao.insert(experience)) {
			Integer candidateId = experience.getCandidateId();
			Integer expInYear = experience.getExpInYear();
			String proSkill = experience.getProSkill();
			try {
				conn = DataBaseUtils.getConnection();
				preparedStatement = DataBaseUtils.getPreStatement(conn,
						SELECT_ONE_EXPERIENCE);
				preparedStatement.setString(1, null);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					resultSet.moveToInsertRow();
					resultSet.updateInt("candidate_id", candidateId);
					resultSet.updateInt("exp_in_year", expInYear);
					resultSet.updateString("pro_skill", proSkill);
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
	public List<Experience> getList() {
		List<Experience> experienceList = new ArrayList<>();
		List<Certificated> certificatedList;
		Integer candidateId;

		try {
			conn = DataBaseUtils.getConnection();
			preparedStatement = DataBaseUtils.getPreStatement(conn,
					SELECT_ALL_EXPERIENCE);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				candidateId = resultSet.getInt("candidate_id");
				certificatedList = certificatedDao.getList(candidateId);
				experienceList.add(new Experience(candidateId,
						resultSet.getString("full_name"),
						DateUtils
								.stringToDate(resultSet.getString("birth_day")),
						resultSet.getString("phone"),
						resultSet.getString("email"), certificatedList,
						resultSet.getInt("exp_in_year"),
						resultSet.getString("pro_skill")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return experienceList;
	}

	/**
	 * Update.
	 *
	 * @param experience
	 *            the experience
	 * @return true, if successful
	 */
	public boolean update(Experience experience) {
		if (candidateDao.update(experience)) {
			Integer candidateId = experience.getCandidateId();
			Integer expInYear = experience.getExpInYear();
			String proSkill = experience.getProSkill();
			try {
				conn = DataBaseUtils.getConnection();
				preparedStatement = DataBaseUtils.getPreStatement(conn,
						SELECT_ONE_EXPERIENCE);
				preparedStatement.setInt(1, candidateId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					if (expInYear != null) {
						resultSet.updateInt("exp_in_year", expInYear);
					}
					if (proSkill != null) {
						resultSet.updateString("pro_skill", proSkill);
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
