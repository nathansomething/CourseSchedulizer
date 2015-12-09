package dataRetriever;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

	private final String connString = "jdbc:derby:codejava/ClassData;create=true";
	private final Connection conn;
	private final Statement statement;
	private String query;
	
	
	public DataRetriever() throws SQLException {
		this.conn = DriverManager.getConnection(connString);
        if (this.conn == null) {
        	throw new SQLException("Failed to connect to database");
        }
        this.statement = this.conn.createStatement();
        this.query = "";
	}
	
	public List<Course> getData(String whereQuery) throws SQLException {
		this.query = "SELECT * FROM COURSE " + whereQuery;
		System.out.println(this.query);
		this.statement.executeQuery(this.query);
        ResultSet resultSet = statement.getResultSet();
        List<Course> courses = new ArrayList<>();
        DateTimeFormatter HOURS_MINS_AM_PM = DateTimeFormatter.ofPattern("h:mm a");
        while (resultSet.next()) {
        	Course course = new Course();
        	course.classroom = resultSet.getString("classroom");
			course.courseNum = resultSet.getString("courseNum");
			course.credits = resultSet.getInt("credits");
			course.crn = resultSet.getString("crn");
			course.depHeader = resultSet.getString("depHeader");
			course.description = resultSet.getString("description");
			course.isHonors = resultSet.getBoolean("isHonors");
			course.location = resultSet.getString("location");
			course.professor = resultSet.getString("professor");
			course.semester = resultSet.getString("semester");
			course.title = resultSet.getString("title");
			
			try {
				course.endTime = LocalTime.parse(resultSet.getString("endtime").replace("am", "AM").replace("pm", "PM"), HOURS_MINS_AM_PM);
				course.startTime = LocalTime.parse(resultSet.getString("starttime").replace("am", "AM").replace("pm", "PM"), HOURS_MINS_AM_PM);
			}
			catch (DateTimeParseException e) { }
			
        	if (resultSet.getBoolean("monday")) {
        		course.days.add(Day.MONDAY);
        	}
        	if (resultSet.getBoolean("tuesday")) {
        		course.days.add(Day.TUESDAY);
        	}
        	if (resultSet.getBoolean("wednesday")) {
        		course.days.add(Day.WEDNESDAY);
        	}
        	if (resultSet.getBoolean("thursday")) {
        		course.days.add(Day.THURSDAY);
        	}
        	if (resultSet.getBoolean("friday")) {
        		course.days.add(Day.FRIDAY);
        	}
        	if (resultSet.getBoolean("saturday")) {
        		course.days.add(Day.SATURDAY);
        	}
        	courses.add(course);
        }
	return courses;
	}
}
