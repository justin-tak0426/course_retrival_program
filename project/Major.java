package project;

import java.io.Serializable;

public class Major extends Course implements Serializable{
	static private int target = 60;
	static private int majorCredit = 0;
	static private int englishTarget = 21;
	static private int englishCredit = 0;
	
	
	public Major(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name,credit,grade,isEnglish);
		setMajorCredit(getMajorCredit() + credit);
		if(isEnglish == true) {
			setEnglishCredit(getEnglishCredit() + credit);
		}
		
	}

	public static void increaseMajorCredit(int credit) {
		majorCredit += credit;
	}

	public static int getMajorCredit() {
		return majorCredit;
	}
	
	public static void setMajorCredit(int credit) {
		majorCredit = credit;
	}
	
	public static int needMajor() {
		if(target - majorCredit >= 0) {
			return target - majorCredit;
		}
		else{
			return 0;
		}
	}

	public static int getEnglishCredit() {
		return englishCredit;
	}

	public static void setEnglishCredit(int englishCredit) {
		Major.englishCredit = englishCredit;
	}

	public static int getEnglishTarget() {
		return englishTarget;
	}

	
	
}
