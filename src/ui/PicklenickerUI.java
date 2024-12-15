package ui;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PicklenickerUI {
	Font courier = new Font("Courier New", Font.BOLD, 30);
	GamePanel gamePanel;
	int gayY;
	PNTitle title;
	BufferedImage heart;
	BufferedImage noheart;
	Logger logger = new Logger("pnUI");
	BufferedImage lol;
	public PNGameOverScreen pngos;
	private final Color[] gaycolors = new Color[] {
			new Color(255, 0, 0),
			new Color(255, 128, 0),
			new Color(255, 255, 0),
			new Color(0, 255, 0),
			new Color(0, 0, 255),
			new Color(117, 7, 135)
	};
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
		drawPoints(graphics2D);
	}
	public void drawTitle(Graphics2D graphics2D) {
		title.draw(graphics2D);
	}
	private void drawGameUI(Graphics2D graphics2D) {
		drawPlayerPower(graphics2D);
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
		drawPoints(graphics2D);
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
	private void drawPlayerPower(Graphics2D graphics2D) {
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(196, 196, 196));
			graphics2D.fillRect(0, 50, gamePanel.player.powered / 6, 50);
		}
		gayY = 50;
		for(Color color : gaycolors) {
			graphics2D.setColor(color);
			for(int i=0;i<gamePanel.player.powered / 6;i++) {
				graphics2D.fillRect(i, gayY + (int) (4 * Math.sin((float)i / 6)), 1, 10);
			}
			gayY += 10;
		}
		if(gamePanel.keyHandler.ctrlPressed) {
			graphics2D.setColor(Color.RED);
			graphics2D.drawLine(0, 50, gamePanel.player.powered / 6, 50);
			graphics2D.drawLine(0, 75, gamePanel.player.powered / 6, 75);
			graphics2D.drawLine(0, 100, gamePanel.player.powered / 6, 100);
			graphics2D.drawLine(gamePanel.player.powered / 6, 50, gamePanel.player.powered / 6, 100);
		}
	}
	private void drawPoints(Graphics2D graphics2D) {
		graphics2D.setColor(Color.black);
		int x1, y1, x2, y2;
		graphics2D.setFont(courier);
		FontMetrics fontM = graphics2D.getFontMetrics();
		x1 = gamePanel.SCREEN_WIDTH - fontM.stringWidth("Points: " + gamePanel.player.points) - 10;
		y1 = fontM.getAscent();
		graphics2D.drawString("Points: "+gamePanel.player.points, x1, y1);
		x2 = gamePanel.SCREEN_WIDTH - fontM.stringWidth("High: " + gamePanel.highscore) - 10;
		y2 = y1 * 2;
		graphics2D.drawString("High: "+gamePanel.highscore, x2, y2);
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
