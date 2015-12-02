package results;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A simple listener to expand/collapse details of a clicked search result.
 * @author Jesse
 *
 */
public class CourseInfoToggleListener extends MouseAdapter {
	private Image expand;
	private Image collapse;
	private Color unhoveredColor;
	private Color hoverColor;

	CourseInfoToggleListener(Image expand, Image collapse, Color unhoveredColor, Color hoverColor) {
		this.expand = expand;
		this.collapse = collapse;
		this.unhoveredColor = unhoveredColor;
		this.hoverColor = hoverColor;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			JPanel courseBar = (JPanel) e.getSource();
			JPanel course = (JPanel) courseBar.getParent();
			JLabel courseInfoToggle = (JLabel) courseBar.getComponent(1);
			JPanel courseInfo = (JPanel) course.getComponent(1);
			
			boolean visibilityToggle = courseInfo.isVisible() ? false : true;
			Image imgToggle = courseInfo.isVisible() ? this.expand : this.collapse;
			courseInfoToggle.setIcon(new ImageIcon(imgToggle));
			courseInfo.setVisible(visibilityToggle);
		}
	}
	
	// Bug: doesn't work after clicking on 'View calendar' button.
	public void mouseEntered(MouseEvent e) {
		JPanel courseBar = (JPanel) e.getSource();
		courseBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		courseBar.setBackground(this.hoverColor);
	}
	
	public void mouseExited(MouseEvent e) {
		JPanel courseBar = (JPanel) e.getSource();
		courseBar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		courseBar.setBackground(this.unhoveredColor);
	}
}
