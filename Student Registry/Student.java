import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  
  public String getId()
  {
	  return id;
  }
  
  public String getName()
  {
	  return name;
  }
  
  // add a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  // create a CreditCourse object
	  // set course active
	  // add to courses array list
		CreditCourse cc = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
		cc.setActive();
		courses.add(cc);
  }
  
  
  // Prints a student transcript
  // Prints all completed (i.e. non active) courses for this student (course code, course name, 
  // semester, letter grade
  // see class CreditCourse for useful methods
  public void printTranscript()
  {
	  for (int i = 0; i < courses.size(); i++) {
			if (!courses.get(i).getActive()) {
			System.out.println(courses.get(i).displayGrade());
		}
		}
  }
  
  // Prints all active courses this student is enrolled in
  // see variable active in class CreditCourse
  public void printActiveCourses()
  {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getActive()) { 
			System.out.println(courses.get(i).getDescription());
			  }
			}
  }
  
  // Drop a course (given by courseCode)
  // Find the credit course in courses arraylist above and remove it
  // only remove it if it is an active course
  public void removeActiveCourse(String courseCode)
  {
	 for (int i = 0; i < courses.size(); i++) {
		 if (courses.get(i).getCode().equalsIgnoreCase(courseCode) && courses.get(i).getActive()) {
			courses.remove(courses.get(i));
		 }
	 }
  }
  
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  // override equals method inherited from superclass Object
  // if student names are equal *and* student ids are equal (of "this" student
  // and "other" student) then return true
  // otherwise return false
  // Hint: you will need to cast other parameter to a local Student reference variable
  public boolean equals(Object other)
  {
	 Student newStudent = (Student) other;
	 if (this.getId().equals(newStudent.getId())) {
		 return true;
	 }
	 return false;
  }
  
	/**
	 * Returns arraylist of credit courses of a student
	 */
	public ArrayList<CreditCourse> getCourses() {
		return courses;
	}
	
	/**
	 * Compares two student objects by their names.
	 * @param s2 Student object #2 to be compared with the first Student object.
	 * @return -1, 1, or 0 depending on the alphabetical order of the names.
	*/
	public int compareTo(Student s2) {
		return getName().compareTo(s2.getName());
	}
		
}
