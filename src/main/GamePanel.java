package main;

import entity.*;
import logger.Logger;
import mouse.MouseHandler;
import ui.PicklenickerUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

public class GamePanel extends JPanel implements Runnable {
	// user prefs
	Preferences prefs = Preferences.userNodeForPackage(Picklenicker.class);
	
	public JFrame window;
	
	HashMap<String, Integer> difficultySettings = new HashMap<>();
	
	// log
	Logger logger;
	
	public MouseHandler mouseHandler;
	
	// handle keys
	public KeyHandler keyHandler = new KeyHandler();
	
	//panel constants
	public final int[] SCREEN_SIZE = new int[] {1000, 600};
	public final int SCREEN_WIDTH = SCREEN_SIZE[0];
	public final int SCREEN_HEIGHT = SCREEN_SIZE[1];
	
	public PicklenickerUI pnUI = new PicklenickerUI(this);
	
	public int highscore;
	
	public GameState gameState = GameState.TITLE;
	
	// thread vars
	double FPS = 240;
	
	public boolean debug = false;
	
	// player vars yay!!!!!!!!!!
	public Player player;
	// pickle var!!!!!!!!!!!
	public ArrayList<Pickle> pickles = new ArrayList<>();
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double Δ = 0;
		long lastPaint = System.nanoTime();
		long currentPaint;
		long timer = 0;
		int paintCount = 0;
		
		while(thread != null) {
			currentPaint = System.nanoTime();
			Δ += (currentPaint - lastPaint) / drawInterval;
			timer += (currentPaint - lastPaint);
			lastPaint = currentPaint;
			if(Δ >= 1) {
				update();
				repaint();
				Δ--;
				paintCount++;
			}
			if(timer >= 1000000000) {
				if(debug){
					logger.debug("TPS: " + paintCount);}
				paintCount = 0;
				timer = 0;
			}
		}
	}
	
	BufferedImage pickleImg;
	
	public void addWindow(JFrame w) {
		window = w;
	}
	
	Thread thread;
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}
	public String difficulty;
	public GamePanel() {
		this.difficulty = prefs.get("difficulty", "normal");
		this.highscore = prefs.getInt("highscore", 0);
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		
		this.difficultySettings.put("easy", 3);
		this.difficultySettings.put("normal", 6);
		this.difficultySettings.put("hard", 10);
		this.difficulty = "normal";
		
		this.logger = new Logger("game panel");
		this.logger.debug("testing local logger");
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(new Color(128, 128, 255));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		
		this.pickleImg = null;
		try {
			pickleImg = ImageIO.read(getClass().getResourceAsStream("/img/pickle/0.png"));
		}
		catch(IOException error) {
			logger.warn("cant load pickle image");
		}
		reset();
	}
	public void update() {
		if (keyHandler.BPressed) {
			System.exit(0);
		}
		debug = keyHandler.TPressed;
		if(gameState == GameState.GAME || gameState == GameState.GAME_OVER) {
			prefs.putInt("highscore", Math.max(player.points, highscore));
			highscore = prefs.getInt("highscore", 0);
			player.update();
			for (int i = 0; i < pickles.size(); i++) {
				pickles.get(i).update();
			}
		}
	}
	public void reset() {
		pickles = new ArrayList<>();
		for(int i = 0;i < difficultySettings.get(difficulty);i++) {
			pickles.add(new Pickle(this, keyHandler, pickleImg));
		}
		player = new Player(this, keyHandler);
	}
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		if(gameState == GameState.GAME || gameState == GameState.GAME_OVER) {
			player.draw(graphics2D);
			for (int i = 0; i < pickles.size(); i++) {
				pickles.get(i).draw(graphics2D);
			}
		}
		pnUI.drawUI(graphics2D);
		
		graphics2D.dispose();
	}
}
