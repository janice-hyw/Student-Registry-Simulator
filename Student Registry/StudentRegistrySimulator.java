import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException ;
import java.util.NoSuchElementException;


public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {
		Registry registry = null;
		try {
			registry = new Registry();
		}
		catch(FileNotFoundException e) {
			System.out.println("students.txt File Not Found");
			return;
		}
		catch(NoSuchElementException e) {
			System.out.println("BadFile students.txt");
			return;
		}
		
		Scheduler scheduler = new Scheduler(registry.getCourses());
	
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  // register a new student in registry
			  // get name and student id string 
			  // e.g. reg JohnBoy 74345
			  // Checks:
			  //  ensure name is all alphabetic characters
			  //  ensure id string is all numeric characters
				String name = " ";
				String id = " ";
				
				if (commandLine.hasNext()) {
					name = commandLine.next();
				}
				
				if (commandLine.hasNext()) {
					id = commandLine.next();
				}
				
			
				if (!isStringOnlyAlphabet(name)) {
					System.out.println("Invalid Characers in Name " + name);
				}
				if (id != " " && !isNumeric(id)) {
					System.out.println("Invalid Characters in ID " + id);
			  }
				if (isStringOnlyAlphabet(name) && isNumeric(id)) {
					registry.addNewStudent(name, id);
				}
				
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  // delete a student from registry
			  // get student id
			  // ensure numeric
			  // remove student from registry
				String id1 = " ";
				if (commandLine.hasNext()) {
					id1 = commandLine.next();
				}
				
				registry.removeStudent(id1);
				
		  }
		  
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			 // add a student to an active course
			 // get student id and course code strings
			 // add student to course (see class Registry)
			  String id = " ";
				String courseCode = " ";
				if (commandLine.hasNext()) {
					id = commandLine.next();
				}
				else {
				System.out.println("Invalid Entry");
				}
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
				else {
				System.out.println("Invalid Entry");
				}
				registry.addCourse(id, courseCode);
				
				
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  // get student id and course code strings
			  // drop student from course (see class Registry)
				String id = " ";
				String courseCode = " ";
				if (commandLine.hasNext()) {
					id = commandLine.next();
				}
	
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
				registry.dropCourse(id, courseCode);
				
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  // print all active courses
				registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  // get course code string
			  // print class list (i.e. students) for this course
			  String courseCode = " ";
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
				registry.printClassList(courseCode);
				
				
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  // get course code string
			  // print name, id and grade of all students in active course
				String courseCode = " ";
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
				
				registry.printGrades(courseCode);
				
		  }
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  // get student id string
			  // print all credit courses of student
			  String id = " ";
				if (commandLine.hasNext()) {
					id = commandLine.next();
				}
				
				registry.printStudentCourses(id);
				
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  // get student id string
			  // print student transcript
				String id = " ";
				if (commandLine.hasNext()) {
					id = commandLine.next();
				}
				registry.printStudentTranscript(id);
				
			  
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  // set final grade of student
			  // get course code, student id, numeric grade
			  // use registry to set final grade of this student (see class Registry)
				String courseCode = " ";
				String id = " ";
				String grade = " ";
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
			
				if (commandLine.hasNext()) {
					id = commandLine.next();
				}
			
				if (commandLine.hasNext()) {
					grade = commandLine.next();
				}
				
				registry.setFinalGrade(courseCode, id, Integer.parseInt(grade));
				
		  }
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  // get course code
			  // sort list of students in course by name (i.e. alphabetically)
			  // see class Registry
				String courseCode = " ";
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
				
				registry.sortCourseByName(courseCode);
				
				
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			// get course code
			// sort list of students in course by student id
			// see class Registry
			String courseCode = " ";
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
					registry.sortCourseById(courseCode);
		
		  }
			// sets course day, start time, duration
			// adds course to schedule
			else if (command.equalsIgnoreCase("SCH"))
			{
				String courseCode = null;
				String day = null;
				int start = 0;
				int duration = 0;
				
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
				if (commandLine.hasNext()) {
					day = commandLine.next();
				}
				if (commandLine.hasNext()) {
					start = Integer.parseInt(commandLine.next());
				}
				if (commandLine.hasNext()) {
					duration = Integer.parseInt(commandLine.next());
				}
				
				try {
					scheduler.setDayAndTime(courseCode, day, start, duration);
				}
				catch(RuntimeException e) {
					System.out.println(e.getMessage());
				}
			}
			// clears course day, start, and duration time
			// clears course from schedule
			else if (command.equalsIgnoreCase("CSCH"))
			{
				String courseCode = null;
				if (commandLine.hasNext()) {
					courseCode = commandLine.next();
				}
				scheduler.clearSchedule(courseCode);
			}
			// prints schedule
			else if (command.equalsIgnoreCase("PSCH"))
			{
				scheduler.printSchedule();
			}
		  System.out.print("\n>");
	  }
  }
 
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      // write method to check if string str contains only alphabetic characters 
	  for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetter(str.charAt(i))) {
		return false;
			}
		}
		return true;
  } 
  
  public static boolean isNumeric(String str)
  {
		 // write method to check if string str contains only numeric characters
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
	  return true;
  }
	
  }
  
