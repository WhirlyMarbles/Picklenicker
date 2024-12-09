package ui;

import logger.Logger;
import main.GamePanel;
import main.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class PlayButton extends Button {
	public PlayButton(GamePanel gamePanel, Rectangle hitbox) {
		this.gamePanel = gamePanel;
		this.hitbox = hitbox;
		try {
			this.imageR = ImageIO.read(getClass().getResourceAsStream("/img/ui/play.png"));
			this.imageC = ImageIO.read(getClass().getResourceAsStream("/img/ui/play_pressed.png"));
		}
		catch(IOException error) {
			Logger.log("cannot load image for PlayButton");
		}
	}
	public void onclick() {
		gamePanel.gameState = GameState.GAME;
	}
}
