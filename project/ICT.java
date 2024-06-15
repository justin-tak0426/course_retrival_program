package project;

import java.io.Serializable;

public class ICT extends NotMajor implements Serializable {

	public ICT(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name, credit, grade, isEnglish);
	}
	
	private static int totalCredits = 0;
    
    public static void addCredits(int credits) {
        totalCredits += credits;
    }
    
    public static int getTotalCredits() {
        return totalCredits;
    }

}
