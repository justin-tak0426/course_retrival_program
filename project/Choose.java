package project;

public class Choose extends Major {
	private static int target = 6;
	private static int chooseCredit = 0;
	

	public Choose(String name, int credit, float grade, boolean isEnglish) {
		super(name, credit, grade, isEnglish);
		// TODO Auto-generated constructor stub
		chooseCredit += credit;
	}
	
	public static int getChooseCredit() {
    	return chooseCredit;
    }
	
	public static void setChooseCredit(int credit) {
    	chooseCredit = credit;
    }
	
	public static int needChoose() {
		if(target-chooseCredit >= 0) {
			return target - chooseCredit;
		}
		else {
			return 0;
		}
	}

}
