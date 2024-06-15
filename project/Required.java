package project;

import java.io.Serializable;

public class Required extends Major implements Serializable {
	private static int target = 23;
	private static int requiredCredits = 0;

	
	
	public Required(String name, int credit, float grade, boolean isEnglish){
		super(name,credit,grade,isEnglish);
		
		requiredCredits += credit;
	}
	
	public static void setRequired(int credit) {
		requiredCredits = credit;
    }
    
    public static int getRequiredCredits() {
        return requiredCredits;
    }
    
    public static int needRequired() {
    	if(target - requiredCredits >= 0) {
    		return target - requiredCredits;
    	}
    	else{
    		return 0;
    	}
    }
    
    public static void printRequired() {
		System.out.println("< 전공필수 해당 강의 >\n1. 공학설계입문\n2. 데이터구조\n3. 컴퓨터구조\n4. 운영체제\n5. 캡스톤디자인1\n6. 캡스톤디자인2\n7. 실전프로젝트1\n8. AI프로젝트입문\n");    
	}
}
