package project;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        
        System.out.println("<수강 관리 프로그램>");

        while (true) {
            System.out.print("\n0. 현재 학점 현황 보기\n1. 수강 강의 입력\n2. 수강 강의 목록 보기\n3. 수강 강의 목록 저장\n4. 수강 강의 목록 불러오기\n5. 수강 강의 삭제\n6. 종료\n선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
            	case 0:
            		Course.printCreditInfo();
            		break;
                case 1:
                    courses = Course.inputCourse(scanner, courses);
                    break;
                case 2:
                	Course.displayCourses(courses);
                    break;
                case 3:
                	Course.saveCourses(courses);
                    break;
                case 4:
                    courses = Course.loadCourses();
                    break;
                case 5:
                    courses = Course.removeCourse(scanner, courses);
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
	}
}