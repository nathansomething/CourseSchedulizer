package courseSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButtonListener implements ActionListener {
	CourseSearch courseSearchPanel;
	
	SearchButtonListener(CourseSearch courseSearchPanel) {
		this.courseSearchPanel = courseSearchPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.courseSearchPanel.runSearch();
	}
}
