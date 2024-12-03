package main;

import logger.Logger;

import javax.swing.*;
import java.awt.*;

public class Picklenicker {
	static Logger logger;
	static final String VERSION = "0.0.1";
	static Image icon;
	public static void main(String[] args) {
		logger = new Logger("main");
		logger.info("starting picklenicker...");
		try {
			icon = Toolkit.getDefaultToolkit().getImage(Picklenicker.class.getResource("/img/logo.png"));
		} catch (Exception error) {
			logger.error("IconImage not found: " + error.getMessage());
		}
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Picklenicker Java " + VERSION);
		
		GamePanel gamePanel = new GamePanel();
		gamePanel.addWindow(window);
		window.add(gamePanel);
		
		if (icon != null) {
			window.setIconImage(icon);
		} else {
			logger.warn("no icon set for window " + window);
		}
		
		window.setIconImage(icon);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startThread();
	}
}
