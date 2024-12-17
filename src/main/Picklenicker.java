package main;

import logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Picklenicker {
	static Logger logger;
	static final String VERSION = "2.0.9";
	static Image icon;
	public static void main(String[] args) {
		logger = new Logger("main");
		logger.info("starting picklenicker...");
		try {
			icon = Toolkit.getDefaultToolkit().getImage(Picklenicker.class.getResource("/img/logo.png"));
		} catch (Exception error) {
			logger.warn("cannot load icon image");
		}
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				int response = JOptionPane.showConfirmDialog(
						window,
						"Do you really want to exit?",
						"Are you sure?",
						JOptionPane.YES_NO_OPTION
				);
				if(response == JOptionPane.YES_OPTION){JOptionPane.showMessageDialog(
						window,
						"Too bad!",
						"Welp",
						JOptionPane.INFORMATION_MESSAGE
				);}
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
			logger.warn("no icon set for window " + window);
		}
		
		window.setIconImage(icon);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startThread();
	}
}
