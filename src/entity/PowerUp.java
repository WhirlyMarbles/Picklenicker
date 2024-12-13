package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class PowerUp extends Entity {
	Random randomE;
	public PowerUp(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.randomE = new Random();
		this.hitbox = new Rectangle(0, 0, 64, 64);
		this.speed = 2F;
		reset();
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/img/powerup/.png"));
		}
		catch(IOException error) {
			gamePanel.getLogger().debug("cannot load powerup image file", "PowerUp.java");
		}
	}
	public void draw(Graphics2D graphics2D) {
		if(gamePanel.keyHandler.ctrlPressed) {
			drawDebug(graphics2D);
		}
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
	public void update() {
		y+=speed;
		hitbox.y=(int)y;
		if(gamePanel.player.hitbox.intersects(hitbox)){reset();gamePanel.player.powered+=1500;}
		if(hitbox.y>=gamePanel.SCREEN_HEIGHT){reset();}
	}
	private void reset() {
		hitbox.x = randomE.nextInt(0, gamePanel.SCREEN_WIDTH - hitbox.width);
		y = randomE.nextInt(-8192, -hitbox.height); // I love powers of two
	}
}
