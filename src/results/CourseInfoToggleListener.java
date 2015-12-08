package results;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A simple listener to expand/collapse details of a clicked search result.
 * @author Jesse
 *
 */
public class CourseInfoToggleListener extends MouseAdapter {
	private Image expand;
	private Image collapse;

	CourseInfoToggleListener(Image expand, Image collapse) {
		this.expand = expand;
		this.collapse = collapse;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			Component source = (Component) e.getSource();
			JPanel courseBar = new JPanel();
			JTextArea courseCode = new JTextArea();
			JTextArea courseName = new JTextArea();
			
			if (source.getName().equals("courseBar")) {
				courseBar = (JPanel) source;
				JPanel courseNameContainer = (JPanel) courseBar.getComponent(0);
				courseCode = (JTextArea) courseNameContainer.getComponent(0);
				courseName = (JTextArea) courseNameContainer.getComponent(1);
			}
			else if (source.getName().equals("courseCode")) {
				courseCode = (JTextArea) source;
				JPanel courseNameContainer = (JPanel) courseCode.getParent();
				courseName = (JTextArea) courseNameContainer.getComponent(1);
				courseBar = (JPanel) courseNameContainer.getParent(); 
			}
			else if (source.getName().equals("courseName")) {
				courseName = (JTextArea) source;
				JPanel courseNameContainer = (JPanel) courseName.getParent();
				courseCode = (JTextArea) courseNameContainer.getComponent(0);
				courseBar = (JPanel) courseNameContainer.getParent();
			}
			
			try {
				JPanel course = (JPanel) courseBar.getParent();
				JLabel courseInfoToggle = (JLabel) courseBar.getComponent(1);
				JPanel courseInfo = (JPanel) course.getComponent(1);
				
				boolean visibilityToggle = courseInfo.isVisible() ? false : true;
				Image imgToggle = courseInfo.isVisible() ? this.expand : this.collapse;
				courseInfoToggle.setIcon(new ImageIcon(imgToggle));
				courseInfo.setVisible(visibilityToggle);
			} catch (Exception exc) {
				System.out.println(exc.getMessage());
			}
		}
	}
}
