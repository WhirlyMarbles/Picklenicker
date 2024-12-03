package entity;

import logger.Logger;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class PowerUp extends Entity {
	KeyHandler keyHandler;GamePanel gamePanel;
	Logger LOGGER;
	Random randome;
	public PowerUp(KeyHandler keyHandler, GamePanel gamePanel, int x, int y, String loggerid) {
		this.randome = new Random();
		this.hitbox = new Rectangle(0, 0, 16, 16);
		this.hitbox.x=x;this.hitbox.y=y;this.speed=2;this.gamePanel=gamePanel;this.keyHandler=keyHandler;
		this.LOGGER = new Logger(loggerid);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/img/powerup/.png"));
		}
		catch(IOException error) {
			LOGGER.warn("could not load image file");
		}
	}
	public void draw(Graphics2D graphics2D) {
		if(keyHandler.TPressed) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
	public void update() {hitbox.y+=speed;}
	public void power(boolean eaten) {
		if(eaten){
			gamePanel.player.powered += 1500;
		}
		hitbox.x = randome.nextInt(0, gamePanel.SCREEN_WIDTH-16);
		hitbox.y = randome.nextInt(-6000, -16);
	}
}
