package explosion;

import java.awt.*;

public class Explosion {
	int lifetime = 0;
	int x, y;
	static int MAX_SIZE = 400;
	static int MAX_LIFETIME = 1500;
	static int EXPAND_SPEED = 5;
	Oval oval;
	public Explosion(int x, int y){this.x=x;this.y=y;this.oval=new Oval(x, y, 0, 0);}
	public void drawAndUpdate(Graphics2D graphics2D) {
		graphics2D.setColor(getColor());
		lifetime+= EXPAND_SPEED;
		oval.draw(graphics2D, lifetime);
	}
	private Color getColor() {
		int alpha = 255-((255 * lifetime) / MAX_LIFETIME);
		return(new Color(255, 255, 255, alpha));
	}
	
	private static class Oval {
		int x, y;int width, height;
		private Oval(int x, int y, int width, int height) {
			this.x=x;this.y=y;this.width=width;this.height=height;
		}
		private void draw(Graphics2D graphics2D, int lifetime) {
			width = (lifetime * MAX_SIZE) / MAX_LIFETIME;
			height = (lifetime * MAX_SIZE) / MAX_LIFETIME;
			graphics2D.fillOval(x - (width / 2), y - (height / 2), width, height);
		}
	}
}