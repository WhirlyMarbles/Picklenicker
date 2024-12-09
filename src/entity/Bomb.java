package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends Entity {
	GamePanel gamePanel;
	BufferedImage image;
	public Bomb(GamePanel gamePanel, int x, int y) {
		this.gamePanel = gamePanel;
		this.speed = 0.6;
		this.hitbox = new Rectangle(x, y, 18, 48);
	}
	public void update() {
		y += speed;
		hitbox.y = (int) y;
	}
	public void draw(Graphics2D graphics2D) {
		if(gamePanel.keyHandler.TPressed) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
}