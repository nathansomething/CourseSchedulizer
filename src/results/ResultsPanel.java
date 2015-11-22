package results;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import schedule.Schedule;
import dataRetriever.Course;
import dataRetriever.DataRetriever;

/**
 * The class for the results panel.  Use the getResultsPanel() method to add it to a JFrame.
 * @author Jesse
 *
 */
public class ResultsPanel extends JPanel {
	private String query;
	
	public enum Weekday {Monday, Tuesday, Wednesday, Thursday, Friday};

	ImageIcon expand = new ImageIcon(ResultsPanel.class.getResource("/img/expand.png"));
	Image newExpand = expand.getImage().getScaledInstance(expand.getIconWidth() / 2, expand.getIconHeight() / 2,  java.awt.Image.SCALE_SMOOTH ); 
	
	ImageIcon collapse = new ImageIcon(ResultsPanel.class.getResource("/img/collapse.png"));
	Image newCollapse = collapse.getImage().getScaledInstance(collapse.getIconWidth() / 2, collapse.getIconHeight() / 2,  java.awt.Image.SCALE_SMOOTH ); 
	
	Color courseBarBackgroundColor = Color.DARK_GRAY;
	Color courseInfoBackgroundColor = Color.LIGHT_GRAY;
	
	public ResultsPanel(String query) {
		this.query = query;
		
		DataRetriever dataRetriever;
		List<Course> courses = new ArrayList<>();
		
		try {
			dataRetriever = new DataRetriever();
			courses = dataRetriever.getData(this.query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout(0, 0));
		JLabel lblResultsForYour = new JLabel(" " + courses.size() + " Results For Your Search");
		lblResultsForYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultsForYour.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		add(lblResultsForYour, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel courseList = new JPanel();
		GroupLayout gl_courseList = new GroupLayout(courseList);
		ParallelGroup parallelGroup = gl_courseList.createParallelGroup();
		SequentialGroup seqGroup = gl_courseList.createSequentialGroup();
				
		// Loop to populate results with courses.
		for (Course course : courses) {
			JPanel coursePanel = this.createCoursePanel(course);
			coursePanel.addMouseListener(new CourseClickListener(newExpand, newCollapse));
			seqGroup.addComponent(coursePanel,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
			seqGroup.addGap(10);
			parallelGroup.addComponent(coursePanel,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
			courseList.add(coursePanel);
		}
		
		gl_courseList.setHorizontalGroup(parallelGroup);
		gl_courseList.setVerticalGroup(seqGroup);
		courseList.setLayout(gl_courseList);
		scrollPane.setViewportView(courseList);
	}

	public ResultsPanel getResultsPanel() {
		return this;
	}
	
	public boolean isCourseBar(JPanel panel) {
		return panel.getName().equals("courseBar");
	}
	
	private JPanel createCoursePanel(Course searchResult) {
		JPanel course = new JPanel();
		course.setName("course: " + searchResult.id);
		course.setBorder(new LineBorder(Color.black));
		course.setLayout(new BorderLayout(0, 0));
		course.add(this.createCourseBar(searchResult), BorderLayout.NORTH);
		course.add(this.createCourseInfo(searchResult), BorderLayout.CENTER);
		return course;
	}
	
	private JPanel createCourseBar(Course searchResult) {
		setCourseBarColor(0);
		JPanel courseBar = new JPanel();
		courseBar.setName("courseBar");
		courseBar.setBackground(courseBarBackgroundColor);
		courseBar.setLayout(new BorderLayout(0, 0));
		courseBar.setPreferredSize(new Dimension(courseBar.getWidth(), courseBar.getHeight() + 60));

		JLabel courseInfoLbl = new JLabel(" " + searchResult.id + ": " + searchResult.name);
		courseInfoLbl.setForeground(Color.WHITE);
		courseInfoLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		courseInfoLbl.setHorizontalAlignment(SwingConstants.LEFT);
		courseBar.add(courseInfoLbl, BorderLayout.WEST);

		JLabel courseInfoToggle = new JLabel();
		courseInfoToggle.setIcon(new ImageIcon(newExpand));
		courseInfoToggle.setName("courseInfoToggle_" + searchResult.id);
		courseBar.add(courseInfoToggle, BorderLayout.EAST);
		return courseBar;
	}
	
	private JPanel createCourseInfo(Course searchResult) {
		JPanel courseInfo = new JPanel();
		courseInfo.setName("courseInfo");
		courseInfo.setVisible(false);
		courseInfo.setBackground(courseInfoBackgroundColor);
		courseInfo.setLayout(new BorderLayout(0, 0));

		JLabel crnLbl = new JLabel(" CRN: " + searchResult.crn);
		crnLbl.setForeground(new Color(0, 0, 0));
		crnLbl.setHorizontalAlignment(SwingConstants.LEFT);
		courseInfo.add(crnLbl, BorderLayout.NORTH);

		JPanel courseDetails = new JPanel();
		courseDetails.setBackground(courseInfoBackgroundColor);
		courseInfo.add(courseDetails);
		courseDetails.setLayout(new BorderLayout(0, 0));

		JLabel lblProfessor = new JLabel(" Professor: " + searchResult.professor);
		lblProfessor.setForeground(new Color(0, 0, 0));
		lblProfessor.setVerticalAlignment(SwingConstants.TOP);
		courseDetails.add(lblProfessor, BorderLayout.NORTH);

		JPanel courseMeetings = new JPanel();
		courseMeetings.setBackground(courseInfoBackgroundColor);
		courseMeetings.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Meeting Times", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		courseDetails.add(courseMeetings);
		courseMeetings.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel courseDetails2 = new JPanel();
		courseDetails.add(courseDetails2, BorderLayout.SOUTH);
		courseDetails2.setLayout(new BorderLayout(0, 0));

		JPanel courseAttributes = new JPanel();
		courseAttributes.setBackground(courseInfoBackgroundColor);
		courseAttributes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Attributes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		courseDetails2.add(courseAttributes, BorderLayout.WEST);
		courseAttributes.setLayout(new GridLayout(0, 1, 0, 0));
		courseAttributes.setPreferredSize(new Dimension(courseAttributes.getWidth() + 250, courseAttributes.getHeight()));

		JPanel scheduleOptions = new JPanel();
		scheduleOptions.setBackground(courseInfoBackgroundColor);
		scheduleOptions.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Scheduling", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		courseDetails2.add(scheduleOptions, BorderLayout.CENTER);

		JButton viewCalendar = new JButton("View Calendar");
		scheduleOptions.add(viewCalendar);
		viewCalendar.addActionListener(new ActionListener() {
			@Override
		   public void actionPerformed(ActionEvent e) {
			  Schedule schedule = Schedule.getInstance();
		      schedule.setModal(true);
		      schedule.setVisible(true);
		      return;
		   }
		});

		JButton registerCourse = new JButton("Register For Course");
		scheduleOptions.add(registerCourse);

//		for (Weekday day : Weekday.values()) {
//			JCheckBox dayBox = new JCheckBox();
//			String dayString = day.toString();
//			dayBox.setText(dayString);
//			dayBox.setEnabled(false);
//			JLabel meetingTime = new JLabel("---");
//			Map<String, MeetingTime> meetings = searchResult.getMeetings();
//
//			if (meetings.containsKey(dayString)) {
//				dayBox.setSelected(true);
//				meetingTime = new JLabel(meetings.get(dayString).toString());
//			}
//			courseMeetings.add(dayBox);
//			courseMeetings.add(meetingTime);
//		}
//
//		for (String attr : searchResult.getAttributes()) {
//			JLabel courseAttribute = new JLabel(attr);
//			courseAttributes.add(courseAttribute);
//		}
		
		return courseInfo;
	}
	
	private void setCourseBarColor(int index) {
		Color darkBlue = new Color(51,102,255);
		Color lightBlue = new Color(153, 204, 255);
		
		courseBarBackgroundColor = darkBlue;
		courseInfoBackgroundColor = lightBlue;
		
	}
}
