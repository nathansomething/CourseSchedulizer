package courseSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearCriteriaListener implements ActionListener {
	private CourseSearch searchPanel;
	
	ClearCriteriaListener(CourseSearch searchPanel) {
		this.searchPanel = searchPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Meeting Times Panel
		this.searchPanel.cbMon.setSelected(false);
		this.searchPanel.cbTue.setSelected(false);
		this.searchPanel.cbWed.setSelected(false);
		this.searchPanel.cbThu.setSelected(false);
		this.searchPanel.cbFri.setSelected(false);
		
		this.searchPanel.startTimeComboBox.setSelectedIndex(0);
		this.searchPanel.endTimeComboBox.setSelectedIndex(0);
		
		// Admin Panel
		this.searchPanel.profTF.setText("");
		this.searchPanel.semesterComboBox.setSelectedIndex(0);
		this.searchPanel.locationComboBox.setSelectedIndex(0);
		this.searchPanel.numCreditsComboBox.setSelectedIndex(0);
		
		// Course Panel
		this.searchPanel.courseNameTF.setText("");
		this.searchPanel.departmentTF.setText("");
		this.searchPanel.courseNumberTF.setText("");
		this.searchPanel.CRNTF.setText("");
	}

}
