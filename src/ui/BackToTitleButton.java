package ui;

import logger.Logger;
import main.GamePanel;
import main.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BackToTitleButton extends Button {
	public BackToTitleButton(GamePanel gamePanel, Rectangle hitbox) {
		this.gamePanel = gamePanel;
		this.hitbox = hitbox;
		try {
			this.imageR = ImageIO.read(getClass().getResourceAsStream("/img/ui/back_to_title.png"));
			this.imageC = ImageIO.read(getClass().getResourceAsStream("/img/ui/back_to_title_pressed.png"));
		}
		catch(IOException error) {
			Logger.log("cannot load image for BackToTitleButton");
		}
	}
	public void onclick() {
		gamePanel.gameState = GameState.TITLE;
	}
}
