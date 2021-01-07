public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	   
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	   
	public String getCode()
	{
	   return code;
	}
	   
	public String getName()
	{
	  return name;
	}
	   
	public String getFormat()
	{
	  return format;
	}
	   
	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}
	
	 public String getInfo()
	 {
	   return code +" - " + name;
	 }
	 
	 // static method to convert numeric score to letter grade string 
	 // e.g. 91 --> "A+"
	 public static String convertNumericGrade(double score)
	 {
		 // fill in code
		 if (score <= 100 && score >= 90) {
		 return "A+"; }
		 if (score <= 89 && score >= 85) {
		 return "A"; }
		 if (score <= 84 && score >= 80) {
	   return "A-"; }
		 if (score <= 79 && score >= 77) {
	   return "B+"; }
		 if (score <= 76 && score >= 73) {
	   return "B"; }
		 if (score <= 72 && score >= 70) {
	   return "B-"; }
		 if (score <= 69 && score >= 67) {
	   return "C+"; }
		 if (score <= 66 && score >= 63) {
	   return "C"; }
		 if (score <= 62 && score >= 60) {
	   return "C-"; }
		 if (score <= 59 && score >= 57) {
	   return "D+"; }
		 if (score <= 56 && score >= 53) {
	   return "D"; }
		 if (score <= 52 && score >= 50) {
	   return "D-"; }
		 if (score <= 49 && score >= 0) {
	   return "F"; }
		 return "";
	 }
	 
	 /**
	  * Returns description of course
	  */
	 public String getDescr() {
		 return description;
	 }
	 
}
