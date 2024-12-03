package ui;

import logger.Logger;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PicklenickerUserInterface {
	Logger LOGGER;
	BufferedImage heart, noheart;
	GamePanel gamePanel;
	public int gameOverBGOpacity = 0;
	public int gameOverFGOpacity = 0;
	BufferedImage powerup;
	
	BufferedImage play;
	BufferedImage play_pressed;
	BufferedImage picklenicker;
	
	PlayButton playButton;
	BackToGameButton backToTitleButton;
	
	public PicklenickerUserInterface(GamePanel gamePanel) {
		this.LOGGER = new Logger("ui handler");
		this.gamePanel = gamePanel;
		this.playButton = new PlayButton(this.gamePanel, new Rectangle((gamePanel.SCREEN_WIDTH - 128) / 3, (gamePanel.SCREEN_HEIGHT - 64) / 2, 128, 64));
		this.backToTitleButton = new BackToGameButton(this.gamePanel, new Rectangle((gamePanel.SCREEN_WIDTH - 128) / 3, (gamePanel.SCREEN_HEIGHT - 64) / 3, 128, 64), this);
		try {
			heart = ImageIO.read(getClass().getResourceAsStream("/img/ui/heart.png"));
			noheart = ImageIO.read(getClass().getResourceAsStream("/img/ui/noheart.png"));
			powerup = ImageIO.read(getClass().getResourceAsStream("/img/powerup/.png"));
			play = ImageIO.read(getClass().getResourceAsStream("/img/ui/play.png"));
			play_pressed = ImageIO.read(getClass().getResourceAsStream("/img/ui/play_pressed.png"));
			picklenicker = ImageIO.read(getClass().getResourceAsStream("/img/ui/picklenicker.png"));
		}
		catch(IOException error) {
			LOGGER.warn("unable to load image files for ui");
		}
	}
	public void drawGameScreen(Graphics2D graphics2D) {
		int penx = 0;
		int peny = 0;
		BufferedImage himg;
		for (int i = 0; i < gamePanel.player.MAX_HP; i++) {
			himg = heart;
			if (i + 1 > gamePanel.player.hp) {
				himg = noheart;
			}
			graphics2D.drawImage(himg, penx, peny, 16, 16, null);
			if (penx > gamePanel.SCREEN_WIDTH) {
				penx = 0;
				peny += 16;
			} else {
				penx += 16;
			}
		}
		graphics2D.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
		FontMetrics fontMetrics = graphics2D.getFontMetrics(graphics2D.getFont());
		int x = (gamePanel.SCREEN_WIDTH - fontMetrics.stringWidth((""+gamePanel.player.points)));
		int y = fontMetrics.getAscent();
		graphics2D.setColor(new Color(0, 0, 0, 255));
		graphics2D.drawString(""+gamePanel.player.points, x, y);
		graphics2D.setColor(Color.BLUE);
		x = (gamePanel.SCREEN_WIDTH - fontMetrics.stringWidth(("High: "+gamePanel.highscore)));
		y = fontMetrics.getAscent() + 32;
		graphics2D.setColor(new Color(0, 0, 0, 255));
		graphics2D.drawString("High: "+gamePanel.highscore, x, y);
		graphics2D.setColor(Color.BLUE);
		graphics2D.fillRect(0, 50, gamePanel.player.powered / 6, 64);
		if(gamePanel.player.powered>0){graphics2D.drawImage(powerup, 0, 50, 64, 64, null);}
		if(gamePanel.player.hp < 1) {
			gameOver(graphics2D);
		}
	}
	private void gameOver(Graphics2D graphics2D) {
		gamePanel.running = false;
		graphics2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 170));
		FontMetrics fontMetrics = graphics2D.getFontMetrics(graphics2D.getFont());
		int x = (gamePanel.SCREEN_WIDTH - fontMetrics.stringWidth("GAME OVER")) / 2;
		int y = ((gamePanel.SCREEN_HEIGHT - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
		graphics2D.setColor(new Color(0, 0, 0, Math.min(gameOverBGOpacity, 255)));
		graphics2D.fillRect(0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT);
		if(gameOverBGOpacity < 255) {gameOverBGOpacity+=2;}else{
			if(gameOverFGOpacity<255){gameOverFGOpacity+=2;}
			graphics2D.setColor(new Color(255, 0, 0, Math.min(gameOverFGOpacity, 255)));
			graphics2D.drawString("GAME OVER", x, y);
			backToTitleButton.update(graphics2D, gameOverFGOpacity);
		}
	}
	public void drawTitleScreen(Graphics2D graphics2D) {
		graphics2D.drawImage(picklenicker, (gamePanel.SCREEN_WIDTH - 512) / 2, 64, 512, 128, null);
		playButton.update(graphics2D);
	}
}
