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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import courseSearch.CourseSearch;
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

	public static final DateTimeFormatter HOURS_MINS_AM_PM = DateTimeFormatter.ofPattern("h:mm a");
	
	private static Color courseBarHoverColor = Colors.MEDIUM_ORANGE;
	private static Color courseBarBackgroundColor = Colors.DARK_BLUE;
	private static Color courseInfoBackgroundColor = Colors.LIGHT_BLUE;
	private String query;
        private ArrayList<Course> registeredCourses;
	
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
		
		ScrollablePanel scrollablePanel = new ScrollablePanel();
		scrollablePanel.setLayout(new BorderLayout(0,0));
		scrollPane.add(scrollablePanel);
		
		JPanel courseList = new JPanel();
		courseList.setLayout(new BoxLayout(courseList, BoxLayout.Y_AXIS));
		Box coursesBox = Box.createVerticalBox(); 
		
		for (Course course : courses) {
			JPanel coursePanel = this.createCoursePanel(course);
			coursesBox.add(coursePanel);
			coursesBox.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		coursesBox.add(Box.createVerticalGlue());
		courseList.add(coursesBox);
		scrollablePanel.add(courseList, BorderLayout.CENTER);
		scrollPane.setViewportView(scrollablePanel);
	}
        
	public ResultsPanel getResultsPanel() {
		return this;
	}
	
	public JScrollPane getScrollPane() {
		return (JScrollPane) this.getComponent(0);
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
		courseBar.setMinimumSize(new Dimension(0, 0));
		courseBar.setName("courseBar");
		courseBar.setBackground(courseBarBackgroundColor);
		courseBar.setLayout(new BorderLayout(0,0));
		courseBar.setBorder(new EmptyBorder(20, 0, 20, 0));
		courseBar.addMouseListener(new CourseInfoToggleListener(NEW_EXPAND, NEW_COLLAPSE, courseBar.getBackground(), courseBarHoverColor));


		JPanel courseNameContainer = new JPanel();
		courseNameContainer.setBackground(courseBarBackgroundColor);
		courseNameContainer.setBorder(null);
		courseNameContainer.setLayout(new BorderLayout(0,0));
				
		JTextArea courseCode = new JTextArea();
		courseCode.setText(" " + searchResult.id + ": ");
		courseCode.setFont(Fonts.LUCIDA_MEDIUM);
		courseCode.setBackground(courseBarBackgroundColor);
		courseCode.setForeground(Color.WHITE);
		courseCode.setEditable(false);;
		courseCode.setBorder(new EmptyBorder(9, 0, 0, 0));
		courseNameContainer.add(courseCode, BorderLayout.WEST);
		
		JTextArea courseInfoLbl = new JTextArea();
		courseInfoLbl.setLineWrap(true);
		courseInfoLbl.setText(searchResult.name);
		courseInfoLbl.setEditable(false);
		courseInfoLbl.setWrapStyleWord(true);
		courseInfoLbl.setBackground(courseBarBackgroundColor);
		courseInfoLbl.setForeground(Color.WHITE);
		courseInfoLbl.setFont(Fonts.LUCIDA_MEDIUM);
		courseInfoLbl.setBorder(new EmptyBorder(9, 0, 0, 0));
		courseNameContainer.add(courseInfoLbl, BorderLayout.CENTER);
		
		JLabel courseInfoToggle = new JLabel();
		courseInfoToggle.setIcon(new ImageIcon(NEW_EXPAND));
		courseInfoToggle.setName("courseInfoToggle_" + searchResult.id);
		
		courseBar.add(courseNameContainer, BorderLayout.CENTER);
		courseBar.add(courseInfoToggle, BorderLayout.EAST);
		return courseBar;
	}
	
	private JPanel createCourseInfo(Course searchResult) {
		JPanel courseInfo = new JPanel();
		courseInfo.setName("courseInfo");
		courseInfo.setVisible(false);
		courseInfo.setBackground(courseInfoBackgroundColor);
		courseInfo.setLayout(new BorderLayout(0, 0));

		
		JTextArea crnLbl = new JTextArea(" CRN: " + searchResult.crn);
		crnLbl.setEditable(false);
		crnLbl.setForeground(new Color(0, 0, 0));
		crnLbl.setBackground(courseInfoBackgroundColor);
		crnLbl.setBorder(null);
		crnLbl.setFont(Fonts.LUCIDA_SMALL);
		crnLbl.setSelectionColor(Color.WHITE);
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

		JTextArea professorLbl = new JTextArea(" Professor: " + searchResult.professor);
		courseDetails2.add(professorLbl, BorderLayout.NORTH);
		professorLbl.setEditable(false);
		professorLbl.setFont(Fonts.LUCIDA_SMALL);
		professorLbl.setForeground(Color.BLACK);
		professorLbl.setBackground(courseInfoBackgroundColor);
		professorLbl.setBorder(null);
		professorLbl.setSelectionColor(Color.WHITE);
		professorLbl.addMouseListener(TextHoverListener.getInstance());
		
		JTextArea courseDescription = new JTextArea();
		courseDescription.setLineWrap(true);
		courseDescription.setText(searchResult.description);
		courseDescription.setEditable(false);
		courseDescription.setFont(Fonts.LUCIDA_SMALL);
		courseDescription.setForeground(Color.BLACK);
		courseDescription.setBackground(courseInfoBackgroundColor);
		courseDescription.setSelectionColor(Color.WHITE);
		courseDescription.setWrapStyleWord(true);
		courseDescription.setBorder(new EmptyBorder(15, 5, 20, 5));
		courseDescription.addMouseListener(TextHoverListener.getInstance());
		courseDetails2.add(courseDescription, BorderLayout.CENTER);
		
		JPanel courseMeetings = new JPanel();
		courseMeetings.setBackground(courseInfoBackgroundColor);
		TitledBorder courseMeetBorder = new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Meeting Times", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0));
		courseMeetBorder.setTitleFont(Fonts.LUCIDA_MEDIUM);
		courseMeetings.setBorder(new CompoundBorder(courseMeetBorder, new EmptyBorder(10,10,10,10)));
		courseDetails.add(courseMeetings);
		courseMeetings.setLayout(new GridLayout(0, 2, 0, 5));
		

		JPanel courseDetails3Container = new JPanel();
		courseDetails3Container.setLayout(new BorderLayout(0, 0));
		JPanel courseDetails3 = new JPanel();
		courseDetails3.setLayout(new BorderLayout(0, 0));

		JPanel courseAttributes = new JPanel();
		courseAttributes.setBackground(courseInfoBackgroundColor);
		TitledBorder courseAttrsBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Attributes", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0));
		courseAttrsBorder.setTitleFont(Fonts.LUCIDA_MEDIUM);
		courseAttributes.setBorder(new CompoundBorder(courseAttrsBorder, new EmptyBorder(10,10,10,10)));
		courseDetails3.add(courseAttributes, BorderLayout.CENTER);

		JPanel scheduleOptions = new JPanel();
		scheduleOptions.setLayout(new GridLayout(0, 3, 10, 0));
		scheduleOptions.setBackground(courseInfoBackgroundColor);
		TitledBorder scheduleOptsBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Scheduling", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0));
		scheduleOptsBorder.setTitleFont(Fonts.LUCIDA_MEDIUM);
		scheduleOptions.setBorder(new CompoundBorder(scheduleOptsBorder, new EmptyBorder(0, 20, 0, 20)));
		courseDetails3.add(scheduleOptions, BorderLayout.EAST);

		JButton viewSchedule = new JButton("View Schedule");
		viewSchedule.setMargin(new Insets(10, 5, 10, 5));
		viewSchedule.setFont(Fonts.LUCIDA_SMALL);
		viewSchedule.setOpaque(true);
		viewSchedule.setBorderPainted(false);
		viewSchedule.setBackground(Colors.MEDIUM_YELLOW);
		viewSchedule.setForeground(Color.WHITE);
		viewSchedule.addActionListener(new ActionListener() {
			@Override
		   public void actionPerformed(ActionEvent e) {
				scheduleButton(e);
		   }
		});
		viewSchedule.addMouseListener(new ButtonHover(viewSchedule.getBackground(), courseBarHoverColor));
		scheduleOptions.add(viewSchedule);

		JButton registerCourse = new JButton("Register For Course");
		registerCourse.setMargin(new Insets(10, 5, 10, 5));
		registerCourse.setFont(Fonts.LUCIDA_SMALL);
		registerCourse.setOpaque(true);
		registerCourse.setBorderPainted(false);
		registerCourse.setBackground(Colors.MEDIUM_GREEN);
		registerCourse.setForeground(Color.WHITE);
		registerCourse.addMouseListener(new ButtonHover(registerCourse.getBackground(), courseBarHoverColor));
		scheduleOptions.add(registerCourse);
		registerCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerButton(e, searchResult);
			}
		});
		
		JButton removeCourse = new JButton("Remove Course");
		removeCourse.setMargin(new Insets(10, 5, 10, 5));
		removeCourse.setFont(Fonts.LUCIDA_SMALL);
		removeCourse.setOpaque(true);
		removeCourse.setBorderPainted(false);
		removeCourse.setBackground(Colors.MEDIUM_RED);
		removeCourse.setForeground(Color.WHITE);
		removeCourse.addMouseListener(new ButtonHover(removeCourse.getBackground(), courseBarHoverColor));
		scheduleOptions.add(removeCourse);
		removeCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeButton(e, searchResult);
			}
		});
		
		
		courseDetails3Container.add(courseDetails3, BorderLayout.CENTER);
		courseDetails.add(courseDetails3Container, BorderLayout.SOUTH);
		
		for (Day day : Day.values()) {
			JTextArea dayBox = new JTextArea();
			dayBox.setText(day.toTitleCase());
			dayBox.setForeground(Color.BLACK);
			dayBox.setBorder(null);
			dayBox.setBackground(courseInfoBackgroundColor);
			dayBox.setEditable(false);
			JLabel meetingTime = new JLabel("---");
			if (searchResult.days.contains(day)) {
				meetingTime = new JLabel(searchResult.startTime.format(HOURS_MINS_AM_PM) + " - " + searchResult.endTime.format(HOURS_MINS_AM_PM));
			}
			courseMeetings.add(dayBox);
			courseMeetings.add(meetingTime);
		}
		
		if (!searchResult.isHonors) {
			JLabel honorsAttr = new JLabel();
			honorsAttr.setText("Honors");
			courseAttributes.add(honorsAttr);
		}
		
		return courseInfo;
	}
	

    private void registerButton(java.awt.event.ActionEvent evt, Course c){
    	if (CourseSearch.registeredCourses.contains(c)) {
    		JOptionPane.showMessageDialog(this, "You've already registered for " + c.id + " (CRN: " + c.crn + ").");
    		return;
    	}
        CourseSearch.registeredCourses.add(c);
        JOptionPane.showMessageDialog(this, c.id + " (CRN: " + c.crn + ") Successfully Registered.");
    }
    
    private void removeButton(java.awt.event.ActionEvent evt, Course c){
    	if (CourseSearch.registeredCourses.contains(c)) {
            CourseSearch.registeredCourses.remove(c);
    		JOptionPane.showMessageDialog(this, c.id + " (CRN: " + c.crn + ") was successfully removed from your schedule.");
    		return;
    	}
        JOptionPane.showMessageDialog(this, c.id + " (CRN: " + c.crn + ") is not yet in your schedule.");
    }
    
    private void scheduleButton(java.awt.event.ActionEvent evt){
        String message = "Registered Courses : ";
        for (Course c: CourseSearch.registeredCourses){
            message = message + "\n" + c.otherToString();
        }
        message += (CourseSearch.registeredCourses.isEmpty()) ?  "\n----------------------------------\nNo courses added to schedule yet." : "";
        JOptionPane.showMessageDialog(this, message);
    }

}
