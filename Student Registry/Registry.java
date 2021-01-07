import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;
import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.NoSuchElementException;

public class Registry 
{
   private TreeMap<String, Student>      students = new TreeMap<String, Student>();
   private TreeMap<String, ActiveCourse> courses  = new TreeMap<String, ActiveCourse>();
   
   public Registry() throws FileNotFoundException, NoSuchElementException
   {
	// Add some students
	   // in A2 we will read from a file
		
		Scanner scanner = new Scanner(new File ("students.txt"));
		
		String name = null;
		String id = null;

		while (scanner.hasNextLine()) {
			name = scanner.next();
			id = scanner.next();
			Student s = new Student(name, id);
			students.put(id, s);
		}
		scanner.close();
		
		 ArrayList<Student> list = new ArrayList<Student>();
		 // CPS209
     String courseName = "Computer Science II";
     String courseCode = "CPS209";
     String descr = "Learn how to write complex programs!";
     String format = "3Lec 2Lab";
		 ActiveCourse ac1 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
     courses.put(ac1.getCode(), ac1);
     // CPS511
     courseName = "Computer Graphics";
     courseCode = "CPS511";
     descr = "Learn how to write cool graphics programs";
     format = "3Lec";
		 ActiveCourse ac2 = new ActiveCourse(courseName,courseCode,descr,format,"F2020",list);
     courses.put(ac2.getCode(), ac2);
     // CPS643
     courseName = "Virtual Reality";
     courseCode = "CPS643";
     descr = "Learn how to write extremely cool virtual reality programs";
     format = "3Lec 2Lab";
		 ActiveCourse ac3 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
     courses.put(ac3.getCode(), ac3);
     // CPS706
     courseName = "Computer Networks";
     courseCode = "CPS706";
     descr = "Learn about Computer Networking";
     format = "3Lec 1Lab";
		 ActiveCourse ac4 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
     courses.put(ac4.getCode(), ac4);
     // CPS616
     courseName = "Algorithms";
     courseCode = "CPS616";
     descr = "Learn about Algorithms";
     format = "3Lec 1Lab";
		 ActiveCourse ac5 = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
     courses.put(ac5.getCode(), ac5);
   }
   
   // Add new student to the registry (students arraylist above) 
   /**
		* @param name Name of student
		* @param id Id of student
		* @return false if new Student object equals Student object already in registry
		* and prints error message
		* @return true if new Student object isn't already in registry and adds the student
    */
	public boolean addNewStudent(String name, String id)
   {
	   // Create a new student object
	   // check to ensure student is not already in registry
	   // if not, add them and return true, otherwise return false
	   // make use of equals method in class Student
		 Student newStudent = new Student(name, id);
		
		 if (findStudent(id) != null) {
			 System.out.println("Student " + newStudent.getName() + " is already registered");
			 return false;
		 }
		 students.put(id, newStudent);
		 return true;
	 }
   
	 // Remove student from registry 
	 /**
	  * @param studentId Student ID for identification
		* @return true if student is found in registry and removes the student
		* @return false if student not found
	  */
   public boolean removeStudent(String studentId)
   {
	   // Find student in students arraylist
	   // If found, remove this student and return true
		 if (findStudent(studentId) != null) {
			 students.remove(studentId);
			 return true;
		 }
		return false;
   }
   
   // Print all registered students
   public void printAllStudents()
   {
		 Set<String> keySet = students.keySet();
		 for (String key : keySet) {
			 System.out.println("ID: " + key + " Name: " + students.get(key).getName());
		 }
   }
   
   // Given a studentId and a course code, add student to the active course
	 /**
	  * @param studentId ID of student
		* @param courseCode The course code of the course
		* Exits method if student not found and/or activecourse not found
		* Then checks if student has already taken course and if student is currently enrolled
		* If conditions are passed, add student to activecourse and add creditcourse to student
	  */
   public void addCourse(String studentId, String courseCode)
   {
	   // Find student object in registry (i.e. students arraylist)
	   // Check if student has already taken this course in the past Hint: look at their credit course list
	   // If not, then find the active course in courses array list using course code
	   // If active course found then check to see if student already enrolled in this course
	   // If not already enrolled
	   //   add student to the active course
	   //   add course to student list of credit courses with initial grade of 0
	
		Student s = findStudent(studentId); // finds if student is in registry, if not s = null
		ActiveCourse ac = findCourse(courseCode); // finds if the course is in activecourse, if not ac = null
		
		if (s == null) {
			return;
		}
		if (ac == null) {
			return;
		}

		 for (int i = 0; i < s.getCourses().size(); i++) {
				if (s.getCourses().get(i).getCode().equalsIgnoreCase(courseCode)) { // checks to see if already taken in creditcourse
					 return;
			 }
		}

		 for (int i = 0; i < ac.getStudent().size(); i++) { // is student enrolled?
			 if (ac.getStudent().get(i).getId().equals(studentId)) {
				 return;
			 }
		}
	
		ac.addStudent(s);  //add student to active course
		s.addCourse(ac.getName(), ac.getCode(), ac.getDescr(), ac.getFormat(), ac.getSemester(), 0); //add creditcourse to student 
   }
   
