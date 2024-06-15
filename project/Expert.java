package project;

import java.io.Serializable;

public class Expert extends NotMajor implements Serializable{
	private static int totalCredits = 0;

	public Expert(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name, credit, grade, isEnglish);
	}
	
    
    public static void addCredits(int credits) {
        totalCredits += credits;
    }
    
    public static int getTotalCredits() {
        return totalCredits;
    }

}
