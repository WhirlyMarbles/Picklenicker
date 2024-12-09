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
	Random randomE;
	public PowerUp(KeyHandler keyHandler, GamePanel gamePanel, int x, int y, String loggerid) {
		this.randomE = new Random();
		this.hitbox = new Rectangle(0, 0, 128, 128);
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
		if(keyHandler.ctrlPressed) {
			drawDebug(graphics2D);
		}
		if(gamePanel.debug) {
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
		hitbox.x = randomE.nextInt(0, gamePanel.SCREEN_WIDTH-hitbox.height);
		hitbox.y = randomE.nextInt(-6000, -hitbox.height);
	}
}
