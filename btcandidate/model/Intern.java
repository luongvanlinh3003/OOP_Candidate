/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.model;

import java.util.Date;
import java.util.List;

import btcandidate.constant.CandidateType;
import btcandidate.until.UserInputUtils;

/**
 * The Class Intern.
 */
public class Intern extends Candidate {

	/** The majors. */
	private String majors;

	/** The semester. */
	private Integer semester;

	/** The university name. */
	private String universityName;

	{
		setCandidateType(CandidateType.INTERN_TYPE);
	}

	/**
	 * Instantiates a new intern.
	 */
	public Intern() {
		super();
	}

	/**
	 * Instantiates a new intern.
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
	 * @param majors
	 *            the majors
	 * @param semester
	 *            the semester
	 * @param universityName
	 *            the university name
	 */
	public Intern(Integer candidateId, String fullName, Date birthDay,
			String phone, String email, List<Certificated> certificateds,
			String majors, int semester, String universityName) {
		super(candidateId, fullName, birthDay, phone, email, certificateds);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
	}

	/**
	 * Gets the majors.
	 *
	 * @return the majors
	 */
	public String getMajors() {
		return majors;
	}

	/**
	 * Sets the majors.
	 *
	 * @param majors
	 *            the new majors
	 */
	public void setMajors(String majors) {
		this.majors = majors;
	}

	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public Integer getSemester() {
		return semester;
	}

	/**
	 * Sets the semester.
	 *
	 * @param semester
	 *            the new semester
	 */
	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	/**
	 * Gets the university name.
	 *
	 * @return the university name
	 */
	public String getUniversityName() {
		return universityName;
	}

	/**
	 * Sets the university name.
	 *
	 * @param universityName
	 *            the new university name
	 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	/**
	 * Show me.
	 */
	@Override
	public void showMe() {
		System.out.println("INTERN:");
		System.out.println(super.toString() + "\t" + getMajors() + "\t"
				+ getSemester() + "\t" + getUniversityName());
		super.showCertificates();
	}

	/**
	 * Input info.
	 */
	@Override
	public void inputInfo() {
		super.inputInfo();
		System.out.print("Nhap majors: ");
		setMajors(UserInputUtils.inputTypeString());
		System.out.print("Nhap semester: ");
		setSemester(UserInputUtils.inputTypeInt());
		System.out.print("Nhap university_name: ");
		setUniversityName(UserInputUtils.inputTypeString());
	}

}
