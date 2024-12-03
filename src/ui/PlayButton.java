package ui;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayButton {
	BufferedImage image, imagec;
	GamePanel gamePanel;
	Rectangle hitbox;
	Logger LOGGER;
	public PlayButton(GamePanel gamePanel, Rectangle hitbox) {
		this.LOGGER = new Logger("PlayButton");
		this.gamePanel = gamePanel;
		this.hitbox = hitbox;
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/img/ui/play.png"));
			this.imagec = ImageIO.read(getClass().getResourceAsStream("/img/ui/play_pressed.png"));
		}
		catch(IOException error) {
			LOGGER.warn("can't load image file");
		}
	}
	public void update(Graphics2D graphics2D) {
		Point mousePos = MouseInfo.getPointerInfo().getLocation();
		Point frameLocation = gamePanel.window.getLocationOnScreen();
		Insets insets = gamePanel.window.getInsets();
		mousePos.x -= frameLocation.x + insets.left;
		mousePos.y -= frameLocation.y + insets.top;
		BufferedImage currentImage = image;
		if(gamePanel.mouseHandler.mousePressed && hitbox.contains(mousePos)) {
			currentImage = imagec;
		}
		if(gamePanel.mouseHandler.mouseReleased && hitbox.contains(mousePos)) {
			gamePanel.startGame();
			System.out.println("ln. 40, PlayButton.javba");
		}
		gamePanel.mouseHandler.mouseReleased = false;
		graphics2D.drawImage(currentImage, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
}
