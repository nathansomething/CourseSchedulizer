package results;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextHoverListener extends MouseAdapter {
	private static TextHoverListener listener = new TextHoverListener();
	
	private TextHoverListener() { }
	
	public static TextHoverListener getInstance() {
		return listener;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		Component source = (Component) e.getSource();
		source.setCursor(new Cursor(Cursor.TEXT_CURSOR));
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		Component source = (Component) e.getSource();
		source.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
