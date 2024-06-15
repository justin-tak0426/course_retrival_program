# University Course and Credit retrieval Program for CSEE students
- 21800763 JUNHYUK TAK

# Project description
## 1. Purpose
The purpose of the program is to help computer science major students easily check the complex course requirements and keep track of the courses they have taken. Computer science major students have to fulfill many requirements such as core major courses, major elective requirements, required math courses, required general education courses, etc. It would be convenient if they could see at a glance what courses they have taken and what courses they still need to take, either when registering for classes or checking graduation requirements. The program will categorize courses into different required groups, calculate the total credits and GPA for each group based on the courses taken so far, and store this information in a file for management. Users can load the file anytime to update their information.

## 2. Class hierarchy
The basis for establishing the class hierarchy is the categories of courses required for graduation. For each category, the courses taken are managed, and the credits earned and credits lacking are calculated.
This is UML diagram of classes and interfaces.

![UML](https://github.com/justin-tak0426/course_retrival_program/assets/79962142/7b819552-4835-4146-926a-691387a1ee5b)
![Major_UML](https://github.com/justin-tak0426/course_retrival_program/assets/79962142/4a236a65-18c0-4d25-a601-0d5644ec2f1f)
![NotMajor_UML](https://github.com/justin-tak0426/course_retrival_program/assets/79962142/8d3e1487-c845-496e-9217-17b039dd4991)

## 3. The roles of each classes
### Main class
The Main class serves the role of displaying the program's menu. The functions of the selected menu are implemented in the Course class. It continues to show the menu until the user terminates the program.

<img width="170" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/0c5f1f2f-bbcd-43bb-92bb-20474a670fa5">

In the class, the courses informations are managed by ArrayList<Course>.

### CreditInfo class
The CreditInfo class includes information about the total credits taken, total credits of English courses taken, credits of major and general major courses taken, and credits of major required, major elective required, and major design courses taken. This class manages the credit information when course information is saved or loaded.
#### fields
totalCredit: total credit you took.
majorCredit: major class credit you took.
notMajorCredit: non major class credit you took.
requiredCredit: major required class credit you took.
chooseCredit: major choose class credit you took. (It means elective required course)
designCredit: major design class credit you took.
englishCredit: total English course credit you took.
All fields have setters and getters.


### Course class
The Course class has functions to input and delete course information, display the list of courses taken, show the information of credits earned, save the course and credit information in a text file format, load the saved text file, and calculate the GPA.
#### fileds
target: total credit which CSEE students have to take for graduation.
totalCredit: total credit which the user has taken.
totalEnglish: total credit you took which is English course.
gpa: your GPA.
name: the course name
credit: the course's credit
isEnglish: check if the course is English course.
grade: the grade of the course you got.

#### methods
constructor: get name, credit, grade, and isEnglish value and set.
```java
public Course(String name, int credit, float grade, boolean isEnglish) {
        this.name = name;
        this.credit = credit;
        this.grade = grade;
        this.isEnglish = isEnglish;
        
        if(isEnglish == true) {
        	totalEnglish += credit;
        }
}
```

inputCourses: input the course information you took. you need to input name, isEnglish, credit, grade. If the choose Design class, you have to input design credit additionally.
For each class of the courses you have selected, an instance is created. (Polymorphism)
```java
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
```

removeCourse: you can delete the course which you took. When you enter the course name, the course is deleted in the course list.
```java
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
	}
```
increaseTotalCredit: increase the totalCredit and totalEnglish when you enter the course you took in the course list. If the course is English course(isEnglish==true), add totalEnglish.
```java
public static void increateTotalCredit(int credit, boolean isEnglish) {
		totalCredit += credit;
		if(isEnglish==true) {
			totalEnglish += credit;
		}
	}
```

displayCourses: display the courses you took in the course list. It shows you name, credit, grade, and isEnglish.
```java
public static void displayCourses(ArrayList<Course> courses) {
        System.out.println("\n<수강 강의 목록>");
        for (Course course : courses) {
            System.out.println("강의명: " + course.getName() + ", 학점: " + course.getCredit() + ", 받은 성적: " + course.getGrade() + ", 영어강의: " + course.isEnglish());
        }
    }
```

saveCourses: save the course information to text file(.txt). It include gpa, totalCredit, majorCredit, notMajorCredit, requiredCredit, chooseCredit, designCredit, englishCredit, course name, course credit, course grade, and course isEnglish value. There is exception handler when you fail to save the text file. I use FileWriter and BufferedWriter to save the information.
```java
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
```

loadCourses: load the information about course information using course.txt file. It include gpa, totalCredit, majorCredit, notMajorCredit, requiredCredit, chooseCredit, designCredit, englishCredit, course name, course credit, course grade, and course isEnglish value. There is exception handler when you input the wrong text file name.  I use FileWriter, BufferedWriter, and setters to load the information.
```java
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
```

printCreditInfo: print the credit information. It includes gpa, totalCredit, majorCredit, notMajorCredit, requiredCredit, chooseCredit, designCredit, and englishCredit.
```java
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
```

calculateGPA: It calculates gpa of courses you took by using the courses list (ArrayList<Course> courses). After calculating, print and set the gpa value.
```java
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
```

needCourse: calculate the total credit you need to take for graduation.
```java
public static int needCourse() {
		if(target - totalCredit >= 0) {
			return target - totalCredit;
		}
		else {
			return 0;
		}
	}
```

### Major
The Major class manages the course which is major course. It calculate the major credit informations such as target total credit of major courses, major credit you took, English course target credit, and English course credit you took. 

#### fields
target: target total credit of major courses for graduation.
majorCredit: major credit you took.
englishTarget: English course target credit for graduation.
englishCredit: English course credit you took.

This class has getter and setter for each fields.

#### methods
constructor: It gets parameters including name, credit, grade, and isEnglish, and call super class's constructor to make an instance of course. Also, It increase majorCredit and englishCredit if the course is English course(isEnglish==true).
```java
public Major(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name,credit,grade,isEnglish);
		setMajorCredit(getMajorCredit() + credit);
		if(isEnglish == true) {
			setEnglishCredit(getEnglishCredit() + credit);
		}
		
	}
```

increaseMajorCredit: increase major courses credit you took when the Major class course is added in the course lise.
```java
public static void increaseMajorCredit(int credit) {
		majorCredit += credit;
	}
```

needMajor: calculate and return the remained major course credit you have to take for graduation.
```java
public static int needMajor() {
		if(target - majorCredit >= 0) {
			return target - majorCredit;
		}
		else{
			return 0;
		}
	}
```


### Required
The Required class manages the required major course for graducation.

#### fields
target: The target major required courses for graduation.
requiredCredits: The total credit you took in the required class.

This class has getter and setter for each fields.

#### methods
constructor: It gets parameters including name, credit, grade, and isEnglish, and call super class's constructor to make an instance of course. Also, It increase requiredCredits.
```java
public Required(String name, int credit, float grade, boolean isEnglish){
		super(name,credit,grade,isEnglish);
		
		requiredCredits += credit;
	}
```

needRequired: calculate and return the remained major required course credit you have to take for graduation.
```java
public static int needRequired() {
    	if(target - requiredCredits >= 0) {
    		return target - requiredCredits;
    	}
    	else{
    		return 0;
    	}
    }
```

printRequired: print the required courses list.
```java
public static void printRequired() {
		System.out.println("< 전공필수 해당 강의 >\n1. 공학설계입문\n2. 데이터구조\n3. 컴퓨터구조\n4. 운영체제\n5. 캡스톤디자인1\n6. 캡스톤디자인2\n7. 실전프로젝트1\n8. AI프로젝트입문\n");    
	}
```

### Design
The Design class manages the major design courses.

#### fields
target: The target major design courses for graduation.
designCredit: design credit for each courses.
totalDesignCredit: design credits you took.

This class has getter and setter for each fields.

#### methods
constructor: It gets parameters including name, credit, grade, and isEnglish, and call super class's constructor to make an instance of course. Also, It shows the list of design courses and get information about desgin credit for each courses. And users have to input the design credit for each course and increase totalDesignCredit.
```java
public Design(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name, credit, grade, isEnglish);
		printDesign();
		System.out.print("해당 강의의 설계인정학점을 입력하세요: ");
		this.designCredit = scanner.nextInt();
		totalDesignCredit += designCredit;
	}
```

needDesign: calculate and return the remained major design course credit you have to take for graduation.
```java
public static int needDesign() {
		if(target - totalDesignCredit>=0) {
			return target - totalDesignCredit;
		}
		else {
			return 0;
		}
	}
```

printDesign: print the list of design courses and each courses' desgin credit.
```java
public static void printDesign() {
		System.out.println("< 설계학점 해당 강의 >\n1. 공학설계입문(3)\n2. 웹 서비스 개발(1)\n3. 실전프로젝트1(1)\n4. 디지털시스템설계(1)\n5. 마이크로프로세서응용(1)\n6. AI프로젝트입문(1)\n7. 객체지향 설계패턴(1)\n8. 모바일앱개발(1)\n9. IoT 시스템 설계(1)\n10. 캡스톤디자인1(2)\n11. 캡스톤디자인2(4)\n");    
	}
```

### Choose
The Choose class manages the major elective required courses (choose).

#### fields
target: The target major choose courses for graduation.
chooseCredit: choose credits you took.

This class has getter and setter for each fields.

#### methods
constructor: It gets parameters including name, credit, grade, and isEnglish, and call super class's constructor to make an instance of course. And increase chooseCredit.
```java
public Choose(String name, int credit, float grade, boolean isEnglish) {
		super(name, credit, grade, isEnglish);
		// TODO Auto-generated constructor stub
		chooseCredit += credit;
	}
```

needChoose: calculate and return the remained major choose course credit you have to take for graduation.
```java
public static int needChoose() {
		if(target-chooseCredit >= 0) {
			return target - chooseCredit;
		}
		else {
			return 0;
		}
	}
```

### General
The General class manages the major courses which is not specific course (not required, design, choose).

#### field
totalCredits: Total credit of general major courses' credits you took

This class has getter and setter for totalCredits field.

#### methods
constructor: It gets parameters including name, credit, grade, and isEnglish, and call super class's constructor to make an instance of course. And increase totalCredit.
```java
public General(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name, credit, grade, isEnglish);
	}
```

addCredits: increase the totalCredit of general major class.
```java
public static void addCredits(int credits) {
        totalCredits += credits;
    }
```


### NotMajor 
The NotMajor class manages the course which is non-major course. It calculate total credits of non-major courses.

#### fields
totalCredits:  Total credit of non-major courses' credits you took

This class has getter and setter for totalCredits field.

#### methods
constructor: It gets parameters including name, credit, grade, and isEnglish, and call super class's constructor to make an instance of course. And increase totalCredits of non-major courses.
```java
public General(String name, int credit, float grade, boolean isEnglish) {
		// TODO Auto-generated constructor stub
		super(name, credit, grade, isEnglish);
		addCredits(credit);
	}
```

addCredits: increase the totalCredits of non-major class.
```java
public static void addCredits(int credits) {
        totalCredits += credits;
    }
```

### English, Expert, Faith, Free, ICT, Leadership, Math (which are subclass of NotMajor class)
These class is not major class. So, I didn't make special methods and field. They only calculate the not-major courses credits.

## How to use this program?
### Start program
when you start the program, you can see the menu. 

<img width="165" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/39e24519-6917-4ecd-a379-c0943a306b67">


choose the menu what you want to do. 
0: show current credits information.
1: input the courses I took.
2: show the courses I took and the information of the courses.
3: save the courses list into text file (course.txt).
4: load the courses list from text file (course.txt).
5: remove the course I took in the list.
6. terminate the program.

If you finish each functions, you can see menu again, and select what you need again until terminating the program.

### 0: show current credits information
Initial values are all 0 before you input courses you took.
The credits information has the format like "the credits you took / remained credits you have to take"

<img width="283" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/9ea977c0-c811-4f8c-9bf2-828ccc50352c">

After you input some courses information, the results is like the below image.



### 1: Input the courses I took.
After you choose number 1, you can see the list of major required courses. And you have to input the name, credit, grade of the course, and if it is English course.
<img width="150" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/f581add6-4adb-4f7f-8edf-d5acc1f54d29">
<img width="498" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/b0e03ac0-64e5-4494-88aa-bd64a539e5bd">


Also, you have to enter the type of course (Required, Design, Choose, General, etc.).
<img width="221" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/0e4007cc-f04f-4906-b33e-68521b2c0ecb">


If you choose Design type, you can check the design credit of your course, and input the design credit of the course.

<img width="264" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/9abd9383-f87c-4a39-9164-8b838a0ee3d4">


After choosing the type of course, you can check if your input is correct. If the input is correct, you can add the course in the course list.

<img width="473" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/83acd56a-cafd-42a9-9331-2c27dd561347">


When you input "q", the function is finished, and show your GPA.

<img width="390" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/63d346bf-16ee-4483-9236-469c8cc0077c">


### 2. show the courses I took and the information of the courses.
You can see the courses information you took. It includes name, credit, grade, and if the course is English course.

<img width="436" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/ce0ff075-d48d-42f4-a4db-a313d23d31f9">



### 3. save the courses list into text file (course.txt).
You can save the courses list into text file (course.txt). This file is stored in your java project repository. If you want to change your saving directory, you can change the code in Course Class (saveCourses method).

<img width="204" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/ea4f04e8-b4ab-4ae1-86da-2904b8f13682">


The result text file is stored as the below image.

<img width="444" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/3b483ec7-f031-4223-b1ba-f2e26d30add8">


### 4. load the courses list from text file (course.txt).
You can load the courses when you start the program or during the program.

- before loading the course data

<img width="291" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/e9f79834-226b-448b-aa27-95c0d7125f4d">
<img width="170" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/f71f3e24-e036-4ad7-bef3-35f39bb91b19">

- after loading the course data

<img width="282" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/50d380f9-0fa7-4e4b-a5b8-820f23ce9123">
<img width="445" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/14288e9a-48bd-4fe1-91ff-52e38e8a65b6">


### 5. remove the course I took in the list.
You can remove the course you took in the course list by using the course name.
If you input the wrong name, you can't remove the course.

<img width="238" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/05cf40db-2b6b-4243-8dc6-4cf7ebfed98b">


If your input is correct, the course is deleted in the course list.

<img width="316" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/586830a8-a156-4a80-a473-e26b57ec063a">

<img width="428" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/b48deb5a-6c42-4550-9a3f-f668e715bff7">


### 6. terminate the program.
If you want to finish the program, input number 6 in the console.

<img width="195" alt="image" src="https://github.com/justin-tak0426/course_retrival_program/assets/79962142/8ee16276-f75f-466c-9fd8-096a4142cedc">


