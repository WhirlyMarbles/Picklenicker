package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pickle extends Entity {
	BufferedImage image;
	KeyHandler keyHandler;
	GamePanel gamePanel;
	public Pickle(GamePanel gamePanel, KeyHandler keyHandler, BufferedImage image, int x, int y, int size) {
		this.keyHandler = keyHandler;
		this.gamePanel = gamePanel;
		
		this.image = image;
		this.hitbox = new Rectangle();
		this.hitbox.x = x;this.hitbox.y = y;
		this.x = x;this.y = y;this.hitbox.width=size/2;this.hitbox.height=size/2*3;
		this.speed = 0.5;
	}
	public void draw(Graphics2D graphics2D) {
		if(keyHandler.TPressed) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
	public void update() {
		y += speed;
		hitbox.y = (int) y;
	}
}