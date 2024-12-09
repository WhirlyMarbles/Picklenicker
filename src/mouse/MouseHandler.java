package mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseHandler implements MouseListener {
	public boolean mousePressed = false;
	
	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (mousePressed) {
			mousePressed = false;
		}
	}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	public Point getMousePosInWindow(JFrame frame) {
		Point mousePosOnScreen = MouseInfo.getPointerInfo().getLocation();
		Point frameLocation = frame.getLocationOnScreen();
		Insets insets = frame.getInsets();
		int adjustedX = mousePosOnScreen.x - frameLocation.x - insets.left;
		int adjustedY = mousePosOnScreen.y - frameLocation.y - insets.top;
		return new Point(adjustedX, adjustedY);
	}
}
