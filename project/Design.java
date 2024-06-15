package project;

import java.io.Serializable;
import java.util.Scanner;

public class Design  extends Major implements Serializable {
	private static int target = 12;
	private int designCredit = 0;
	private static int totalDesignCredit = 0;
	
	
	Scanner scanner = new Scanner(System.in);
	
	public Design(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name, credit, grade, isEnglish);
		printDesign();
		System.out.print("해당 강의의 설계인정학점을 입력하세요: ");
		this.designCredit = scanner.nextInt();
		increaseTotalDesignCredit(designCredit);
		
	}
    
    public static void increaseTotalDesignCredit(int designCredit) {
    	totalDesignCredit += designCredit;
    }
    
    public static int getTotalDesignCredit() {
    	return totalDesignCredit;
    }
    
    public static void setTotalDesignCredit(int totalDesignCredit) {
    	Design.totalDesignCredit = totalDesignCredit;
    }


	public static int needDesign() {
		if(target - totalDesignCredit>=0) {
			return target - totalDesignCredit;
		}
		else {
			return 0;
		}
	}
	
	public static void printDesign() {
		System.out.println("< 설계학점 해당 강의 >\n1. 공학설계입문(3)\n2. 웹 서비스 개발(1)\n3. 실전프로젝트1(1)\n4. 디지털시스템설계(1)\n5. 마이크로프로세서응용(1)\n6. AI프로젝트입문(1)\n7. 객체지향 설계패턴(1)\n8. 모바일앱개발(1)\n9. IoT 시스템 설계(1)\n10. 캡스톤디자인1(2)\n11. 캡스톤디자인2(4)\n");    
	}

}
