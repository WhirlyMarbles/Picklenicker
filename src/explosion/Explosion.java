package explosion;

import main.GamePanel;

import java.awt.*;

public class Explosion {
	int lifetime = 0;
	int x, y;
	int MAX_SIZE;
	static int MAX_LIFETIME = 1500;
	static int EXPAND_SPEED = 5;
	GamePanel gamePanel;
	Oval oval;
	public Explosion(int x, int y, GamePanel gamePanel) {
		this.x=x;this.y=y;this.oval=new Oval(x, y, 0, 0);this.gamePanel=gamePanel;
		this.MAX_SIZE = 400;
	}
	public void drawAndUpdate(Graphics2D graphics2D) {
		graphics2D.setColor(getColor());
		lifetime += EXPAND_SPEED;
		oval.draw(graphics2D, lifetime);
	}
	private Color getColor() {
		int alpha = Math.max(Math.min(255-((255 * lifetime) / MAX_LIFETIME), 255), 0);
		return(new Color(255, 255, 255, alpha));
	}
	
	private class Oval {
		int x, y;int width, height;
		private Oval(int x, int y, int width, int height) {
			this.x=x;this.y=y;this.width=width;this.height=height;
		}
		private void draw(Graphics2D graphics2D, int lifetime) {
			width = (lifetime * MAX_SIZE) / MAX_LIFETIME;
			height = (lifetime * MAX_SIZE) / MAX_LIFETIME;
			graphics2D.fillOval(x - (width / 2), y - (height / 2), width, height);
			if(gamePanel.keyHandler.EPressed) {
				graphics2D.setColor(Color.red);
				graphics2D.drawRect(x - (width / 2), y - (height / 2), width, height);
			}
		}
	}
}