package ui;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PicklenickerUI {
	GamePanel gamePanel;
	PNTitle title;
	BufferedImage heart;
	BufferedImage noheart;
	Logger logger = new Logger("pnUI");
	BufferedImage lol;
	public PNGameOverScreen pngos;
	public PicklenickerUI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.pngos = new PNGameOverScreen(gamePanel);
		this.title = new PNTitle(gamePanel);
		try {
			heart = ImageIO.read(getClass().getResourceAsStream("/img/ui/heart.png"));
			noheart = ImageIO.read(getClass().getResourceAsStream("/img/ui/noheart.png"));
			lol = ImageIO.read(getClass().getResourceAsStream("/img/lol/.png"));
		}
		catch(IOException error) {
			logger.warn("cannot import ui symbols");
		}
	}
	public void drawGameOver(Graphics2D graphics2D) {
		pngos.draw(graphics2D);
	}
	public void drawTitle(Graphics2D graphics2D) {
		title.draw(graphics2D);
	}
	public void drawGameUI(Graphics2D graphics2D) {
		int heartSize = 16;
		BufferedImage heartImg = heart;
		int x = 0;
		int y = 0;
		for (int i = 0; i < gamePanel.player.MAX_HP; i++) {
			graphics2D.drawImage(heartImg, x, y, heartSize, heartSize, null);
			if(i+1 >= gamePanel.player.hp) {
				heartImg = noheart;
			}
			if(x>gamePanel.SCREEN_WIDTH-heartSize) {x=0;y+=heartSize;}
			else{x+=heartSize;}
		}
		if(gamePanel.keyHandler.ctrlPressed) {
			graphics2D.setColor(new Color(255, 0, 0));
			graphics2D.drawLine(0, heartSize, gamePanel.SCREEN_WIDTH, heartSize);
			graphics2D.drawLine(heartSize * gamePanel.player.MAX_HP, 0, heartSize * gamePanel.player.MAX_HP, gamePanel.SCREEN_HEIGHT);
			graphics2D.setColor(new Color(128, 0, 128));
			graphics2D.drawLine(heartSize * gamePanel.player.hp, 0, heartSize * gamePanel.player.hp, gamePanel.SCREEN_HEIGHT);
		}
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(64, 64, 64));
			graphics2D.fillRect(0, 0, heartSize * gamePanel.player.MAX_HP, heartSize);
			graphics2D.setColor(new Color(255, 255, 255));
			graphics2D.fillRect(0, 0, heartSize * gamePanel.player.hp, heartSize);
		}
	}
	public void drawUI(Graphics2D graphics2D) {
		switch(gamePanel.gameState) {
			case TITLE:
				drawTitle(graphics2D);
				break;
			case GAME:
				drawGameUI(graphics2D);
				break;
			case GAME_OVER:
				drawGameOver(graphics2D);
				break;
		}
		if(gamePanel.keyHandler.JPressed) {
			graphics2D.drawImage(lol, 0, 0, null);
		}
	}
}
