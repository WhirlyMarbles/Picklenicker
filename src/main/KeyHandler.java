package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {@Override public void keyTyped(KeyEvent e){}
	public boolean QPressed = false;
	public boolean WPressed = false;
	public boolean EPressed = false;
	public boolean RPressed = false;
	public boolean TPressed = false;
	public boolean YPressed = false;
	public boolean UPressed = false;
	public boolean IPressed = false;
	public boolean OPressed = false;
	public boolean PPressed = false;
	public boolean APressed = false;
	public boolean SPressed = false;
	public boolean DPressed = false;
	public boolean GPressed = false;
	public boolean FPressed = false;
	public boolean HPressed = false;
	public boolean JPressed = false;
	public boolean KPressed = false;
	public boolean LPressed = false;
	public boolean ZPressed = false;
	public boolean XPressed = false;
	public boolean CPressed = false;
	public boolean VPressed = false;
	public boolean BPressed = false;
	public boolean NPressed = false;
	public boolean MPressed = false;
	public boolean spacePressed = false;
	public boolean enterPressed = false;
	public boolean ctrlPressed = false;
	@Override
	public void keyPressed(KeyEvent e) {
		int kCode = e.getKeyCode();
		if(kCode == KeyEvent.VK_Q) {QPressed = true;}
		if(kCode == KeyEvent.VK_W) {WPressed = true;}
		if(kCode == KeyEvent.VK_E) {EPressed = true;}
		if(kCode == KeyEvent.VK_R) {RPressed = true;}
		if(kCode == KeyEvent.VK_T) {TPressed = true;}
		if(kCode == KeyEvent.VK_Y) {YPressed = true;}
		if(kCode == KeyEvent.VK_U) {UPressed = true;}
		if(kCode == KeyEvent.VK_I) {IPressed = true;}
		if(kCode == KeyEvent.VK_O) {OPressed = true;}
		if(kCode == KeyEvent.VK_P) {PPressed = true;}
		if(kCode == KeyEvent.VK_A) {APressed = true;}
		if(kCode == KeyEvent.VK_S) {SPressed = true;}
		if(kCode == KeyEvent.VK_D) {DPressed = true;}
		if(kCode == KeyEvent.VK_F) {FPressed = true;}
		if(kCode == KeyEvent.VK_G) {GPressed = true;}
		if(kCode == KeyEvent.VK_H) {HPressed = true;}
		if(kCode == KeyEvent.VK_J) {JPressed = true;}
		if(kCode == KeyEvent.VK_K) {KPressed = true;}
		if(kCode == KeyEvent.VK_L) {LPressed = true;}
		if(kCode == KeyEvent.VK_Z) {ZPressed = true;}
		if(kCode == KeyEvent.VK_X) {XPressed = true;}
		if(kCode == KeyEvent.VK_C) {CPressed = true;}
		if(kCode == KeyEvent.VK_V) {VPressed = true;}
		if(kCode == KeyEvent.VK_B) {BPressed = true;}
		if(kCode == KeyEvent.VK_N) {NPressed = true;}
		if(kCode == KeyEvent.VK_M) {MPressed = true;}
		if(kCode == KeyEvent.VK_SPACE) {spacePressed = true;}
		if(kCode == KeyEvent.VK_ENTER) {enterPressed = true;}
		if(kCode == KeyEvent.VK_CONTROL) {ctrlPressed = true;}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int kCode = e.getKeyCode();
		if(kCode == KeyEvent.VK_Q) {QPressed = false;}
		if(kCode == KeyEvent.VK_W) {WPressed = false;}
		if(kCode == KeyEvent.VK_E) {EPressed = false;}
		if(kCode == KeyEvent.VK_R) {RPressed = false;}
		if(kCode == KeyEvent.VK_T) {TPressed = false;}
		if(kCode == KeyEvent.VK_Y) {YPressed = false;}
		if(kCode == KeyEvent.VK_U) {UPressed = false;}
		if(kCode == KeyEvent.VK_I) {IPressed = false;}
		if(kCode == KeyEvent.VK_O) {OPressed = false;}
		if(kCode == KeyEvent.VK_P) {PPressed = false;}
		if(kCode == KeyEvent.VK_A) {APressed = false;}
		if(kCode == KeyEvent.VK_S) {SPressed = false;}
		if(kCode == KeyEvent.VK_D) {DPressed = false;}
		if(kCode == KeyEvent.VK_F) {FPressed = false;}
		if(kCode == KeyEvent.VK_G) {GPressed = false;}
		if(kCode == KeyEvent.VK_H) {HPressed = false;}
		if(kCode == KeyEvent.VK_J) {JPressed = false;}
		if(kCode == KeyEvent.VK_K) {KPressed = false;}
		if(kCode == KeyEvent.VK_L) {LPressed = false;}
		if(kCode == KeyEvent.VK_Z) {ZPressed = false;}
		if(kCode == KeyEvent.VK_X) {XPressed = false;}
		if(kCode == KeyEvent.VK_C) {CPressed = false;}
		if(kCode == KeyEvent.VK_V) {VPressed = false;}
		if(kCode == KeyEvent.VK_B) {BPressed = false;}
		if(kCode == KeyEvent.VK_N) {NPressed = false;}
		if(kCode == KeyEvent.VK_M) {MPressed = false;}
		if(kCode == KeyEvent.VK_SPACE) {spacePressed = false;}
		if(kCode == KeyEvent.VK_ENTER) {enterPressed = false;}
		if(kCode == KeyEvent.VK_CONTROL) {ctrlPressed = false;}
	}
}
