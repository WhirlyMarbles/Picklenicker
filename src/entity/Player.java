package entity;

import logger.Logger;
import main.GamePanel;
import main.GameState;
import main.KeyHandler;
import ui.PNGameOverScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
	public int points = 0;
	public int MAX_HP = 20;
	public int hp = MAX_HP;
	public BufferedImage left, right;
	public String direction = "";
	
	public int powered = 0;
	
	Logger LOGGER;
	
	KeyHandler keyHandler;
	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		this.LOGGER = new Logger("player");
		LOGGER.info("building player class");
		
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		this.hitbox = new Rectangle(0, 0, 64, 96);
		init();
	}
	public void draw(Graphics2D graphics2D) {
		if(keyHandler.ctrlPressed) {
			drawDebug(graphics2D);
		}
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(0, 255, 0, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
		image = switch(direction) {
			case "right" -> right;
			case "left" -> left;
			default -> left;
		};
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
	private void init() {
		speed = 1;
		hitbox.y = gamePanel.SCREEN_HEIGHT - hitbox.height;
		hitbox.x = gamePanel.SCREEN_WIDTH / 2 - hitbox.width / 2;
		y=gamePanel.SCREEN_HEIGHT - hitbox.height;x=gamePanel.SCREEN_WIDTH / 2 - hitbox.width / 2;
		getPlayerImage();
	}
	private void getPlayerImage() {
		try {
			left = ImageIO.read(getClass().getResourceAsStream("/img/player/left.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/img/player/right.png"));
		}
		catch(IOException error) {
			LOGGER.info("failed to load player image for "+direction);
		}
	}
	public void update() {
		if(hp <= 0 && gamePanel.gameState != GameState.GAME_OVER) {
			gamePanel.gameState = GameState.GAME_OVER;
			gamePanel.pnUI.pngos.reset();
		}
		if(keyHandler.DPressed) {direction = "right";}
		if(keyHandler.APressed) {direction = "left";}
		if(powered>0) {speed=6;powered--;}else{speed=4;}
		switch(direction) {
			case "right":
				x += speed;
				break;
			case "left":
				x -= speed;
				break;
		}
		sync();
		if(hitbox.x > gamePanel.SCREEN_WIDTH) {
			x = -hitbox.width;
			sync();
		}
		if(hitbox.x < -hitbox.width) {
			x = gamePanel.SCREEN_WIDTH;
			sync();
		}
	}
	public void sync() {
		hitbox.x = (int) x;
	}
}
