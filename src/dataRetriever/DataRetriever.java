package dataRetriever;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		this.query = "SELECT * FROM COURSE ";
		if (!whereQuery.equals("")) {
			this.query += whereQuery;
		}
		System.out.println(this.query); //For Debugging
		this.statement.executeQuery(this.query);
        ResultSet resultSet = statement.getResultSet();
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
        	Course course = new Course();
        	course.crn = resultSet.getString("crn");
        	course.credits = resultSet.getDouble("credits");
        	//course.endTime = LocalTime.of(resultSet.getTime("endtime").getHours(), resultSet.getTime("endtime").getMinutes());
        	course.id = resultSet.getString("id");
        	course.location = resultSet.getString("location");
        	course.name = resultSet.getString("name");
        	course.professor = resultSet.getString("professor");
        	//course.startTime = LocalTime.of(resultSet.getTime("starttime").getHours(), resultSet.getTime("starttime").getMinutes());
        	course.term = resultSet.getString("term");
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
