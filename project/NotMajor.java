package project;

import java.io.Serializable;

public class NotMajor  extends Course implements Serializable {
	private static int notMajorCredit = 0;
	private static int englishCredit = 0;
	


	public NotMajor(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name,credit,grade,isEnglish);
		notMajorCredit += credit;
		if(isEnglish == true) {
			setEnglishCredit(getEnglishCredit() + credit);
		}
		
		
	}
	
	public static void setNotMajorCredit(int credit) {
		notMajorCredit = credit;
	}
	
	public static int getNotMajorCredit() {
		return notMajorCredit;
	}

	public static int getEnglishCredit() {
		return englishCredit;
	}

	public static void setEnglishCredit(int englishCredit) {
		NotMajor.englishCredit = englishCredit;
	}

}
