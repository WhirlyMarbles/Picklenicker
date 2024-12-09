package ui;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
	Rectangle hitbox;
	BufferedImage imageR;
	BufferedImage imageC;
	GamePanel gamePanel;
	BufferedImage image;
	boolean clicked;
	public void update(Graphics2D graphics2D) {
		if(hitbox.contains(gamePanel.mouseHandler.getMousePosInWindow(gamePanel.window))) {
			clicked = true;
			if(gamePanel.mouseHandler.mousePressed) {
				clicked = false;
				onclick();
			}
		}
		else {
			clicked = false;
		}
		draw(graphics2D);
	}public void update(Graphics2D graphics2D, int opacity) {
		if(hitbox.contains(gamePanel.mouseHandler.getMousePosInWindow(gamePanel.window))) {
			clicked = true;
			if(gamePanel.mouseHandler.mousePressed) {
				clicked = false;
				onclick();
			}
		}
		else {
			clicked = false;
		}
		draw(graphics2D, opacity);
	}
	
	protected void draw(Graphics2D graphics2D) {
		if(clicked) {image = imageC;}else {image = imageR;}
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
		if(gamePanel.keyHandler.ctrlPressed) {
			graphics2D.setColor(new Color(128, 255, 128));
			graphics2D.drawLine(0, hitbox.y, gamePanel.SCREEN_WIDTH, hitbox.y);
			graphics2D.drawLine(0, hitbox.y+hitbox.height, gamePanel.SCREEN_WIDTH, hitbox.y+hitbox.height);
			graphics2D.drawLine(hitbox.x, 0, hitbox.x, gamePanel.SCREEN_HEIGHT);
			graphics2D.drawLine(hitbox.x+hitbox.width, 0, hitbox.x+hitbox.width, gamePanel.SCREEN_HEIGHT);
		}
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(128, 255, 128, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
	}
	protected void draw(Graphics2D graphics2D, int opacity) {
		if(clicked) {image = imageC;}else {image = imageR;}
		float α = Math.min(opacity * (1F / 255), 1);
		graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, α));
		graphics2D.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
		graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		if(gamePanel.keyHandler.ctrlPressed) {
			graphics2D.setColor(new Color(128, 255, 128));
			graphics2D.drawLine(0, hitbox.y, gamePanel.SCREEN_WIDTH, hitbox.y);
			graphics2D.drawLine(0, hitbox.y+hitbox.height, gamePanel.SCREEN_WIDTH, hitbox.y+hitbox.height);
			graphics2D.drawLine(hitbox.x, 0, hitbox.x, gamePanel.SCREEN_HEIGHT);
			graphics2D.drawLine(hitbox.x+hitbox.width, 0, hitbox.x+hitbox.width, gamePanel.SCREEN_HEIGHT);
		}
		if(gamePanel.debug) {
			graphics2D.setColor(new Color(128, 255, 128, 128));
			graphics2D.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		}
	}
	public void onclick() {}
}
