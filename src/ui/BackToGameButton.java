package ui;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackToGameButton {
	BufferedImage image, imagec;
	GamePanel gamePanel;
	Rectangle hitbox;
	Logger LOGGER;
	PicklenickerUserInterface pnui;
	boolean enterWasPressed = false;
	public BackToGameButton(GamePanel gamePanel, Rectangle hitbox, PicklenickerUserInterface pnui) {
		this.LOGGER = new Logger("PlayButton");
		this.pnui = pnui;
		this.gamePanel = gamePanel;
		this.hitbox = hitbox;
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/img/ui/back_to_title.png"));
			this.imagec = ImageIO.read(getClass().getResourceAsStream("/img/ui/back_to_title_pressed.png"));
		}
		catch(IOException error) {
			LOGGER.warn("can't load image file for BackToGameButton.java");
		}
	}
	public void update(Graphics2D graphics2D, int opacity) {
		Point mousePos = MouseInfo.getPointerInfo().getLocation();
		Point frameLocation = gamePanel.window.getLocationOnScreen();
		Insets insets = gamePanel.window.getInsets();
		mousePos.x -= frameLocation.x + insets.left;
		mousePos.y -= frameLocation.y + insets.top;
		BufferedImage currentImage = image;
		if(gamePanel.mouseHandler.mousePressed && hitbox.contains(mousePos) || gamePanel.keyHandler.enterPressed) {
			currentImage = imagec;
			enterWasPressed = true;
		}
		if(gamePanel.mouseHandler.mouseReleased && hitbox.contains(mousePos) || (enterWasPressed && (!gamePanel.keyHandler.enterPressed))) {
			pnui.gameOverFGOpacity=0;pnui.gameOverBGOpacity=0;
			enterWasPressed = false;
			gamePanel.reset();
		}
		gamePanel.mouseHandler.mouseReleased = false;
		graphics2D.drawImage(currentImage, hitbox.x, hitbox.y, hitbox.width+1, hitbox.height, null);
		graphics2D.setColor(new Color(0, 0, 0, Math.max(255 - opacity, 0)));
		graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
}
