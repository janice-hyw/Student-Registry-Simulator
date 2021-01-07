import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
	private ArrayList<Student> students; 
	private String             semester;
	private int lectureStart;
	private int lectureDuration;
	private String lectureDay;
	private int lectureEnd;
	 
   // Add a constructor method with appropriate parameters
   // should call super class constructor to initialize inherited variables
   // make sure to *copy* students array list being passed in into new arraylist of students
   // see class Registry to see how an ActiveCourse object is created and used
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
		super(name, code, descr, fmt);
		this.students = new ArrayList<Student>(students);
		this.semester = semester;
   }
   
   public String getSemester()
   {
	   return semester;
   }
   
   // Prints the students in this course  (name and student id)
   public void printClassList()
   {
		 for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).toString());
		 }
   }
   
   // Prints the grade of each student in this course (along with name and student id)
   
   public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++) 
		 {
			 System.out.println( students.get(i).getId() + " " + students.get(i).getName() + " " + getGrade(students.get(i).getId()) );
		 }
   }
   
	 
   // Returns the numeric grade in this course for this student
   // If student not found in course, return 0 
	 /** 
		 * Finds the student in ArrayList students and stores the index of the student into sIndex.
		 * If the student has been found, and the course code of the active course matches 
		 * the course code in the student's arraylist of credit courses,
		 * return the grade of that course.
		 * If student or course isn't found, 0 is returned.
		 */
	 public double getGrade(String studentId)
   {
	  // Search the student's list of credit courses to find the course code that matches this active course
	  // see class Student method getGrade() 	
	  // return the grade stored in the credit course object
		int sIndex = -1;
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId().equals(studentId)) {
				sIndex = i;
			}
		}
		if (sIndex != -1) {
			for (int j = 0; j < students.get(sIndex).getCourses().size(); j++) {
				if (students.get(sIndex).getCourses().get(j).getCode().equals(getCode())) {
					return students.get(sIndex).getCourses().get(j).getGrade();
				}
			}
		}
		return 0;
   }
   
   // Returns a String containing the course information as well as the semester and the number of students 
   // enrolled in the course
   // must override method in the superclass Course and use super class method getDescription()
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() + "\n";
   }
    
   // Sort the students in the course by name using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortByName()
   {
 	  this.students.sort(new NameComparator());
   }
   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student s1, Student s2) {
			return s1.compareTo(s2);
		}
		/** 
		 * Makes use of compareTo method created in Student class.
		 */
   }
   
   // Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortById()
   {
		this.students.sort(new IdComparator());
   }
   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student s1, Student s2) {
			if (Integer.parseInt(s1.getId()) > Integer.parseInt(s2.getId())) {
			return 1; 
		}
			if (Integer.parseInt(s1.getId()) < Integer.parseInt(s2.getId())) {
			return -1; 
		}
			return 0;
		}
   }
	 
	 
	/**
	 * Returns arraylist of students taking this active course
	 */
	 public ArrayList<Student> getStudent() {
		 return students;
	 }
	 
	 /**
		* Removes the student from the arraylist of students in an active course.
		* @param studentId Student Id used for identification of student.
	  */
	 public void removeStud(String studentId) {
		 for (int i = 0; i < students.size(); i++) {
			 if (students.get(i).getId().equals(studentId)) {
				 students.remove(i);	
			 }
		 }
	 }
	 
	 /**
		* Adds student to the arraylist of students in an active course.
		* @param student Student object to be added.
	  */
	 public void addStudent(Student student) {
		students.add(student);	 
	 }
	 
	 /**
		* Several set methods to set the day, time, and duration of active course
	  */
	 public void setDay(String day) {
		lectureDay = day;
	 }
	 
	 public void setStart(int start) {
		lectureStart = start;
	 }
	 
	 public void setDuration(int duration) {
		lectureDuration = duration;
	 }
	 
	 public void setEnd (int start, int duration) {
		lectureEnd = start + duration;
	 }
	 
	/**
		* Reset lecture day, start time, and duration
	  */
	public void reset() {
		lectureDay = "";
		lectureStart = 0;
		lectureDuration = 0;
	}

}
