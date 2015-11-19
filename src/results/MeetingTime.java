/**
 * A class to represent a meeting time for a course section.  Times are in minutes.
 * Just one way to store data for the results panel mockup. May not end up using.
 * 
 * @author Jesse
 *
 * 12am is 0
 * 8am is 480
 * 9:15am is 555
 * 10:30am is 630
 * 11:45am is 705
 * 1:35pm is 815
 * 2:50pm is 890
 * 3:25pm is 925
 * 4:35pm is 995
 */

package results;

public class MeetingTime {
	private int startTime;
	private int endTime;
	
	MeetingTime(int startTime, int endTime) {
		if (startTime < 0 || endTime > 1440) {
			throw new RuntimeException("Invalid meeting time, not within the span of a day");
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	private boolean isAm(int time) {
		int hours = time / 60;
		return hours < 12;
	}
	
	private String makeDoubleDigitMinutes(int minutes) {
		String minsString = Integer.toString(minutes);
		if (minutes < 10) {
			return "0" + minsString;
		}
		return minsString;
	}
	
	public String toHoursMins(int time) {
		String result = "";
		boolean isAm = this.isAm(time);
		int minutes = time % 60;
		
		int hours = (time / 60) % 12;
		result = hours + ":" + this.makeDoubleDigitMinutes(minutes);
		result += (isAm) ? "am" : "pm";
		return result;
	}
	
	public String toString() {
		return this.toHoursMins(this.startTime) + " to " + this.toHoursMins(this.endTime);
	}
}

