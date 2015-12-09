package dataRetriever;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import results.ResultsPanel;

public class Course {

	public String classroom;
	public String courseNum;
	public Integer credits;
	public String crn;
	public List<Day> days;
	public String depHeader;
	public String description;
	public LocalTime endTime;
	public Boolean isHonors;
	public String location;
	public String professor;
	public String semester;
	public LocalTime startTime;
	public String title;
	public static final DateTimeFormatter HOURS_MINS_AM_PM = DateTimeFormatter.ofPattern("h:mm a");
	
	Course() {
		this.classroom = "";
		this.courseNum = "";
		this.credits = 0;
		this.crn = "";
		this.days = new ArrayList<>();
		this.depHeader = "";
		this.description = "";
		this.endTime = LocalTime.of(0, 0);
		this.isHonors = false;
		this.location = "";
		this.professor = "";
		this.semester = "";
		this.startTime = LocalTime.of(0, 0);
		this.title = "";
	}
	
	@Override
	public String toString() {
		return "----------------------------------" +
			"\n Classroom: " + this.classroom +
			"\n Course Number: " + this.courseNum +
			"\n Credits: " + this.credits +
			"\n CRN: " + this.crn +
			"\n Days: " + this.days +
			"\n Department Header: " + this.depHeader +
			"\n Description: " + this.description +
			"\n End Time: " + this.endTime +
			"\n Is Honors: " + this.isHonors +
			"\n Location: " + this.location +
			"\n Professor: " + this.professor +
			"\n Semester: " + this.semester +
			"\n Start Time: " + this.startTime +
			"\n Title: " + this.title +
			"\n----------------------------------";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		else if (!(o instanceof Course)) {
			return false;
		}
		Course temp = (Course) o;
		return this.crn.equals(temp.crn);
 	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(this.crn);
	}
                
    public String otherToString(){
        return "----------------------------------" +
            "\nName: " + this.title +
			"\nCRN: " + this.crn +
            "\nID: " + this.courseNum +
			"\nDays: " + this.days +
			"\nStart Time: " + this.startTime.format(HOURS_MINS_AM_PM) +
            "\nEnd Time: " + this.endTime.format(HOURS_MINS_AM_PM) +
            "\nClassroom: " + this.classroom;
    }
    
    public boolean conflictsWithOtherCourse(Course c) {
    	return (this.conflictsWithOtherCourseDay(c) || c.conflictsWithOtherCourseDay(this)) &&
    			(this.conflictsWithOtherCourseTime(c) || c.conflictsWithOtherCourseTime(this));
    }
    
    private boolean conflictsWithOtherCourseTime(Course c) {
    	return ((this.startTime.toSecondOfDay() <= c.startTime.toSecondOfDay()) && (c.startTime.toSecondOfDay() <= this.endTime.toSecondOfDay())) ||
    			((this.startTime.toSecondOfDay() <= c.startTime.toSecondOfDay()) && (c.endTime.toSecondOfDay() <= this.endTime.toSecondOfDay()));
    }
    
    private boolean conflictsWithOtherCourseDay(Course c) {
    	for (Day day : this.days) {
    		if (c.days.contains(day)) {
    			return true;
    		}
    	}
    	return false;
    }
}