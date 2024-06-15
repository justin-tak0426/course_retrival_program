package project;

import java.io.Serializable;

public class General extends Major implements Serializable {
	private static int totalCredits = 0;

	public General(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name, credit, grade, isEnglish);
		addCredits(credit);
	}
	
    
    public static void addCredits(int credits) {
        totalCredits += credits;
    }
    
    public static int getTotalCredits() {
        return totalCredits;
    }

}
