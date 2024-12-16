package ui;

import main.Difficulty;
import main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class DifficultyModerator {
	GamePanel gamePanel;
	public Rectangle hitbox = new Rectangle(0,0,0,0); // as to avoid nullity
	private final Font headerFont = new Font("Courier New", Font.BOLD, 30);
	private final Font textFont = new Font("Courier New", Font.BOLD, 50);
	private final int headerHeight = getFontHeight(headerFont);
	private final int textHeight =  getFontHeight(textFont);
	private final int headerWidth = getStringWidth(headerFont,  "Difficulty:");
	private int textWidth;
	private Point pen;
	private final int penYdefault;
	public DifficultyModerator(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.pen = new Point(10, gamePanel.SCREEN_HEIGHT / 2);
		this.hitbox.x = this.pen.x - 5;
		this.hitbox.y = this.pen.y - getFontMetrics(headerFont).getAscent();
		this.hitbox.height = (getFontHeight(headerFont) + getFontHeight(textFont));
		this.penYdefault = gamePanel.SCREEN_HEIGHT / 2;
	}
	private int getFontHeight(Font font) {
		JPanel temp = new JPanel();
		return(temp.getFontMetrics(font).getHeight());
	}
	private FontMetrics getFontMetrics(Font font) {
		JPanel temp = new JPanel();
		return(temp.getFontMetrics(font));
	}
	private int getStringWidth(Font font, String string) {
		JPanel temp = new JPanel();
		return(temp.getFontMetrics(font).stringWidth(string));
	}
	private void resetPen() {
		pen = new Point(10, gamePanel.SCREEN_HEIGHT / 2);
	}
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(Color.WHITE);
		hitbox.width = Math.max(textWidth, headerWidth) + 5;
		textWidth = getStringWidth(textFont, gamePanel.difficulty.toString());
		graphics2D.fillRoundRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height, 10, 10);
		graphics2D.setColor(Color.BLACK);
		graphics2D.setFont(headerFont);
		graphics2D.drawString("Difficulty:", pen.x, pen.y);
		graphics2D.setFont(textFont);
		pen.y += textHeight;
		graphics2D.drawString(gamePanel.difficulty.toString(), pen.x, pen.y);
		resetPen();
		if(gamePanel.mouseHandler.mousePressed && hitbox.contains(gamePanel.mouseHandler.getMousePosInWindow(
				gamePanel.window))) {
			gamePanel.difficulty = gamePanel.difficulty.next();
			gamePanel.prefs.put("difficulty", gamePanel.difficulty.name());
		}
		if(gamePanel.keyHandler.HPressed) {
			gamePanel.difficulty = Difficulty.HACKED;
		}
	}
}