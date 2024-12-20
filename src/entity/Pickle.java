package entity;

import logger.Logger;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Class Pickle - extends Entity
 * Constructor receives GamePanel and image
 * @see entity.Entity
 * @see main.GamePanel
 * */
public class Pickle extends Entity {
	Random randomE = new Random();
	BufferedImage image;
	KeyHandler keyHandler;
	int scale;
	public Pickle(GamePanel gamePanel, BufferedImage image) {
		this.scale = randomE.nextInt(16, 48);
		int y = randomE.nextInt(-1024, -(this.scale*2));
		int x = randomE.nextInt(0, gamePanel.SCREEN_WIDTH-this.scale);
		this.keyHandler = gamePanel.keyHandler;
		this.gamePanel = gamePanel;
		
		this.image = image;
		this.hitbox = new Rectangle();
		this.hitbox.x = x;this.hitbox.y = y;
		
		this.x = x;this.y = y;this.hitbox.width=this.scale/2;this.hitbox.height=this.scale/2*3;
		this.speed = randomE.nextDouble(0.01, 1.60);
	}
	public Pickle(GamePanel gamePanel) {
		this.scale = randomE.nextInt(16, 48);
		int y = randomE.nextInt(-1024, -(this.scale*2));
		int x = randomE.nextInt(0, gamePanel.SCREEN_WIDTH-this.scale);
		this.keyHandler = gamePanel.keyHandler;
		this.gamePanel = gamePanel;
		
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/img/pickle/0.png"));
		}
		catch(IOException error) {
			Logger.log("Cannot import pickle image file, <class Pickle> " + this);
		}
		this.hitbox = new Rectangle();
		this.hitbox.x = x;this.hitbox.y = y;
		
		this.x = x;this.y = y;this.hitbox.width=this.scale/2;this.hitbox.height=this.scale/2*3;
		this.speed = randomE.nextDouble(0.01, 1.60);
	}
	public void draw(Graphics2D graphics2D) {
		if(keyHandler.ctrlPressed) {
			drawDebug(graphics2D);
		}
		if(gamePanel.debug || hitbox.contains(gamePanel.mouseHandler.getMousePosInWindow(gamePanel.window))) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
	public void update() {
		y += speed;
		hitbox.y = (int) y;
		if(hitbox.intersects(gamePanel.player.hitbox)) {
			spawnPicklesAndReset();
			gamePanel.player.points++;
		}
		if(hitbox.y >= gamePanel.SCREEN_HEIGHT) {
			spawnPicklesAndReset();
			if(gamePanel.player.powered <= 0){
				gamePanel.player.hp--;
			}
		}
	}
	public void spawnPicklesAndReset() {
		reset();
		for(int i=0;i<randomE.nextInt(0, 3);i++) {
			gamePanel.pickles.add(new Pickle(gamePanel, image));
		}
	}
	private void reset() {
		this.scale = randomE.nextInt(16, 48);
		int y = randomE.nextInt(-1024, -(this.scale*2));
		int x = randomE.nextInt(0, gamePanel.SCREEN_WIDTH-this.scale);
		this.hitbox = new Rectangle();
		this.hitbox.x = x;this.hitbox.y = y;
		this.x = x;this.y = y;this.hitbox.width=this.scale/2;this.hitbox.height=this.scale/2*3;
		this.speed = randomE.nextDouble(0.01, 1.60);
	}
}