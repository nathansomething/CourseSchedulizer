package courseSearch;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import results.ResultsPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.InputMethodEvent;

public class CourseSearch extends JFrame {

	private JTextField CRNTF;
    private JTextField courseNumberTF;
    private JPanel adminPanel;
    private JPanel attributePanel;
    private JCheckBox cbFri;
    private JCheckBox cbHonors;
    private JCheckBox cbMon;
    private JCheckBox cbThu;
    private JCheckBox cbTue;
    private JCheckBox cbWed;
    private JButton clearButton;
    private JComboBox courseAttributeComboBox;
    private JTextField courseNameTF;
    private JPanel coursePanel;
    private JPanel criteriaPanel;
    private JComboBox departmentTF;
    private JComboBox endTimeComboBox;
    private JComboBox jComboBox1;
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
    private JLabel courseAttributeLabel;
    private JPanel timePanel;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel meetingPanel;
    private JComboBox profComboBox;
    private JButton searchButton;
    private JComboBox semesterComboBox;
    private JComboBox locationComboBox;
    private JComboBox startTimeComboBox;
    private JSplitPane splitPane;
    private ResultsPanel resultsPanel;
    private JPanel awesomeCourseSearchPanel;
    
    /**
     * Creates new form CourseSearch
     */
    public CourseSearch() {
    	adminPanel = new JPanel();
    	awesomeCourseSearchPanel = new JPanel();
        profComboBox = new JComboBox();
        professorLabel = new JLabel();
        semesterLabel = new JLabel();
        semesterComboBox = new JComboBox();
        creditsLabel = new JLabel();
        jComboBox1 = new JComboBox();
        locationLabel = new JLabel();
        locationComboBox = new JComboBox();
        meetingPanel = new JPanel();
        cbMon = new JCheckBox();
        cbTue = new JCheckBox();
        cbWed = new JCheckBox();
        cbThu = new JCheckBox();
        cbFri = new JCheckBox();
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
        departmentTF = new JComboBox();
        courseNumberLabel = new JLabel();
        courseNumberTF = new JTextField();   
        attributePanel = new JPanel();
        courseAttributeLabel = new JLabel();
        courseAttributeComboBox = new JComboBox();
        cbHonors = new JCheckBox();
        criteriaPanel = new JPanel();
        searchCriteriaLabel = new JLabel();
        jPanel3 = new JPanel();
        jPanel2 = new JPanel();
        searchButton = new JButton();
        
        setWindowProperties();
        initElements();
        setLayouts();
        
        this.resultsPanel = new ResultsPanel("");
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, awesomeCourseSearchPanel, resultsPanel.getResultsPanel());
        getContentPane().add(splitPane);

        pack();
        
