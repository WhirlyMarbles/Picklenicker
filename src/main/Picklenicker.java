package main;

import logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Picklenicker {
	static final String VERSION = "2.0.9";
	static Image icon;
	public static void main(String[] args) {
		Logger.log("starting picklenicker...");
		try {
			icon = Toolkit.getDefaultToolkit().getImage(Picklenicker.class.getResource("/img/logo.png"));
		} catch (Exception error) {
			Logger.log("cannot load icon image");
		}
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0); /*the same as putting window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
				, wanted it to be customizable*/
			}
		});
		window.setResizable(false);
		window.setTitle("Picklenicker Java " + VERSION);
		
		GamePanel gamePanel = new GamePanel();
		gamePanel.addWindow(window);
		window.add(gamePanel);
		
		if (icon != null) {
			window.setIconImage(icon);
		} else {
			Logger.log("no icon set for window " + window);
		}
		
		window.setIconImage(icon);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startThread();
	}
}
