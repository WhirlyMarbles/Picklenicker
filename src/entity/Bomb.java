package entity;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Bomb extends Entity {
	GamePanel gamePanel;
	BufferedImage image;
	Random randomE;
	public Bomb(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.speed = 0.6;
		this.randomE = new Random();
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/img/bomb/.png"));
		}
		catch(IOException error) {
			Logger.log("Cannot import bomb image file");
		}
		this.reset();
	}
	public void update() {
		y += speed;
		hitbox.y = (int) y;
		if(hitbox.y >= gamePanel.SCREEN_HEIGHT) {
			reset();
		}
		if(hitbox.intersects(gamePanel.player.hitbox)) {
			gamePanel.explosionHandler.add((int)hitbox.getCenterX(), (int)hitbox.getCenterY());
			reset();
			gamePanel.boom();
		}
	}
	private void reset() {
		y = randomE.nextInt(-4096, -48);
		hitbox = new Rectangle(randomE.nextInt(gamePanel.SCREEN_WIDTH), (int) y, 64, 64);
	}
	public void draw(Graphics2D graphics2D) {
		if(gamePanel.keyHandler.TPressed) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
		graphics2D.setColor(new Color(255, 255, 255));
	}
}