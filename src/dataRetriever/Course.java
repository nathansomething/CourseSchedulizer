package dataRetriever;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Course {

	public List<String> attributes;
	public Double credits;
	public String crn;
	public List<Day> days;
	public LocalTime endTime;
	public String id;
	public String location;
	public String name;
	public String professor;
	public LocalTime startTime;
	public String term;
	
	Course() {
		this.attributes = new ArrayList<>();
		this.credits = 0.0;
		this.crn = "";
		this.days = new ArrayList<>();
		this.endTime = null;
		this.id = "";
		this.location = "";
		this.name = "";
		this.professor = "";
		this.startTime = null;
		this.term = "";
	}
	
	@Override
	public String toString() {
		return "----------------------------------" +
				"\nAttributes: " + this.attributes +
				"\nCredits: " + this.credits +
				"\nCRN: " + this.crn +
				"\nDays: " + this.days +
				"\nEnd Time: " + this.endTime +
				"\nID: " + this.id +
				"\nLocation: " + this.location +
				"\nName: " + this.name +
				"\nProfessor: " + this.professor +
				"\nStart Time: " + this.startTime +
				"\nTerm: " + this.term +
				"\n----------------------------------";
	}
}