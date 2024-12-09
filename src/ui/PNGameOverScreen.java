package ui;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PNGameOverScreen {	Logger logger;
	ArrayList<Button> buttons = new ArrayList<>();
	BufferedImage game_overImg = null;
	GamePanel gamePanel;
	int goImgW = 512;
	int goImgH = 64;
	float opacity;public void reset(){opacity=0;}
	public PNGameOverScreen(GamePanel gamePanel) {
		this.opacity = 0;
		this.gamePanel = gamePanel;
		try {
			game_overImg = ImageIO.read(getClass().getResourceAsStream("/img/ui/game_over.png"));
		}
		catch(IOException error) {
			logger.warn("cannot load title image");
		}
		this.buttons.add(new BackToTitleButton(gamePanel, new Rectangle(
				(gamePanel.SCREEN_WIDTH - 128)/2,
				(int)(gamePanel.SCREEN_HEIGHT * 0.6F - 64F / 2),
				128, 64)));
	}
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(new Color(255, 255, 255, Math.min((int) opacity, 255)));
		graphics2D.fillRect(0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT);
		int pnx = (gamePanel.SCREEN_WIDTH - goImgW) / 2;
		int pny = (gamePanel.SCREEN_HEIGHT / 3) - goImgH / 2;
		float α = Math.min(opacity * (1F / 255), 1);
		graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, α));
		graphics2D.drawImage(game_overImg, pnx, pny, goImgW, goImgH, null);
		graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(128, 255, 128));
			graphics2D.drawLine(0, pny, gamePanel.SCREEN_WIDTH, pny);
			graphics2D.drawLine(pnx, 0, pnx, gamePanel.SCREEN_HEIGHT);
			graphics2D.drawLine(0, pny+ goImgH, gamePanel.SCREEN_WIDTH, pny+goImgH);
			graphics2D.drawLine(pnx+ goImgW, 0, pnx+ goImgW, gamePanel.SCREEN_HEIGHT);
			graphics2D.setColor(new Color(255, 0, 0, 128));
			graphics2D.fillRect(pnx, pny, goImgW, goImgH);
			graphics2D.setColor(Color.BLACK);
			graphics2D.drawLine(pnx+ goImgW /2, 0, pnx+ goImgW /2, gamePanel.SCREEN_HEIGHT);
			graphics2D.drawLine(0, pny+ goImgH /2, gamePanel.SCREEN_WIDTH, pny+goImgH /2);
		}
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).update(graphics2D, (int) opacity);
		}
		opacity+=1.5;
	}
}
