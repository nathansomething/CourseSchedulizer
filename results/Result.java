package results;
import java.util.Map;
import java.util.Set;

/**
 * A class to store a search result.
 * Just one way to store data for the results panel mockup. May not end up using.
 * @author Jesse
 *
 */
public class Result {
	private int id;
	private String dept;
	private int code;
	private String name;
	private int crn;
	private String professor;
	// day mapped to a meeting time.
	private Map<String, MeetingTime> meetings;
	// attribute mapped to a boolean, true if the course has the attribute.
	private Set<String> attributes;
	
	// constructor without id field.
	public Result(String dept, int code, String name, int crn,
			String professor, Map<String, MeetingTime> meetings,
			Set<String> attributes) {
		this.dept = dept;
		this.code = code;
		this.name = name;
		this.crn = crn;
		this.professor = professor;
		this.meetings = meetings;
		this.attributes = attributes;
	}
	
	public Result(int id, String dept, int code, String name, int crn,
			String professor, Map<String, MeetingTime> meetings,
			Set<String> attributes) {
		this.id = id;
		this.dept = dept;
		this.code = code;
		this.name = name;
		this.crn = crn;
		this.professor = professor;
		this.meetings = meetings;
		this.attributes = attributes;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCrn() {
		return crn;
	}

	public void setCrn(int crn) {
		this.crn = crn;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Map<String, MeetingTime> getMeetings() {
		return meetings;
	}

	public void setMeetings(Map<String, MeetingTime> meetings) {
		this.meetings = meetings;
	}

	public Set<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<String> attributes) {
		this.attributes = attributes;
	}  
}
