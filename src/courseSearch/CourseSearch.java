package courseSearch;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import results.ResultsPanel;

public class CourseSearch extends JFrame {
	private static final int SCREEN_WIDTH = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	private JTextField CRNTF;
    private JTextField courseNumberTF;
    private JPanel adminPanel;
    private JPanel attributePanel;
    private JCheckBox cbFri;
    private JCheckBox cbHonors;
    private JCheckBox cbCompCultures;
    private JCheckBox cbMon;
    private JCheckBox cbThu;
    private JCheckBox cbTue;
    private JCheckBox cbWed;
    private JCheckBox cbSat;
    private JButton clearButton;
    private JTextField courseNameTF;
    private JPanel coursePanel;
    private JPanel criteriaPanel;
    private JTextField departmentTF;
    private JComboBox endTimeComboBox;
    private JComboBox numCreditsComboBox;
    private JLabel startTimeLabel;
    private JLabel searchCriteriaLabel;
    private JLabel creditsLabel;
    private JLabel locationLabel;
    private JLabel endTimeLabel;
    private JLabel courseNameLabel;
    private JLabel crnLabel;
    private JLabel departmentLabel;
    private JLabel professorLabel;
    private JLabel semesterLabel;
    private JLabel courseNumberLabel;
    private JPanel timePanel;
    // I don't think this actually gets used
    private JPanel jPanel2;
    private JPanel searchButtonPanel;
    private JPanel meetingPanel;
    private JTextField profTF;
    private JButton searchButton;
    private JComboBox semesterComboBox;
    private JComboBox locationComboBox;
    private JComboBox startTimeComboBox;
    private JSplitPane splitPane;
    private ResultsPanel resultsPanel;
    private JPanel searchPanel;
    
    private javax.swing.JMenu helpMenuItem;
    private javax.swing.JMenu aboutMenuItem;
    private javax.swing.JMenuBar menuBar;
    
    /**
     * Creates new form CourseSearch
     */
    public CourseSearch() {
    	searchPanel = new JPanel();
    	adminPanel = new JPanel();
        profTF = new JTextField();
        professorLabel = new JLabel();
        semesterLabel = new JLabel();
        semesterComboBox = new JComboBox();
        creditsLabel = new JLabel();
        numCreditsComboBox = new JComboBox();
        locationLabel = new JLabel();
        locationComboBox = new JComboBox();
        meetingPanel = new JPanel();
        cbMon = new JCheckBox("M");
        cbTue = new JCheckBox("T");
        cbWed = new JCheckBox("W");
        cbThu = new JCheckBox("R");
        cbFri = new JCheckBox("F");
        cbSat = new JCheckBox("S");
        timePanel = new JPanel();
        startTimeComboBox = new JComboBox();
        endTimeComboBox = new JComboBox();
        startTimeLabel = new JLabel();
        endTimeLabel = new JLabel();
        coursePanel = new JPanel();
        courseNameLabel = new JLabel();
        courseNameTF = new JTextField();
        crnLabel = new JLabel();
        CRNTF = new JTextField();        
        departmentLabel = new JLabel();
        departmentTF = new JTextField();
        courseNumberLabel = new JLabel();
        courseNumberTF = new JTextField();  
        attributePanel = new JPanel();
        menuBar = new javax.swing.JMenuBar();
        helpMenuItem = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenu();
        cbHonors = new JCheckBox();
        cbCompCultures = new JCheckBox();
        criteriaPanel = new JPanel();
        searchCriteriaLabel = new JLabel();
        searchButtonPanel = new JPanel();
        jPanel2 = new JPanel();
        searchButton = new JButton();
        
        setWindowProperties();
        initElements();
        setLayouts();

        this.resultsPanel = new ResultsPanel("");
        this.setResultsPanelDimensions();
        this.searchPanel.setMinimumSize(new Dimension(0, 0));
        this.searchPanel.setPreferredSize(new Dimension(SCREEN_WIDTH / 4, 800));
        this.searchPanel.setBorder(new EmptyBorder(10, 0, 0, 10));
        
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, resultsPanel.getResultsPanel());
        
