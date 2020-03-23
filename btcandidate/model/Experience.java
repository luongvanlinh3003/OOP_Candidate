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
 * The Class Experience.
 */
public class Experience extends Candidate {

	private Integer expInYear;
	private String proSkill;
	{
		setCandidateType(CandidateType.EXPERIENCE_TYPE);
	}

	/**
	 * Instantiates a new experience.
	 */
	public Experience() {
		super();
	}

	/**
	 * Instantiates a new experience.
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
	 * @param expInYear
	 *            the exp in year
	 * @param proSkill
	 *            the pro skill
	 */
	public Experience(Integer candidateId, String fullName, Date birthDay,
			String phone, String email, List<Certificated> certificateds,
			Integer expInYear, String proSkill) {
		super(candidateId, fullName, birthDay, phone, email, certificateds);
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}

	/**
	 * Gets the exp in year.
	 *
	 * @return the exp in year
	 */
	public Integer getExpInYear() {
		return expInYear;
	}

	/**
	 * Sets the exp in year.
	 *
	 * @param expInYear
	 *            the new exp in year
	 */
	public void setExpInYear(Integer expInYear) {
		this.expInYear = expInYear;
	}

	/**
	 * Gets the pro skill.
	 *
	 * @return the pro skill
	 */
	public String getProSkill() {
		return proSkill;
	}

	/**
	 * Sets the pro skill.
	 *
	 * @param proSkill
	 *            the new pro skill
	 */
	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}

	/**
	 * Show me.
	 */
	@Override
	public void showMe() {
		System.out.println("EXPERIENCE:");
		System.out.println(super.toString() + "\t" + getExpInYear() + "\t"
				+ getProSkill());
		super.showCertificates();
	}

	/**
	 * Input info.
	 */
	@Override
	public void inputInfo() {
		super.inputInfo();
		System.out.print("Nhap exp_in_year: ");
		setExpInYear(UserInputUtils.inputTypeInt());
		System.out.print("Nhap proSkill: ");
		setProSkill(UserInputUtils.inputTypeString());
	}

	/**
	 * Input update info.
	 */
	@Override
	public void inputUpdateInfo() {
		super.inputUpdateInfo();
		System.out.print("Nhap exp_in_year(" + getExpInYear() + "): ");
		setExpInYear(UserInputUtils.inputTypeInt());
		System.out.print("Nhap proSkill(" + getProSkill() + "): ");
		setProSkill(UserInputUtils.inputTypeString());
	}

}
