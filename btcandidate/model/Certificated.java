/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.model;

import java.util.Date;

import btcandidate.until.DateUtils;
import btcandidate.until.UserInputUtils;

/**
 * The Class Certificated.
 */
public class Certificated {

	/** The certificated id. */
	private Integer certificatedId;

	/** The certificated name. */
	private String certificatedName;

	/** The certificated rank. */
	private String certificatedRank;

	/** The certificated date. */
	private Date certificatedDate;

	/**
	 * Instantiates a new certificated.
	 */
	public Certificated() {
		super();
	}

	/**
	 * Instantiates a new certificated.
	 *
	 * @param certificatedId
	 *            the certificated id
	 * @param certificatedName
	 *            the certificated name
	 * @param certificatedRank
	 *            the certificated rank
	 * @param certificatedDate
	 *            the certificated date
	 */
	public Certificated(Integer certificatedId, String certificatedName,
			String certificatedRank, Date certificatedDate) {
		super();
		this.certificatedId = certificatedId;
		this.certificatedName = certificatedName;
		this.certificatedRank = certificatedRank;
		this.certificatedDate = certificatedDate;
	}

	/**
	 * Gets the certificated id.
	 *
	 * @return the certificated id
	 */
	public Integer getCertificatedId() {
		return certificatedId;
	}

	/**
	 * Sets the certificated id.
	 *
	 * @param certificatedId
	 *            the new certificated id
	 */
	public void setCertificatedId(Integer certificatedId) {
		this.certificatedId = certificatedId;
	}

	/**
	 * Gets the certificate name.
	 *
	 * @return the certificate name
	 */
	public String getCertificateName() {
		return certificatedName;
	}

	/**
	 * Sets the certificate name.
	 *
	 * @param certificateName
	 *            the new certificate name
	 */
	public void setCertificateName(String certificateName) {
		this.certificatedName = certificateName;
	}

	/**
	 * Gets the certificate rank.
	 *
	 * @return the certificate rank
	 */
	public String getCertificateRank() {
		return certificatedRank;
	}

	/**
	 * Sets the certificate rank.
	 *
	 * @param certificateRank
	 *            the new certificate rank
	 */
	public void setCertificateRank(String certificateRank) {
		this.certificatedRank = certificateRank;
	}

	/**
	 * Gets the certificate date.
	 *
	 * @return the certificate date
	 */
	public Date getCertificateDate() {
		return certificatedDate;
	}

	/**
	 * Sets the certificate date.
	 *
	 * @param certificateDate
	 *            the new certificate date
	 */
	public void setCertificateDate(Date certificateDate) {
		this.certificatedDate = certificateDate;
	}

	/**
	 * Show me.
	 */
	public void showMe() {
		System.out.println(getCertificateName() + "\t" + getCertificateRank()
				+ "\t" + DateUtils.dateToString(getCertificateDate()));

	}

	/**
	 * Input info.
	 */
	public void inputInfo() {
		System.out.print("Nhap certificated_name: ");
		setCertificateName(UserInputUtils.inputTypeString());
		System.out.print("Nhap certificated_rank: ");
		setCertificateRank(UserInputUtils.inputTypeString());
		System.out.print("Nhap certificated_date: ");
		setCertificateDate(UserInputUtils.inputTypeDate());
	}

}
