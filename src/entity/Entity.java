package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	double speed;
	double x, y;
	BufferedImage image;
	public Rectangle hitbox;
	GamePanel gamePanel;
	public void update(){}public void draw(Graphics2D graphics2D){}
	public void drawDebug(Graphics2D graphics2D) {
		graphics2D.setColor(new Color(128, 255, 128));
		graphics2D.drawLine(hitbox.x, 0, hitbox.x, gamePanel.SCREEN_HEIGHT);
		graphics2D.drawLine(hitbox.x + hitbox.width, 0, hitbox.x + hitbox.width, gamePanel.SCREEN_HEIGHT);
		graphics2D.drawLine(0, hitbox.y, gamePanel.SCREEN_WIDTH, hitbox.y);
		graphics2D.drawLine(0, hitbox.y+hitbox.height, gamePanel.SCREEN_WIDTH, hitbox.y+hitbox.height);
		graphics2D.setColor(new Color(0, 0, 0));
		graphics2D.drawLine(hitbox.x + hitbox.width / 2, 0, hitbox.x + hitbox.width / 2, gamePanel.SCREEN_HEIGHT);
		graphics2D.drawLine(0, hitbox.y + hitbox.height / 2, gamePanel.SCREEN_WIDTH, hitbox.y + hitbox.height / 2);
	}
}
