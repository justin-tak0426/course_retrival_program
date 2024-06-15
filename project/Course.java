package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



public class Course implements Serializable{
	public static int target = 130;
	public static int totalCredit = 0;
	public static int totalEnglish = 0;
	public static float gpa;
	
	private String name;
	private int credit;
	private boolean isEnglish;
	private float grade;
	
	public Course(String name, int credit, float grade, boolean isEnglish) {
        this.name = name;
        this.credit = credit;
        this.grade = grade;
        this.isEnglish = isEnglish;
    }

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}

	
	public static ArrayList<Course> inputCourse(Scanner scanner, ArrayList<Course> courses) {
		Required.printRequired();
		
		
		while(true) {
	        System.out.print("\n수강한 강의 이름을 입력하세요 (입력을 멈추시려면 q를 누르세요 ): ");
	        String name = scanner.nextLine();
	        if(name.equals("q")) {
	        	break;
	        }
	        
	        boolean isEnglish = false;
	        System.out.print("영어 수업입니까? (맞으면 y, 아니면 나머지를 입력하세요): ");
	        String cond = scanner.nextLine();
	        if(cond.equals("y")) {
	        	isEnglish = true;
	        }
	        		
	
	        System.out.print("해당 강의는 몇학점짜리 강의입니까?: ");
	        int credit = scanner.nextInt();
	        
	        System.out.print("해당 강의에서 얻은 학점을 입력하세요: ");
	        float grade = scanner.nextFloat();
	
	        System.out.print("\n해당 강의가 속한 클래스를 선택하세요.\n" +
	                "1. 전공 필수\n2. 전공 설계\n3. 전공 선택필수\n4. 전공 일반\n5. 교양 신앙\n" +
	                "6. 교양 리더십\n7. 교양 영어\n8. 교양 수학\n9. 교양 ICT\n" +
	                "10. 교양 전문\n11. 교양 자유\n선택: ");
	        int choice = scanner.nextInt();
	        scanner.nextLine(); // consume newline
	
	        System.out.print("입력하신 정보는 다음과 같습니다\n강의: " + name + ", 강의 학점: " + credit + ", 받은 학점: " + grade + ", 영어강의: " + isEnglish + "\n수강강의 목록에 추가하시겠습니까? (추가는 y): ");
	        String confirmation = scanner.nextLine();
	        if (confirmation.equals("y")) {
	            Course course;
	            switch (choice) {
	                case 1: course = new Required(name, credit, grade, isEnglish); break;
	                case 2: course = new Design(name, credit, grade, isEnglish); break;
	                case 3: course = new Choose(name, credit, grade, isEnglish); break;
	                case 4: course = new General(name, credit, grade, isEnglish); break;
	                case 5: course = new Faith(name, credit, grade, isEnglish); break;
	                case 6: course = new Leadership(name, credit, grade, isEnglish); break;
	                case 7: course = new English(name, credit, grade, isEnglish); break;
	                case 8: course = new Math(name, credit, grade, isEnglish); break;
	                case 9: course = new ICT(name, credit, grade, isEnglish); break;
	                case 10: course = new Expert(name, credit, grade, isEnglish); break;
	                case 11: course = new Free(name, credit, grade, isEnglish); break;
	                default:
	                    System.out.println("잘못된 선택입니다.");
	                    return courses;
	            }
	            
	            courses.add(course);
	            System.out.println("수강 강의 목록에 추가되었습니다.");
	            increateTotalCredit(credit, isEnglish);
	        } else {
	            System.out.println("수강 강의 목록에 추가되지 않았습니다.");
	        }
		}
		
		System.out.println("강의 입력을 종료합니다.");
		gpa = calculateGPA(courses);

		return courses;
    }
	
	public static ArrayList<Course> removeCourse(Scanner scanner, ArrayList<Course> courses) {
        System.out.print("삭제할 강의 이름을 입력하세요: ");
        String name = scanner.nextLine();

        boolean removed = false;
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getName().equals(name)) {
                iterator.remove();
                removed = true;
                System.out.println("강의가 삭제되었습니다.");
                break;
            }
        }

        if (!removed) {
            System.out.println("해당 강의를 찾을 수 없습니다.");
        }
        return courses;
    }
	
	public static void increateTotalCredit(int credit, boolean isEnglish) {
		totalCredit += credit;
		if(isEnglish==true) {
			totalEnglish += credit;
		}
	}
	

	public static void displayCourses(ArrayList<Course> courses) {
        System.out.println("\n<수강 강의 목록>");
        for (Course course : courses) {
            System.out.println("강의명: " + course.getName() + ", 학점: " + course.getCredit() + ", 받은 성적: " + course.getGrade() + ", 영어강의: " + course.isEnglish());
        }
    }

	public static void saveCourses(ArrayList<Course> courses) {
        try {
            FileWriter fileWriter = new FileWriter("courses.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            // GPA 정보 저장
            float gpa = calculateGPA(courses);
            bufferedWriter.write("gpa:" + gpa);
            bufferedWriter.newLine();
            
            // 학점 정보 저장
            CreditInfo creditInfo = new CreditInfo();
            creditInfo.setTotalCredit(getTotalCredit());
            creditInfo.setMajorCredit(Major.getMajorCredit());
            creditInfo.setNotMajorCredit(NotMajor.getNotMajorCredit());
            creditInfo.setRequiredCredit(Required.getRequiredCredits());
            creditInfo.setChooseCredit(Choose.getChooseCredit());
            creditInfo.setDesignCredit(Design.getTotalDesignCredit());
            creditInfo.setEnglishCredit(Major.getEnglishCredit());

            bufferedWriter.write("totalCredit:" + creditInfo.getTotalCredit());
            bufferedWriter.newLine();
            bufferedWriter.write("majorCredit:" + creditInfo.getMajorCredit());
            bufferedWriter.newLine();
            bufferedWriter.write("notMajorCredit:" + creditInfo.getNotMajorCredit());
            bufferedWriter.newLine();
            bufferedWriter.write("requiredCredit:" + creditInfo.getRequiredCredit());
            bufferedWriter.newLine();
            bufferedWriter.write("chooseCredit:" + creditInfo.getChooseCredit());
            bufferedWriter.newLine();
            bufferedWriter.write("designCredit:" + creditInfo.getDesignCredit());
            bufferedWriter.newLine();
            bufferedWriter.write("englishCredit:" + creditInfo.getEnglishCredit());
            bufferedWriter.newLine();
            
            for (Course course : courses) {
                String line = String.format("강의명: %s, 학점: %d, 성적: %.1f, 영어강의: %s",
                        course.getName(), course.getCredit(), course.getGrade(), course.isEnglish() ? "Yes" : "No");
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
            System.out.println("수강 강의 목록이 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("수강 강의 목록 저장에 실패했습니다.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Course> loadCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("courses.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // GPA 정보 불러오기
            String gpaLine = bufferedReader.readLine();
            if (gpaLine != null) {
                float gpa = Float.parseFloat(gpaLine.split(":")[1]);
                Course.gpa = gpa;
            }
            
            // 학점 정보 불러오기
            CreditInfo creditInfo = new CreditInfo();
            creditInfo.setTotalCredit(Integer.parseInt(bufferedReader.readLine().split(":")[1]));
            creditInfo.setMajorCredit(Integer.parseInt(bufferedReader.readLine().split(":")[1]));
            creditInfo.setNotMajorCredit(Integer.parseInt(bufferedReader.readLine().split(":")[1]));
            creditInfo.setRequiredCredit(Integer.parseInt(bufferedReader.readLine().split(":")[1]));
            creditInfo.setChooseCredit(Integer.parseInt(bufferedReader.readLine().split(":")[1]));
            creditInfo.setDesignCredit(Integer.parseInt(bufferedReader.readLine().split(":")[1]));
            creditInfo.setEnglishCredit(Integer.parseInt(bufferedReader.readLine().split(":")[1]));
            
            Course.setTotalCredit(creditInfo.getTotalCredit());
            Required.setRequired(creditInfo.getRequiredCredit());
            Major.setEnglishCredit(creditInfo.getEnglishCredit());
            Major.setMajorCredit(creditInfo.getMajorCredit());
            Design.setTotalDesignCredit(creditInfo.getDesignCredit());
            NotMajor.setNotMajorCredit(creditInfo.getNotMajorCredit());
            Choose.setChooseCredit(creditInfo.getChooseCredit());
            
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0].substring(5); // "강의명: " 제거
                int credit = Integer.parseInt(parts[1].substring(4)); // "학점: " 제거
                float grade = Float.parseFloat(parts[2].substring(4)); // "성적: " 제거
                boolean isEnglish = parts[3].substring(6).equals("Yes"); // "영어강의: " 제거

                Course course = new Course(name, credit, grade, isEnglish);
                courses.add(course);
            }

            bufferedReader.close();
            fileReader.close();
            System.out.println("수강 강의 목록을 불러왔습니다.");
        } catch (IOException e) {
            System.out.println("수강 강의 목록 불러오기에 실패했습니다.");
            e.printStackTrace();
        }
        return courses;
    }
	
	public static void printCreditInfo() {
		System.out.println("\n(수강한 학점 / 부족한 학점) 으로 출력이 됩니다.");
		System.out.println("현재까지 들은 학점들의 GPA: " + String.format("%.2f", gpa));
		System.out.println("수강한 총 학점: " + getTotalCredit() + " / " + needCourse());
		System.out.println("수강한 총 전공: " + Major.getMajorCredit() + " / " + Major.needMajor());
		System.out.println("수강한 총 교양: " + NotMajor.getNotMajorCredit());
		System.out.println("전공 필수 : " + Required.getRequiredCredits() + " / " + Required.needRequired());
		System.out.println("전공 선택필수 : " + Choose.getChooseCredit() + " / " + Choose.needChoose());
		System.out.println("전공 설계 : " + Design.getTotalDesignCredit() + " / " + Design.needDesign());
		System.out.println("전공 영어 " + Major.getEnglishCredit() +  " / "  + Major.getEnglishTarget());
	}
	
	public static float calculateGPA(ArrayList<Course> courses) {
	    float totalGradePoints = 0;
	    int totalCredits = 0;
	    
	    for (Course course : courses) {
	        float gradePoint = course.getGradePoint();
	        int credit = course.getCredit();
	        
	        totalGradePoints += gradePoint * credit;
	        totalCredits += credit;
	    }
	    
	    if (totalCredits == 0) {
	        System.out.println("현재까지 들은 학점이 없습니다.");
	        return 0;
	    }
	    
	    float gpa = totalGradePoints / totalCredits;
	    System.out.println("현재까지 들은 학점들의 GPA: " + String.format("%.2f", gpa));
	    return gpa;
	}
    
    public float getGradePoint() {
        switch ((int) grade) {
            case 4:
                return 4.5f;
            case 3:
                return 4.0f;
            case 2:
                return 3.0f;
            case 1:
                return 2.0f;
            default:
                return 0.0f;
        }
    }


	public boolean isEnglish() {
		return isEnglish;
	}


	public void setEnglish(boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
	
	public static int getTotalEnglish() {
		return totalEnglish;
	}


	public float getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public static int needCourse() {
		if(target - totalCredit >= 0) {
			return target - totalCredit;
		}
		else {
			return 0;
		}
	}
	
	public static int getTotalCredit() {
		return totalCredit;
	}
	
	public static void setTotalCredit(int credit) {
		totalCredit = credit;
	}
	
}
