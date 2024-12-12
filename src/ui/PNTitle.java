package ui;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PNTitle {
	Logger logger;
	ArrayList<Button> buttons = new ArrayList<>();
	BufferedImage picklenicker = null;
	GamePanel gamePanel;
	int pnImgW = 512;
	int pnImgH = 128;
	DifficultyModerator difficultyModerator;
	public PNTitle(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.difficultyModerator = new DifficultyModerator(gamePanel);
		try {
			picklenicker = ImageIO.read(getClass().getResourceAsStream("/img/ui/picklenicker.png"));
		}
		catch(IOException error) {
			logger.warn("cannot load title image");
		}
		this.buttons.add(new PlayButton(gamePanel, new Rectangle(
				(gamePanel.SCREEN_WIDTH - 128)/2,
				(gamePanel.SCREEN_HEIGHT - 64)/2,
				128, 64)));
	}
	public void draw(Graphics2D graphics2D) {
		int pnx = (gamePanel.SCREEN_WIDTH - pnImgW) / 2;
		int pny = (gamePanel.SCREEN_HEIGHT / 3) - pnImgH / 2;
		graphics2D.drawImage(picklenicker, pnx, pny, pnImgW, pnImgH, null);
		difficultyModerator.draw(graphics2D);
		if(gamePanel.keyHandler.ctrlPressed) {
			graphics2D.setColor(new Color(128, 255, 128));
			graphics2D.drawLine(0, pny, gamePanel.SCREEN_WIDTH, pny);
			graphics2D.drawLine(pnx, 0, pnx, gamePanel.SCREEN_HEIGHT);
			graphics2D.drawLine(0, pny+pnImgH, gamePanel.SCREEN_WIDTH, pny+pnImgH);
			graphics2D.drawLine(pnx+pnImgW, 0, pnx+pnImgW, gamePanel.SCREEN_HEIGHT);
			graphics2D.setColor(Color.BLACK);
			graphics2D.drawLine(pnx+pnImgW/2, 0, pnx+pnImgW/2, gamePanel.SCREEN_HEIGHT);
			graphics2D.drawLine(0, pny+pnImgH/2, gamePanel.SCREEN_WIDTH, pny+pnImgH/2);
		}
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(255, 0, 0, 128));
			graphics2D.fillRect(pnx, pny, pnImgW, pnImgH);
		}
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).update(graphics2D);
		}
	}
}
