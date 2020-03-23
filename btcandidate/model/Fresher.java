/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.model;

import java.util.Date;
import java.util.List;

import btcandidate.constant.CandidateType;
import btcandidate.until.DateUtils;
import btcandidate.until.UserInputUtils;

/**
 * The Class Fresher.
 */
public class Fresher extends Candidate {

	/** The graduation date. */
	private Date graduationDate;

	/** The graduation rank. */
	private String graduationRank;

	/** The education. */
	private String education;

	{
		setCandidateType(CandidateType.FRESHER_TYPE);
	}

	/**
	 * Instantiates a new fresher.
	 */
	public Fresher() {
		super();
	}

	/**
	 * Instantiates a new fresher.
	 *
	 * @param candidateId
	 *            the candidate id
	 * @param fullName
	 *            the full name
	 * @param birthDay
	 *            the birth day
	 * @param phone
	 *            the phone
	 * @param email
	 *            the email
	 * @param certificateds
	 *            the certificateds
	 * @param graduationDate
	 *            the graduation date
	 * @param graduationRank
	 *            the graduation rank
	 * @param education
	 *            the education
	 */
	public Fresher(Integer candidateId, String fullName, Date birthDay,
			String phone, String email, List<Certificated> certificateds,
			Date graduationDate, String graduationRank, String education) {
		super(candidateId, fullName, birthDay, phone, email, certificateds);
		this.graduationDate = graduationDate;
		this.graduationRank = graduationRank;
		this.education = education;
	}

	/**
	 * Gets the graduation date.
	 *
	 * @return the graduation date
	 */
	public Date getGraduationDate() {
		return graduationDate;
	}

	/**
	 * Sets the graduation date.
	 *
	 * @param graduationDate
	 *            the new graduation date
	 */
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	/**
	 * Gets the graduation rank.
	 *
	 * @return the graduation rank
	 */
	public String getGraduationRank() {
		return graduationRank;
	}

	/**
	 * Sets the graduation rank.
	 *
	 * @param graduationRank
	 *            the new graduation rank
	 */
	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}

	/**
	 * Gets the education.
	 *
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * Sets the education.
	 *
	 * @param education
	 *            the new education
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * Show me.
	 */
	@Override
	public void showMe() {
		System.out.println("FRESHER:");
		System.out.println(super.toString() + "\t"
				+ DateUtils.dateToString(getGraduationDate()) + "\t"
				+ getGraduationRank() + "\t" + getEducation());
		super.showCertificates();

	}

	/**
	 * Input info.
	 */
	@Override
	public void inputInfo() {
		super.inputInfo();
		System.out.print("Nhap graduation_date: ");
		setGraduationDate(UserInputUtils.inputTypeDate());
		System.out.print("Nhap graduation_rank: ");
		setGraduationRank(UserInputUtils.inputTypeString());
		System.out.print("Nhap education: ");
		setEducation(UserInputUtils.inputTypeString());
	}

	/**
	 * Input update info.
	 */
	@Override
	public void inputUpdateInfo() {
		super.inputUpdateInfo();
		System.out.print("Nhap graduation_date("
				+ DateUtils.dateToString(getGraduationDate()) + "): ");
		setGraduationDate(UserInputUtils.inputTypeDate());
		System.out.print("Nhap graduation_rank(" + getGraduationRank() + "): ");
		setGraduationRank(UserInputUtils.inputTypeString());
		System.out.print("Nhap education(" + getEducation() + "): ");
		setEducation(UserInputUtils.inputTypeString());
	}

}
