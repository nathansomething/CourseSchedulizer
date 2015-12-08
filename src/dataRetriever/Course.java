package dataRetriever;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import results.ResultsPanel;

public class Course {

	public String classroom;
	public Double credits;
	public String crn;
	public List<Day> days;
	public String description;
	public LocalTime endTime;
	public String id;
	public Boolean isHonors;
	public String location;
	public String name;
	public String professor;
	public LocalTime startTime;
	public String term;
	
	Course() {
		this.classroom = "";
		this.credits = 0.0;
		this.crn = "";
		this.days = new ArrayList<>();
		this.endTime = null;
		this.id = "";
		this.isHonors = false;
		this.location = "";
		this.name = "";
		this.professor = "";
		this.startTime = null;
		this.term = "";
	}
	
	@Override
	public String toString() {
		return "----------------------------------" +
				"\nClassroom: " + this.classroom +
				"\nCredits: " + this.credits +
				"\nCRN: " + this.crn +
				"\nDays: " + this.days +
				"\nDescription: " + this.description +
				"\nEnd Time: " + this.endTime.format(ResultsPanel.HOURS_MINS_AM_PM) +
				"\nHonors: " + this.isHonors.toString() +
				"\nID: " + this.id +
				"\nLocation: " + this.location +
				"\nName: " + this.name +
				"\nProfessor: " + this.professor +
				"\nStart Time: " + this.startTime.format(ResultsPanel.HOURS_MINS_AM_PM) +
				"\nTerm: " + this.term +
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
            "\nName: " + this.name +
			"\nCRN: " + this.crn +
            "\nID: " + this.id +
			"\nDays: " + this.days +
			"\nStart Time: " + this.startTime.format(ResultsPanel.HOURS_MINS_AM_PM) +
            "\nEnd Time: " + this.endTime.format(ResultsPanel.HOURS_MINS_AM_PM) +
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