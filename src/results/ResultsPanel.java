package results;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import schedule.Schedule;
import util.Colors;
import util.Fonts;
import dataRetriever.Course;
import dataRetriever.DataRetriever;
import dataRetriever.Day;

/**
 * The class for the results panel.  Use the getResultsPanel() method to add it to a JFrame.
 * @author Jesse
 *
 */
public class ResultsPanel extends JPanel {
	private static final ImageIcon EXPAND = new ImageIcon(ResultsPanel.class.getResource("/img/expand.png"));
	private static final ImageIcon COLLAPSE =  new ImageIcon(ResultsPanel.class.getResource("/img/collapse.png"));
	private static final Image NEW_EXPAND = EXPAND.getImage().getScaledInstance(EXPAND.getIconWidth() / 2, EXPAND.getIconHeight() / 2,  java.awt.Image.SCALE_SMOOTH );
	private static final Image NEW_COLLAPSE = COLLAPSE.getImage().getScaledInstance(COLLAPSE.getIconWidth() / 2, COLLAPSE.getIconHeight() / 2,  java.awt.Image.SCALE_SMOOTH );
	private static final int COURSE_BAR_HEIGHT = 60;

	private static final DateTimeFormatter HOURS_MINS_AM_PM = DateTimeFormatter.ofPattern("h:mm a");
	
