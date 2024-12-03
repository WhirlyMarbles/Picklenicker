package mouse;

import java.awt.event.*;

public class MouseHandler implements MouseListener {
	public boolean mouseReleased = false;
	public boolean mousePressed = false;
	
	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		mouseReleased = false;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseReleased = true;
		mousePressed = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
}
