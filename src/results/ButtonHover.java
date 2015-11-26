package results;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonHover implements MouseListener {
	private Color unhoveredColor;
	private Color hoverColor;

	ButtonHover(Color unhoveredColor, Color hoverColor) {
		this.unhoveredColor = unhoveredColor;
		this.hoverColor = hoverColor;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBackground(this.hoverColor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		button.setBackground(this.unhoveredColor);
	}

}
