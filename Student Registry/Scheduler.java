import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.io.*;

public class Scheduler 
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	TreeMap<String,ActiveCourse> schedule;
	private String[][] timeTable = new String[9][5];
		
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
		// sets up empty schedule
		for (int i = 0; i < timeTable.length; i++) {
			for (int j = 0; j < timeTable[i].length; j++) {
				timeTable[i][j] = " ";
			}
		}
	}
	
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)	
	{
		// see assignment doc
			ActiveCourse ac = null;
			
			Set<String> keySet = schedule.keySet();
			if (keySet.contains(courseCode.toUpperCase())) { 
				ac = findCourse(courseCode);
			}
			else {
				String message = "Unknown course: " + courseCode;
				throw new UnknownCourseException(message);
			}
	
			if (day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed") || day.equalsIgnoreCase("Thu") || day.equalsIgnoreCase("Fri")) {
				ac.setDay(day);
			}
			else {
				String message = "Invalid Lecture Day";
				throw new InvalidDayException(message);
			}
			
			if (startTime >= 800 && startTime + duration < 1700 && startTime % 100 == 0) {
				ac.setStart(startTime);
			}
			else {
				String message = "Invalid Lecture Start Time";
				throw new InvalidTimeException(message);
			}
			
			if (duration == 1 || duration == 2 || duration == 3) {
				ac.setDuration(duration);
				ac.setEnd(startTime, duration);
			}
			else {
				String message = "Invalid Lecture Duration";
				throw new InvalidDurationException(message);
			}
			
			// index of day with start time
			int x = convertDay(day);
			int y = convertTime(startTime);
			
			// checks if those time slots in schedule are empty
			for (int i = 0; i < duration; i++) {
				if (timeTable[y][x] != " ") {
					String message = "Lecture Time Collision";
					throw new LectureTimeCollisionException(message);
				}
				y++;
			}
			// reset y index, so it starts at the correct start time
			y = convertTime(startTime);
			//add to schedule
			for (int i = 0; i < duration; i++) {
				timeTable[y][x] = courseCode;
				y++;
			}

	}
	
	/**
	 * @param courseCode code of course
	 * resets the lecture day, start time, and duration variables and
	 * removes this course from the schedule
	 */
	public void clearSchedule(String courseCode)
	{
		// see Assignment doc
		ActiveCourse ac = findCourse(courseCode);
		if (ac == null) {
			return;
		}
		ac.reset();
		for (int i = 0; i < timeTable.length; i++) {
      for (int j = 0; j < timeTable[i].length; j++) {
          if (timeTable[i][j].equalsIgnoreCase(courseCode)) {
						timeTable[i][j] = " ";
					}
				}
			}
	}
	
	/**
	 * Prints the days and times along with the schedule
	 */
	public void printSchedule()
	{
		// see assignment doc
		int time = 800;
		String str = null;
		String t = "\t";
		System.out.println(t + "Mon" + t + "Tue" + t + "Wed" + t + "Thu" + t + "Fri");
		for (String[] i : timeTable) {
			if (time < 1000) {
					str = String.format("%04d", time);
					System.out.print(str + t);
				}
				else {
					System.out.print(time + t);
				}
			time += 100;
			for (String j : i) {
				System.out.print(j + "\t");
			}
			System.out.println();
		}
		
	}
	/**
	 * Several custom exception classes created that return custom messages
	*/
	class UnknownCourseException extends RuntimeException
	{
		public UnknownCourseException() {}
		public UnknownCourseException(String message) {
			super(message); }
			
	}
	
	class InvalidDayException extends RuntimeException
	{
		public InvalidDayException() {}
		public InvalidDayException(String message) {
			super(message); 
		}
	}
	
	class InvalidTimeException extends RuntimeException
	{
		public InvalidTimeException() {}
		public InvalidTimeException(String message) {
			super(message);
		}
	}
	
	class InvalidDurationException extends RuntimeException
	{
		public InvalidDurationException() {}
		public InvalidDurationException(String message) {
			super(message);
		}
	}
	
	class LectureTimeCollisionException extends RuntimeException
	{
		public LectureTimeCollisionException() {}
		public LectureTimeCollisionException(String message) {
			super(message);
		}
	}
	
	/**
	 * @param code coursecode
	 * @return Active course, if not found then return null
	 * Searches treemap schedule to find Active Course
	 */
	private ActiveCourse findCourse(String code) {
		Set<String> keySet = schedule.keySet();
		for (String key : keySet) {
			if (key.equalsIgnoreCase(code)) {
				ActiveCourse ac = schedule.get(key);
				return ac;
			}
		}
		return null;
	}
	
	/**
	 * @param day Day of the week
	 * converts day to index of 2d array
	 */
	private int convertDay(String day) {
		int y = -1;
		
		if (day.equals("Mon")) { y = 0; }
		else if (day.equals("Tue")) { y = 1; }
		else if (day.equals("Wed")) { y = 2; }
		else if (day.equals("Thu")) { y = 3; }
		else if (day.equals("Fri")) { y = 4; }
		
		return y;
	}
	
	/**
	 * @param start Start time of course
	 * converts startTime to index of 2d array
	 */
	private int convertTime(int start) {
		int x = -1;
		
		if (start == 800) { x = 0; }
		else if (start == 900)  { x = 1; }
		else if (start == 1000) { x = 2; }
		else if (start == 1100) { x = 3; }
		else if (start == 1200) { x = 4; }
		else if (start == 1300) { x = 5; }
		else if (start == 1400) { x = 6; }
		else if (start == 1500) { x = 7; }
		else if (start == 1000) { x = 8; }
		
		return x;
	}
	
}

