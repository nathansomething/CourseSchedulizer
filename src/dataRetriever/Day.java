package dataRetriever;

public enum Day {

	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY;
	
	public String toTitleCase() {
		return this.toString().substring(0, 1) + this.toString().substring(1).toLowerCase();
	}
}
