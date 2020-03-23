/**
  * 	@FRESHER 61
  * 	@author UynNVD1
  *	
  **/
package btcandidate.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import btcandidate.dao.ExperienceDao;
import btcandidate.dao.FresherDao;
import btcandidate.dao.InternDao;
import btcandidate.model.Candidate;
import btcandidate.model.Experience;
import btcandidate.model.Fresher;
import btcandidate.model.Intern;
import btcandidate.until.DateUtils;
import btcandidate.until.UserInputUtils;

/**
 * The Class MenuControl.
 */
public class MenuControl {

	/** The candidate list. */
	private List<Candidate> candidateList;

	/** The experience dao. */
	private ExperienceDao experienceDao;

	/** The fresher dao. */
	private FresherDao fresherDao;

	/** The intern dao. */
	private InternDao internDao;

	/**
	 * Instantiates a new menu control.
	 */
	public MenuControl() {
		super();
		experienceDao = new ExperienceDao();
		fresherDao = new FresherDao();
		internDao = new InternDao();
		getFromDB();
	}

	/**
	 * Show menu.
	 *
	 * @return the int
	 */
	private int showMenu() {
		System.out.println("1. Nhap thong tin ung vien");
		System.out.println("2. Xem thong tin ung vien");
		System.out.println("3. Xem fullName ung vien");
		System.out.println("4. So luong ung vien vua nhap");
		System.out.println("5. Sap xep");
		System.out.println("6. Xoa trung lap");
		System.out.println("7. Update info");
		System.out.println("8. Exit");
		System.out.print("Chon: ");
		return UserInputUtils.inputTypeInt();
	}

	/**
	 * Run.
	 */
	public void run() {
		do {
			switch (showMenu()) {
				case 1 :
					inputData();
					getFromDB();
					break;
				case 2 :
					showAll();
					break;
				case 3 :
					showFullName();
					break;
				case 4 :
					showCandidateCount();
					break;
				case 5 :
					sortList();
					break;
				case 6 :
					deleteDuplicateId();
					break;
				case 7 :
					updateInfoCandidate();
					getFromDB();
					break;
				case 8 :
					System.out.println("--DONE--");
					System.exit(0);
					break;
				default :
					break;
			}
		} while (true);
	}

	/**
	 * Update info candidate.
	 */
	private void updateInfoCandidate() {
		System.out.print("Candidate ID muon update: ");
		int candidateId = UserInputUtils.inputTypeInt();
		Candidate candidate = candidateList.stream()
				.filter(e -> e.getCandidateId() == candidateId).findAny()
				.orElse(null);
		if (candidate == null) {
			System.out.println("Candidate ID khong ton tai !");
			return;
		}
		candidate.inputUpdateInfo();
		if (candidate instanceof Experience) {
			experienceDao.update((Experience) candidate);
		} else if (candidate instanceof Fresher) {
			fresherDao.update((Fresher) candidate);
		} else if (candidate instanceof Intern) {
			internDao.update((Intern) candidate);
		}
	}

	/**
	 * Gets the from DB.
	 *
	 * @return the from DB
	 */
	private void getFromDB() {
		candidateList = new ArrayList<>();
		candidateList.addAll(experienceDao.getList());
		candidateList.addAll(fresherDao.getList());
		candidateList.addAll(internDao.getList());
	}

	/**
	 * Sort list.
	 */
	private void sortList() {
		candidateList.stream().sorted((o1, o2) -> {
			int o1Type = o1.getCandidateType();
			int o2Type = o2.getCandidateType();
			if (Integer.compare(o1Type, o2Type) == 0) {
				int o1Year = DateUtils.getYear(o1.getBirthDay());
				int o2Year = DateUtils.getYear(o2.getBirthDay());
				return -Integer.compare(o1Year, o2Year);
			}
			return Integer.compare(o1Type, o2Type);
		}).forEach(Candidate::showMe);

	}

	/**
	 * Show candidate count.
	 */
	private void showCandidateCount() {
		System.out.println("So luong: " + Candidate.canidateCount);

	}

	/**
	 * Input data.
	 */
	private void inputData() {
		System.out.println("Chon loai ung vien:");
		System.out.println("0. EXPERIENCE");
		System.out.println("1. FRESHER");
		System.out.println("2. INTERN");
		switch (UserInputUtils.inputTypeInt()) {
			case 0 :
				Experience experience = new Experience();
				experience.inputInfo();
				experienceDao.insert(experience);
				Candidate.canidateCount++;
				break;
			case 1 :
				Fresher fresher = new Fresher();
				fresher.inputInfo();
				fresherDao.insert(fresher);
				Candidate.canidateCount++;
				break;
			case 2 :
				Intern intern = new Intern();
				intern.inputInfo();
				internDao.insert(intern);
				Candidate.canidateCount++;
				break;
			default :
				break;
		}

	}

	/**
	 * Show all.
	 */
	private void showAll() {
		candidateList.forEach(Candidate::showMe);
	}

	/**
	 * Show full name.
	 */
	private void showFullName() {
		StringBuffer stringBuffer = new StringBuffer();
		candidateList.forEach(e -> stringBuffer.append(e.getFullName() + ", "));
		System.out.println(stringBuffer);
	}

	/**
	 * Delete duplicate id.
	 */
	private void deleteDuplicateId() {
		/*
		 * Set<Candidate> candidateSet = new HashSet<>();
		 * candidateList.forEach(e -> candidateSet.add(e)); candidateList =
		 * candidateSet.stream().collect(Collectors.toList());
		 */
		candidateList = candidateList.stream().distinct()
				.collect(Collectors.toList());
	}
}
