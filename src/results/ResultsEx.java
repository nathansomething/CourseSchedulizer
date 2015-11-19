package results;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class full of mock test data.  Should probably be used to instantiate real
 * data from the database when we run queries.
 * @author Jesse
 *
 */
public class ResultsEx {
	private List<Result> results = new ArrayList<Result>();
	MeetingTime mt1450_1630 = new MeetingTime(890, 990);
	MeetingTime mt0915_1020 = new MeetingTime(555, 620);
	MeetingTime mt0800_1135 = new MeetingTime(480, 695);
	Map<String, MeetingTime> meetingMapHci = new HashMap<String, MeetingTime>();
	Map<String, MeetingTime> meetingMapLogic = new HashMap<String, MeetingTime>();
	Map<String, MeetingTime> meetingMap2D = new HashMap<String, MeetingTime>();
	Set<String> attrsHci = new HashSet<String>();
	Set<String> attrsLogic = new HashSet<String>();
	Set<String> attrs2D = new HashSet<String>();
	
	private void createResults() {	
		this.create2DMaps();
		this.createHciMaps();
		this.createLogicMaps();
		
		for (int i = 0; i < 100; i++) {
			Result res = null;
			
			if (i % 3 == 0) {
				res = new Result(i, "ARTF", 1122, "2D Foundations", 87102, "Sophia Ainslie", meetingMap2D, attrs2D);
			}
			else if (i % 3 == 1) {
				res = new Result(i, "IS", 4300, "Human Computer Interaction", 10195, "Timothy Bickmore", meetingMapHci, attrsHci);
			}
			else if (i % 3 == 2) {
				res = new Result(i, "CS", 2800, "Logic and Computation", 34521, "Thomas Wahl", meetingMapLogic, attrsLogic);
			}
			results.add(res);
		}
	}
	
	private void createHciMaps() {
		meetingMapHci.put("Monday", mt1450_1630);
		meetingMapHci.put("Wednesday", mt1450_1630);
		attrsHci.add("Analytical Thinking");
		attrsHci.add("Capstone");
		attrsHci.add("Honors");
	}
	
	private void createLogicMaps() {
		meetingMapLogic.put("Monday", mt0915_1020);
		meetingMapLogic.put("Wednesday", mt0915_1020);
		meetingMapLogic.put("Thursday", mt0915_1020);
		attrsLogic.add("Analytical Thinking");
		attrsLogic.add("Mathematical Thinking");
	}
	
	private void create2DMaps() {
		meetingMap2D.put("Tuesday", mt0800_1135);
		attrs2D.add("Comparative Cultures");
		attrs2D.add("Honors");
	}
	
	public List<Result> getResults() {
		createResults();
		return results;
	}
}
