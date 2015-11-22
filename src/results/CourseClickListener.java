package results;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A simple listener to expand/collapse details of a clicked search result.
 * @author Jesse
 *
 */
public class CourseClickListener extends MouseAdapter {
	Image expand;
	Image collapse;

	CourseClickListener(Image expand, Image collapse) {
		this.expand = expand;
		this.collapse = collapse;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			JPanel course = (JPanel) e.getSource();
			JPanel courseBar = (JPanel) course.getComponent(0);
			JPanel courseInfo = (JPanel) course.getComponent(1);
			
			JLabel courseInfoToggle = (JLabel) courseBar.getComponent(1);
			
			boolean visibilityToggle = courseInfo.isVisible() ? false : true;
			Image imgToggle = courseInfo.isVisible() ? this.expand : this.collapse;
			courseInfoToggle.setIcon(new ImageIcon(imgToggle));
			courseInfo.setVisible(visibilityToggle);
		}
	}
}