   // Given a studentId and a course code, drop student from the active course
	 /**
		* @param studentId ID of Student
		* @param courseCode course code of course to be dropped
	  */
   public void dropCourse(String studentId, String courseCode)
   {
	   // Find the active course
	   // Find the student in the list of students for this course
	   // If student found:
	   //   remove the student from the active course
	   //   remove the credit course from the student's list of credit courses
		 ActiveCourse ac = findCourse(courseCode);
		 Student s = findStudent(studentId);

		if (ac != null && s != null) {
			ac.removeStud(studentId);	// Removes the Student from Active Course
			for (int i = 0; i < s.getCourses().size(); i++) {
				if (s.getCourses().get(i).getCode().equalsIgnoreCase(courseCode)) { // Finds credit course in the CreditCourse arraylist in Student
					s.removeActiveCourse(courseCode); // Removes CreditCourse from Student
				 }
			 }
		 }
   }
   
   // Print all active courses
   public void printActiveCourses()
   {
		Set<String> keySet = courses.keySet();
		for (String key : keySet) {
			ActiveCourse ac = courses.get(key);
			System.out.println(ac.getDescription());
		}
   }
   
   // Print the list of students in an active course
   public void printClassList(String courseCode)
   {
		ActiveCourse ac = findCourse(courseCode);
		if (ac != null) {
			ac.printClassList();
		}
   }
   
   // Given a course code, find course and sort class list by student name
   public void sortCourseByName(String courseCode)
   {
		ActiveCourse ac = findCourse(courseCode);
		if (ac != null) {
			ac.sortByName();
		}
   }
   
   // Given a course code, find course and sort class list by student name
   public void sortCourseById(String courseCode)
   {
		ActiveCourse ac = findCourse(courseCode);
		if (ac != null) {
			ac.sortById();
		}
	 }
   
   // Given a course code, find course and print student names and grades
   public void printGrades(String courseCode)
   {
		ActiveCourse ac = findCourse(courseCode);
		if (ac != null) {
			ac.printGrades();
		}
   }
   
   // Given a studentId, print all active courses of student
   public void printStudentCourses(String studentId)
   {
		Student s = findStudent(studentId);
		if (s != null) {
			s.printActiveCourses();
		}
   }
   
   // Given a studentId, print all completed courses and grades of student
   public void printStudentTranscript(String studentId)
   {
		Student s = findStudent(studentId);
		if (s != null) {
			s.printTranscript();
		}
   }
   
   // Given a course code, student id and numeric grade
   // set the final grade of the student
	 /**
	  * @param courseCode Course code of Course
		* @param studentId ID of Student
		* @param grade The grade to be set as final grade
		* If activecourse and student are found, set student's grade and set activecourse inactive
	  */
	 
	 public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   // find the active course
	   // If found, find the student in class list
	   // then search student credit course list in student object and find course
	   // set the grade in credit course and set credit course inactive
	  
		ActiveCourse ac = findCourse(courseCode);
		Student s = findStudent(studentId);

		if (ac != null && s != null) {
			for (int i = 0; i < s.getCourses().size(); i++) {
				if (s.getCourses().get(i).getCode().equalsIgnoreCase(courseCode)) {
				  s.getCourses().get(i).setGrade(grade);
					s.getCourses().get(i).setInactive();
				 }
			 }
		 }
   }
  
	
	/**
	 * @param id Id of student
	 * @return student, if not found then return null
	 * Searches treemap of students to find Student
	 */
	private Student findStudent(String id) {
		Set<String> keySet = students.keySet();
		for (String key : keySet) {
			if (key.equals(id)) {
				Student s = students.get(key);
				return s;
			}
		}
		return null;
	}
	
	/**
	 * @param code coursecode
	 * @return Active course, if not found then return null
	 * Searches treemap courses to find Active Course
	 */
	private ActiveCourse findCourse(String code) {
		Set<String> keySet = courses.keySet();
		for (String key : keySet) {
			if (key.equalsIgnoreCase(code)) {
				ActiveCourse ac = courses.get(key);
				return ac;
			}
		}
		return null;
	}
	
	public TreeMap<String, ActiveCourse> getCourses() {
		return courses;
	}
}
