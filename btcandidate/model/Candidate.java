/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import btcandidate.until.DateUtils;
import btcandidate.until.UserInputUtils;

/**
 * The Class Candidate.
 */
public abstract class Candidate {

	/** The candidate id. */
	private Integer candidateId;

	/** The full name. */
	private String fullName;

	/** The birth day. */
	private Date birthDay;

	/** The phone. */
	private String phone;

	/** The email. */
	private String email;

	/** The candidate type. */
	private Integer candidateType;

	/** The certificateds. */
	private List<Certificated> certificateds;

	/** The canidate count. */
	public static int canidateCount;

	/**
	 * Instantiates a new candidate.
	 */
	public Candidate() {
		super();
	}

	/**
	 * Instantiates a new candidate.
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
	 */
	Candidate(Integer candidateId, String fullName, Date birthDay, String phone,
			String email, List<Certificated> certificateds) {
		super();
		this.candidateId = candidateId;
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.phone = phone;
		this.email = email;
		this.certificateds = certificateds;
	}

	/**
	 * Gets the candidate id.
	 *
	 * @return the candidate id
	 */
	public Integer getCandidateId() {
		return candidateId;
	}

	/**
	 * Sets the candidate id.
	 *
	 * @param candidateId
	 *            the new candidate id
	 */
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName
	 *            the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the birth day.
	 *
	 * @return the birth day
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * Sets the birth day.
	 *
	 * @param birthDay
	 *            the new birth day
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the candidate type.
	 *
	 * @return the candidate type
	 */
	public Integer getCandidateType() {
		return candidateType;
	}

	/**
	 * Sets the candidate type.
	 *
	 * @param candidateType
	 *            the new candidate type
	 */
	public void setCandidateType(Integer candidateType) {
		this.candidateType = candidateType;
	}

	/**
	 * Gets the certificateds.
	 *
	 * @return the certificateds
	 */
	public List<Certificated> getCertificateds() {
		return certificateds;
	}

	/**
	 * Sets the certificateds.
	 *
	 * @param certificateds
	 *            the new certificateds
	 */
	public void setCertificateds(List<Certificated> certificateds) {
		this.certificateds = certificateds;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return getCandidateId() + "\t" + getFullName() + "\t"
				+ DateUtils.dateToString(getBirthDay()) + "\t" + getPhone()
				+ "\t" + getEmail() + "\t" + getCandidateType();
	}

	/**
	 * Show me.
	 */
	public abstract void showMe();

	/**
	 * Input info.
	 */
	public void inputInfo() {
		int number;
		List<Certificated> list;
		Certificated certificated;
		System.out.print("Nhap full_name: ");
		setFullName(UserInputUtils.inputTypeString());
		System.out.print("Nhap birth_day: ");
		setBirthDay(UserInputUtils.inputTypeBirthDay());
		System.out.print("Nhap phone: ");
		setPhone(UserInputUtils.inputTypeString());
		System.out.print("Nhap email: ");
		setEmail(UserInputUtils.inputTypeEmail());
		System.out.print("Co bao nhieu bang cap: ");
		number = UserInputUtils.inputTypeInt();
		if (number > 0) {
			list = new ArrayList<>();
			for (int i = 1; i <= number; i++) {
				System.out.println("Bang cap thu " + i);
				certificated = new Certificated();
				certificated.inputInfo();
				list.add(certificated);
			}
			setCertificateds(list);
		}

	}

	/**
	 * Show certificates.
	 */
	void showCertificates() {
		List<Certificated> certificatedsList = getCertificateds();
		if (certificatedsList != null) {
			System.out.println("Certificated:");
			for (Certificated certificated : getCertificateds()) {
				certificated.showMe();
			}
		}
		System.out
				.println("---------------------------------------------------");
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return candidateId.hashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * Input update info.
	 */
	public void inputUpdateInfo() {
		System.out.print("Nhap full_name(" + getFullName() + "): ");
		setFullName(UserInputUtils.inputTypeString());
		System.out.print("Nhap birth_day("
				+ DateUtils.dateToString(getBirthDay()) + "): ");
		setBirthDay(UserInputUtils.inputTypeBirthDay());
		System.out.print("Nhap phone(" + getPhone() + "): ");
		setPhone(UserInputUtils.inputTypeString());
		System.out.print("Nhap email(" + getEmail() + "): ");
		setEmail(UserInputUtils.inputTypeEmail());
	}

}
