public class CreditCourse extends Course 
{
	// add a constructor method with appropriate parameters
	// should call the super class constructor
	private String semester;
	private double grade;
	public boolean active;
	
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		// add code
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade = grade;
		active = true;
	}
	
	public boolean getActive()
	{
		// add code and remove line below
		return active;
	}
	
	public void setActive()
	{
		// add code
		active = true;
		
	}
	
	public void setInactive()
	{
		// add code
		active = false;
	}
	
	public String displayGrade()
	{
		// Change line below and print out info about this course plus which semester and the grade achieved
		// make use of inherited method in super class
		return getInfo() + " " + semester + " " + "Grade " + convertNumericGrade(grade);
	}
	
	/**
	  * Returns grade of credit course
	  */
	public double getGrade()
	{
		return grade;
	}
	/**
	  * Sets grade to inputted grade
		* @param grade1 inputted grade
	  */
	public void setGrade(double grade1) {
		grade = grade1;
	}
	
}