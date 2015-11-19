package results;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * A simple listener to expand/collapse details of a clicked search result.
 * @author Jesse
 *
 */
public class SelectedCourseListener extends MouseAdapter {
	DefaultListModel<JPanel> listModel;
	Image expand;
	Image collapse;

	SelectedCourseListener(DefaultListModel<JPanel> listModel, Image expand, Image collapse) {
		this.listModel = listModel;
		this.expand = expand;
		this.collapse = collapse;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			JList list = (JList) e.getSource();
			int index = list.getSelectedIndex();
			JPanel course = (JPanel) list.getSelectedValue();
			JPanel courseBar = (JPanel) course.getComponent(0);
			JLabel courseInfoToggle = (JLabel) courseBar.getComponent(1);
			JPanel courseInfo = (JPanel) course.getComponent(1);
			
			boolean visibilityToggle = (courseInfo.isVisible()) ? false : true;
			Image imgToggle = (courseInfo.isVisible()) ? this.expand : this.collapse;
			courseInfo.setVisible(visibilityToggle);
			courseInfoToggle.setIcon(new ImageIcon(imgToggle));
			listModel.setElementAt(course, index);
		}
	}
}