	private static Color courseBarHoverColor = Colors.MEDIUM_ORANGE;
	private static Color courseBarBackgroundColor = Colors.DARK_BLUE;
	private static Color courseInfoBackgroundColor = Colors.LIGHT_BLUE;
	private String query;
	
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
		lblResultsForYour.setFont(Fonts.LUCIDA_LARGE);
		add(lblResultsForYour, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel courseList = new JPanel();
		courseList.setLayout(new BoxLayout(courseList, BoxLayout.Y_AXIS));
		Box coursesBox = Box.createVerticalBox();
		
		for (Course course : courses) {
			JPanel coursePanel = this.createCoursePanel(course);
			coursePanel.setMaximumSize(new Dimension(1000, COURSE_BAR_HEIGHT));
			coursesBox.add(coursePanel);
			coursesBox.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		coursesBox.add(Box.createVerticalGlue());
		courseList.add(coursesBox);
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
		JPanel courseBar = new JPanel();
		courseBar.setName("courseBar");
		courseBar.setBackground(courseBarBackgroundColor);
		courseBar.setLayout(new BorderLayout(0, 0));
		courseBar.setPreferredSize(new Dimension(courseBar.getWidth(), COURSE_BAR_HEIGHT));
		courseBar.addMouseListener(new CourseInfoToggleListener(NEW_EXPAND, NEW_COLLAPSE, courseBar.getBackground(), courseBarHoverColor));

		JLabel courseInfoLbl = new JLabel(" " + searchResult.id + ": " + searchResult.name);
		courseInfoLbl.setForeground(Color.WHITE);
		courseInfoLbl.setFont(Fonts.LUCIDA_MEDIUM);
		courseInfoLbl.setHorizontalAlignment(SwingConstants.LEFT);
		courseBar.add(courseInfoLbl, BorderLayout.WEST);

		JLabel courseInfoToggle = new JLabel();
		courseInfoToggle.setIcon(new ImageIcon(NEW_EXPAND));
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

		
		JTextField crnLbl = new JTextField(" CRN: " + searchResult.crn);
		crnLbl.setEditable(false);
		crnLbl.setForeground(new Color(0, 0, 0));
		crnLbl.setBackground(courseInfoBackgroundColor);
		crnLbl.setBorder(null);
		crnLbl.setFont(Fonts.LUCIDA_SMALL);
		crnLbl.setSelectionColor(Color.WHITE);
		crnLbl.setHorizontalAlignment(SwingConstants.LEFT);
		crnLbl.addMouseListener(TextHoverListener.getInstance());
		courseInfo.add(crnLbl, BorderLayout.NORTH);
		

		JPanel courseDetails = new JPanel();
		courseDetails.setBackground(courseInfoBackgroundColor);
		courseInfo.add(courseDetails);
		BorderLayout courseDetailsBorder = new BorderLayout(0, 0);
		courseDetailsBorder.setVgap(10);
		courseDetails.setLayout(courseDetailsBorder);

		
		JPanel courseDetails2 = new JPanel();
		courseDetails.add(courseDetails2, BorderLayout.NORTH);
		courseDetails2.setLayout(new BorderLayout(0, 0));

		JTextField professorLbl = new JTextField(" Professor: " + searchResult.professor);
		courseDetails2.add(professorLbl, BorderLayout.NORTH);
		professorLbl.setEditable(false);
		professorLbl.setFont(Fonts.LUCIDA_SMALL);
		professorLbl.setForeground(Color.BLACK);
		professorLbl.setBackground(courseInfoBackgroundColor);
		professorLbl.setBorder(null);
		professorLbl.setSelectionColor(Color.WHITE);
		professorLbl.setHorizontalAlignment(SwingConstants.LEFT);
		professorLbl.addMouseListener(TextHoverListener.getInstance());
		

		JPanel courseDescriptionContainer = new JPanel();
		courseDescriptionContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		courseDescriptionContainer.setBackground(courseInfoBackgroundColor);
		courseDescriptionContainer.setLayout(new BoxLayout(courseDescriptionContainer, BoxLayout.Y_AXIS));
		
		JTextArea courseDescription = new JTextArea();
		courseDescription.setLineWrap(true);
		courseDescription.setText(searchResult.description);
		courseDescription.setEditable(false);
		courseDescription.setFont(Fonts.LUCIDA_SMALL);
		courseDescription.setForeground(Color.BLACK);
		courseDescription.setBackground(courseInfoBackgroundColor);
		courseDescription.setSelectionColor(Color.WHITE);
		courseDescription.setWrapStyleWord(true);
		courseDescriptionContainer.add(courseDescription);
		courseDetails2.add(courseDescriptionContainer, BorderLayout.CENTER);
		
		JPanel courseMeetings = new JPanel();
		courseMeetings.setBackground(courseInfoBackgroundColor);
		TitledBorder courseMeetBorder = new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Meeting Times", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0));
		courseMeetBorder.setTitleFont(Fonts.LUCIDA_MEDIUM);
		courseMeetings.setBorder(courseMeetBorder);		
		courseDetails.add(courseMeetings);
		courseMeetings.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel courseDetails3 = new JPanel();
		courseDetails.add(courseDetails3, BorderLayout.SOUTH);
		courseDetails3.setLayout(new BorderLayout(0, 0));

		JPanel courseAttributes = new JPanel();
		courseAttributes.setBackground(courseInfoBackgroundColor);
		TitledBorder courseAttrsBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Attributes", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0));
		courseAttrsBorder.setTitleFont(Fonts.LUCIDA_MEDIUM);
		courseAttributes.setBorder(courseAttrsBorder);
		courseDetails3.add(courseAttributes, BorderLayout.WEST);
		courseAttributes.setLayout(new GridLayout(0, 1, 0, 0));
		courseAttributes.setPreferredSize(new Dimension(courseAttributes.getWidth() + 250, courseAttributes.getHeight()));

		JPanel scheduleOptions = new JPanel();
		scheduleOptions.setBackground(courseInfoBackgroundColor);
		TitledBorder scheduleOptsBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Scheduling", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0));
		scheduleOptsBorder.setTitleFont(Fonts.LUCIDA_MEDIUM);
		scheduleOptions.setBorder(scheduleOptsBorder);
		
		courseDetails3.add(scheduleOptions, BorderLayout.CENTER);

		JButton viewCalendar = new JButton("View Calendar");
		viewCalendar.setMargin(new Insets(10, 5, 10, 5));
		viewCalendar.setFont(Fonts.LUCIDA_SMALL);
		viewCalendar.setOpaque(true);
		viewCalendar.setBorderPainted(false);
		viewCalendar.setBackground(Colors.MEDIUM_YELLOW);
		viewCalendar.setForeground(Color.WHITE);
		viewCalendar.addActionListener(new ActionListener() {
			@Override
		   public void actionPerformed(ActionEvent e) {
			  Schedule schedule = Schedule.getInstance();
		      schedule.setModal(true);
		      schedule.setVisible(true);
		   }
		});
		viewCalendar.addMouseListener(new ButtonHover(viewCalendar.getBackground(), courseBarHoverColor));
		scheduleOptions.add(viewCalendar);

		JButton registerCourse = new JButton("Register For Course");
		registerCourse.setMargin(new Insets(10, 5, 10, 5));
		registerCourse.setFont(Fonts.LUCIDA_SMALL);
		registerCourse.setOpaque(true);
		registerCourse.setBorderPainted(false);
		registerCourse.setBackground(Colors.MEDIUM_GREEN);
		registerCourse.setForeground(Color.WHITE);
		registerCourse.addMouseListener(new ButtonHover(registerCourse.getBackground(), courseBarHoverColor));
		scheduleOptions.add(registerCourse);
		
		for (Day day : Day.values()) {
			JCheckBox dayBox = new JCheckBox();
			dayBox.setDisabledIcon(dayBox.getIcon());
			dayBox.setText(day.toTitleCase());
			dayBox.setEnabled(false);
			dayBox.setForeground(Color.BLACK);
			JLabel meetingTime = new JLabel("---");
			if (searchResult.days.contains(day)) {
				dayBox.setSelected(true);
				meetingTime = new JLabel(searchResult.startTime.format(HOURS_MINS_AM_PM) + " - " + searchResult.endTime.format(HOURS_MINS_AM_PM));
			}
			courseMeetings.add(dayBox);
			courseMeetings.add(meetingTime);
		}
//
//		for (String attr : searchResult.getAttributes()) {
//			JLabel courseAttribute = new JLabel(attr);
//			courseAttributes.add(courseAttribute);
//		}
		
		if (searchResult.isHonors) {
			JLabel honorsAttr = new JLabel();
			honorsAttr.setText("Honors");
			courseAttributes.add(honorsAttr);
		}
		
		return courseInfo;
	}

}
