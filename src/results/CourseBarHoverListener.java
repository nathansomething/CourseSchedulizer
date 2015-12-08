package results;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CourseBarHoverListener extends MouseAdapter {
	private Color unhoveredColor;
	private Color hoverColor;
	
	CourseBarHoverListener(Color unhoveredColor, Color hoverColor) {
		this.unhoveredColor = unhoveredColor;
		this.hoverColor = hoverColor;
	}
	
	// Bug: doesn't work after clicking on 'View Schedule' button.
		// Update: Bug with Swing.
		public void mouseEntered(MouseEvent e) {
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
			
			courseBar.setBackground(this.hoverColor);
			courseBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			courseCode.setBackground(this.hoverColor);
			courseCode.setCursor(new Cursor(Cursor.HAND_CURSOR));
			courseName.setBackground(this.hoverColor);
			courseName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
		public void mouseExited(MouseEvent e) {
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
			
			courseBar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			courseBar.setBackground(this.unhoveredColor);
			courseCode.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			courseCode.setBackground(this.unhoveredColor);
			courseName.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			courseName.setBackground(this.unhoveredColor);
		}
}