        this.setSplitPaneOptions();
        
        getContentPane().add(splitPane);
        getRootPane().setDefaultButton(searchButton);

        helpMenuItem.setText("Help");
        menuBar.add(helpMenuItem);

        aboutMenuItem.setText("About");
        menuBar.add(aboutMenuItem);

        setJMenuBar(menuBar);

        pack();
    }
    
    private void setSplitPaneOptions() {
    	this.splitPane.setBackground(Color.BLACK);
        this.splitPane.setContinuousLayout(true);
        this.splitPane.setDividerSize(7);
        this.splitPane.setBorder(new EmptyBorder(0,0,0,0));
        this.splitPane.setDividerLocation(SCREEN_WIDTH / 2);
        this.splitPane.setResizeWeight(1);
    }
    
    private void setResultsPanelDimensions() {
        this.resultsPanel.setMinimumSize(new Dimension(0, 0));
        this.resultsPanel.setPreferredSize(new Dimension(700, 800));
        this.resultsPanel.setBorder(new EmptyBorder(10, 10, 0, 0));
    }
        
    public void runSearch() {
    	String queryString = "WHERE ";
    	
    	//Get Time
    	String[] startTimeStr = startTimeComboBox.getSelectedItem().toString().split(" ");
    	Integer timeNum = Integer.parseInt(startTimeStr[0]);
    	if (timeNum.equals(12)) {
    		timeNum = 0;
    	}
    	Boolean isPm = startTimeStr[1].equals("PM");
    	if (isPm) {
    		timeNum += 12;
    	}
    	queryString += "starttimeHour >= " + timeNum;
    	startTimeStr = endTimeComboBox.getSelectedItem().toString().split(" ");
    	timeNum = Integer.parseInt(startTimeStr[0]);
    	isPm = startTimeStr[1].equals("PM");
    	if (isPm) {
    		timeNum += 12;
    	}
    	queryString += " AND endtimeHour <= " + timeNum;
    	if (!courseNameTF.getText().equals("")) {
    		queryString += " AND lower(name) LIKE \'%" + courseNameTF.getText().toLowerCase() + "%\' " ;
    	}
    	if (!CRNTF.getText().equals("")) {
    		queryString += " AND crn = \'" + CRNTF.getText() + "\'";
    	}
    	if (!courseNumberTF.getText().equals("")) {
    		queryString += " AND id LIKE \'%" + courseNumberTF.getText() + "%\'";
    	}
    	if (cbMon.isSelected()) {
    		queryString += " AND monday = true";
    	}
    	if (cbMon.isSelected()) {
    		queryString += " AND monday = true";
    	}
    	if (cbTue.isSelected()) {
    		queryString += " AND tuesday = true";
    	}
    	if (cbWed.isSelected()) {
    		queryString += " AND wednesday = true";
    	}
    	if (cbThu.isSelected()) {
    		queryString += " AND thursday = true";
    	}
    	if (cbFri.isSelected()) {
    		queryString += " AND friday = true";
    	}
    	if (cbSat.isSelected()) {
    		queryString += " AND saturday = true";
    	}
    	if (!locationComboBox.getSelectedItem().toString().equals("")) {
    		queryString += " AND location = \'" + locationComboBox.getSelectedItem().toString() + "\'";
    	}

    	this.splitPane.remove(resultsPanel);
    	this.resultsPanel = new ResultsPanel(queryString);
    	this.setResultsPanelDimensions();
    	this.splitPane.add(resultsPanel);
        this.setSplitPaneOptions();
    }
    