        searchButton.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String queryString = "";
        		if (!courseNameTF.getText().equals("")) {
        			queryString += "name LIKE \'%" + courseNameTF.getText() + "%\'";
        		}
        		if (!CRNTF.getText().equals("")) {
        			if (!queryString.equals("")) {
        				queryString += " AND ";
        			}
        			queryString += "crn = \'" + CRNTF.getText() + "\'";
        		}
        		if (!courseNumberTF.getText().equals("")) {
        			if (!queryString.equals("")) {
        				queryString += " AND ";
        			}
        			queryString += "id LIKE \'%" + courseNumberTF.getText() + "%\'";
        		}
        		if (!queryString.equals("")) {
        			queryString = "WHERE " + queryString;
        		}
        		
//        		JOptionPane.showMessageDialog(null, "Mouse Exit");
        		splitPane.remove(resultsPanel);
        		resultsPanel = new ResultsPanel(queryString);
                splitPane.add(resultsPanel);
//                getContentPane().add(splitPane);
//        		
//                splitPane.revalidate();
//                splitPane.repaint();
//                setVisible(true);
        	}
        	
        });
    }

    private void setWindowProperties() {
    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Course Search");
        setMaximumSize(new java.awt.Dimension(1900, 1280));
        setPreferredSize(new java.awt.Dimension(1400, 800));
    }
    
    private void initElements() {
    	clearButton = new javax.swing.JButton();
    	adminPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrative"));
        profComboBox.setModel(new DefaultComboBoxModel(new String[] { "Any", "Item 1", "Item 2", "Item 3", "Item 4" }));
        professorLabel.setText("Professor:");
        semesterLabel.setText("Semester:");
        semesterComboBox.setModel(new DefaultComboBoxModel(new String[] { "Spring 2016", "Fall 2015", "So on and so forth", "Item 3", "Item 4" }));
        creditsLabel.setText("Credits:");
        jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "4", " " }));
        locationLabel.setText("Location:");
        locationComboBox.setModel(new DefaultComboBoxModel(new String[] { "Boston", "Seattle", "Charlotte", "Other locations" }));
        cbMon.setText("M");
        cbTue.setText("T");
        cbWed.setText("W");
        cbThu.setText("R");
        cbFri.setText("F");
        startTimeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 AM", "1 AM", "2 AM", "3 AM", "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM ", "5 PM", "6 PM", "7 PM ", "8 PM", "9 PM", "10 PM", "11 PM" }));
        endTimeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12 AM", "1 AM", "2 AM", "3 AM", "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM ", "5 PM", "6 PM", "7 PM ", "8 PM", "9 PM", "10 PM", "11 PM" }));
        startTimeLabel.setText("Start at or after:");
        endTimeLabel.setText("End at or before:");
        courseNameLabel.setText("Name of Course:");
        crnLabel.setText("CRN:");
        departmentLabel.setText("Department:");
        departmentTF.setModel(new DefaultComboBoxModel(new String[] { "Bouvé College of Health Sciences", "College of Arts, Media and Design", "College of Computer and Information Science", "College of Engineering", "College of Professional Studies", "College of Science", "College of Social Sciences and Humanities", "D'Amore-McKim School of Business", "Program for Undeclared Students", "School of Law" }));
        courseNumberLabel.setText("Course Number:");
        awesomeCourseSearchPanel.add(adminPanel);
        adminPanel.getAccessibleContext().setAccessibleName("Administrative");
        meetingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Meeting Times"));
        timePanel.setBorder(BorderFactory.createTitledBorder("Time of Day"));
        awesomeCourseSearchPanel.add(meetingPanel);
        coursePanel.setBorder(BorderFactory.createTitledBorder("Course"));
        awesomeCourseSearchPanel.add(coursePanel);
        attributePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Attributes and Distinctions"));
        courseAttributeLabel.setText("Course Attribute:");
        courseAttributeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Any", "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbHonors.setText("Search for honors (HON) classes");
        awesomeCourseSearchPanel.add(criteriaPanel);
        awesomeCourseSearchPanel.add(attributePanel);
        criteriaPanel.setBorder(BorderFactory.createTitledBorder("Your Search Criteria"));
        searchCriteriaLabel.setText("In the final product your search criteria will appear here");
        awesomeCourseSearchPanel.add(jPanel3);
        searchButton.setText("Search!");
        clearButton.setText("Clear criteria");
        awesomeCourseSearchPanel.add(jPanel2);
        
        
    }
    
    private void setLayouts() {
        
        awesomeCourseSearchPanel.setLayout(new GridLayout(4, 2));
        GroupLayout adminPanelLayout = new GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addComponent(professorLabel)
                        .addGap(38, 38, 38)
                        .addComponent(profComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                    .addGroup(adminPanelLayout.createSequentialGroup()
                        .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(semesterLabel)
                            .addComponent(creditsLabel)
                            .addComponent(locationLabel))
                        .addGap(38, 38, 38)
                        .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(semesterComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(275, Short.MAX_VALUE))
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(profComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(cbThu))
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
        attributePanelLayout.setHorizontalGroup(
            attributePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(attributePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(courseAttributeLabel)
                .addGap(18, 18, 18)
                .addComponent(courseAttributeComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
            .addGroup(attributePanelLayout.createSequentialGroup()
                .addComponent(cbHonors)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        attributePanelLayout.setVerticalGroup(
            attributePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(attributePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(attributePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(courseAttributeLabel)
                    .addComponent(courseAttributeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbHonors)
                .addContainerGap(97, Short.MAX_VALUE))
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

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
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