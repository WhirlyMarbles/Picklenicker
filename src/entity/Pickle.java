package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pickle extends Entity {
	BufferedImage image;
	KeyHandler keyHandler;
	GamePanel gamePanel;
	public Pickle(GamePanel gamePanel, KeyHandler keyHandler, BufferedImage image, int x, int y) {
		this.keyHandler = keyHandler;
		this.gamePanel = gamePanel;
		
		this.image = image;
		this.hitbox = new Rectangle();
		this.hitbox.x = x;this.hitbox.y = y;this.hitbox.width=16;this.hitbox.height=48;
		this.speed = 1;
	}
	public void draw(Graphics2D graphics2D) {
		if(keyHandler.TPressed) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
	public void update() {
		hitbox.y += speed;
	}
}