//    private void setCourseSearchBindings(JComponent parent) {
//    	for (Component component : parent.getComponents()) {
//    		if (component instanceof JComponent) {
//	    		JComponent child = (JComponent) component;
//	    		if (child.getComponents().length > 0) {
//	    			child.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
//	    			child.getActionMap().put("pressed", new SearchAction(this));
//	    			this.setCourseSearchBindings(child);
//	    			return;
//	    		}
//	    		else if (child instanceof JComboBox) {
//	    			JComboBox childCB = (JComboBox) child;
//    			}
//    			else { 
//    				child.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
//	    		}
//    	        child.getActionMap().put("pressed", new SearchAction(this));
//    		}
//    	}
//    }

    private void setWindowProperties() {
    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Course Search");
        setMaximumSize(new java.awt.Dimension(1900, 1280));
        setPreferredSize(new java.awt.Dimension(SCREEN_WIDTH, 800));
    }
    
    private void initElements() {
        
        
        java.awt.GridBagConstraints g = new java.awt.GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        
        JPanel enclosingPanel = new JPanel();
        
        enclosingPanel.setPreferredSize(new java.awt.Dimension(SCREEN_WIDTH * 2 / 3, 800));
        enclosingPanel.setLayout(new GridBagLayout());
    	clearButton = new javax.swing.JButton();
    	adminPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrative"));
        professorLabel.setText("Professor:");
        semesterLabel.setText("Semester:");
        semesterComboBox.setModel(new DefaultComboBoxModel(new String[] {"Any", "Spring 2016", "Fall 2015"}));
        creditsLabel.setText("Credits:");
        numCreditsComboBox.setModel(new DefaultComboBoxModel(new String[] {"Any", "0", "1", "4", " " }));
        locationLabel.setText("Location:");
        locationComboBox.setModel(new DefaultComboBoxModel(new String[] { "Any", "Boston", "Seattle", "Charlotte", "Other locations" }));

        startTimeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Any", "12 AM", "1 AM", "2 AM", "3 AM", "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM ", "5 PM", "6 PM", "7 PM ", "8 PM", "9 PM", "10 PM", "11 PM" }));
        endTimeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Any", "12 AM", "1 AM", "2 AM", "3 AM", "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM ", "5 PM", "6 PM", "7 PM ", "8 PM", "9 PM", "10 PM", "11 PM" }));
        startTimeLabel.setText("Start at or after:");
        endTimeLabel.setText("End at or before:");
        courseNameLabel.setText("Name of Course:");
        crnLabel.setText("CRN:");
        departmentLabel.setText("Dept. Header:");
        courseNumberLabel.setText("Course Number:");
        
        g.gridx = 0;
        g.gridy = 0;
        enclosingPanel.add(adminPanel, g);
        
        adminPanel.getAccessibleContext().setAccessibleName("Administrative");
        meetingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Meeting Times"));
        timePanel.setBorder(BorderFactory.createTitledBorder("Time of Day"));
        
        g.gridx = 1;
        g.gridy = 0;
        enclosingPanel.add(meetingPanel, g);
        
        coursePanel.setBorder(BorderFactory.createTitledBorder("Course"));
        
        g.gridx = 0;
        g.gridy = 1;
        enclosingPanel.add(coursePanel, g);        
        attributePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Attributes and Distinctions"));
        cbHonors.setText("Search for honors (HON) classes");
        cbCompCultures.setText("Search for Comparative Cultures classes");
        
        g.gridx = 0;
        g.gridy = 2;
        //enclosingPanel.add(criteriaPanel, g);
        
        g.gridx = 1;
        g.gridy = 1;
        //enclosingPanel.add(attributePanel, g);
        
        criteriaPanel.setBorder(BorderFactory.createTitledBorder("Your Search Criteria"));
        searchCriteriaLabel.setText("Placeholder");
        
        searchButton.setText("Search!");
        clearButton.setText("Clear criteria");
        
        g.gridx = 1;
        g.gridy = 1;
        //enclosingPanel.add(searchButtonPanel, g);
        
        g.gridx = 1;
        g.gridy = 1;
        enclosingPanel.add(jPanel2, g);
        
        searchButton.addActionListener(new SearchButtonListener(this));
        searchPanel.add(enclosingPanel);
        
    }
    
    private void setLayouts() {
        
        searchPanel.setLayout(new GridLayout());
        GroupLayout adminPanelLayout = new GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addComponent(professorLabel)
                        .addGap(38, 38, 38)
                        .addComponent(profTF))
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(semesterLabel)
                            .addComponent(creditsLabel)
                            .addComponent(locationLabel))
                        .addGap(38, 38, 38)
                        .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(numCreditsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(semesterComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(275, Short.MAX_VALUE))
        );
        adminPanelLayout.setVerticalGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(profTF)
                    .addComponent(professorLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(semesterComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(semesterLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(creditsLabel)
                    .addComponent(numCreditsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

//        cbWed.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                cbWedActionPerformed(evt);
//            }
//        });
//
//        
//        cbThu.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                cbThuActionPerformed(evt);
//            }
//        });
        
        GroupLayout jPanel1Layout = new GroupLayout(timePanel);
        timePanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(startTimeLabel)
                    .addComponent(endTimeLabel))
                .addPreferredGap(ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(startTimeComboBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(endTimeComboBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(startTimeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(startTimeLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(endTimeLabel)
                    .addComponent(endTimeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout meetingPanelLayout = new GroupLayout(meetingPanel);
        meetingPanel.setLayout(meetingPanelLayout);
        meetingPanelLayout.setHorizontalGroup(
            meetingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(meetingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meetingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cbFri)
                    .addComponent(cbMon)
                    .addComponent(cbTue)
                    .addComponent(cbWed)
                    .addComponent(cbThu)
                    .addComponent(cbSat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        meetingPanelLayout.setVerticalGroup(
            meetingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(meetingPanelLayout.createSequentialGroup()
                .addGroup(meetingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(meetingPanelLayout.createSequentialGroup()
                        .addComponent(cbMon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbWed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbThu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSat)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(meetingPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(timePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        GroupLayout coursePanelLayout = new GroupLayout(coursePanel);
        coursePanel.setLayout(coursePanelLayout);
        coursePanelLayout.setHorizontalGroup(
            coursePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(coursePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(courseNumberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(courseNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, coursePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(crnLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(departmentLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(CRNTF, GroupLayout.Alignment.TRAILING)
                    .addComponent(courseNameTF)
                    .addComponent(departmentTF, 0, 404, Short.MAX_VALUE)
                    .addComponent(courseNumberTF, GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        coursePanelLayout.setVerticalGroup(
            coursePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(coursePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(courseNameLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(departmentLabel)
                    .addComponent(departmentTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(crnLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addComponent(CRNTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(courseNumberTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseNumberLabel))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        GroupLayout attributePanelLayout = new GroupLayout(attributePanel);
        attributePanel.setLayout(attributePanelLayout);
        attributePanel.setLayout(attributePanelLayout);
        attributePanelLayout.setHorizontalGroup(
            attributePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attributePanelLayout.createSequentialGroup()
                .addGroup(attributePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbHonors)
                    .addComponent(cbCompCultures))
                .addGap(0, 56, Short.MAX_VALUE))
        );
        attributePanelLayout.setVerticalGroup(
            attributePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attributePanelLayout.createSequentialGroup()
                .addComponent(cbHonors)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCompCultures)
                .addGap(0, 85, Short.MAX_VALUE))
        );

        GroupLayout criteriaPanelLayout = new GroupLayout(criteriaPanel);
        criteriaPanel.setLayout(criteriaPanelLayout);
        criteriaPanelLayout.setHorizontalGroup(
            criteriaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(criteriaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchCriteriaLabel)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        criteriaPanelLayout.setVerticalGroup(
            criteriaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(criteriaPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(searchCriteriaLabel)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        GroupLayout jPanel3Layout = new GroupLayout(searchButtonPanel);
        searchButtonPanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 325, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(clearButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	
        try {
        	UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CourseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseSearch().setVisible(true);
            }
        });
    }
    
}