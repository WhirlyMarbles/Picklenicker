package main;

import entity.Entity;
import entity.Pickle;
import entity.Player;
import entity.PowerUp;
import logger.Logger;
import mouse.MouseHandler;
import ui.PicklenickerUserInterface;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.prefs.Preferences;

public class GamePanel extends JPanel implements Runnable {
	// user prefs
	Preferences prefs = Preferences.userNodeForPackage(Picklenicker.class);
	
	//yey
	public JFrame window;
	
	// pnread
	HashMap<String, Integer> difficultySettings = new HashMap<>();
	
	// log
	Logger LOGGER;
	
	public MouseHandler mouseHandler;
	
	public boolean running = true;
	public boolean gameStarted = false;
	
	// ui
	public PicklenickerUserInterface ui;
	
	// handle keys
	KeyHandler keyHandler = new KeyHandler();
	
	//panel constants
	public final int[] SCREEN_SIZE = new int[] {1000, 600};
	public final int SCREEN_WIDTH = SCREEN_SIZE[0];
	public final int SCREEN_HEIGHT = SCREEN_SIZE[1];
	
	public int highscore;
	
	// thread vars
	double FPS = 240;
	
	// player vars yay!!!!!!!!!!
	public Player player;
	// pickle var!!!!!!!!!!!
	public ArrayList<Entity> pickles = new ArrayList<>();
	PickleAdmin pickleInit;
	
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
				if(keyHandler.TPressed){System.out.println("TPS: " + paintCount);}
				paintCount = 0;
				timer = 0;
			}
		}
	}
	
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
		
		this.ui = new PicklenickerUserInterface(this);
		this.LOGGER = new Logger("game panel");
		this.LOGGER.debug("testing local logger");
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(new Color(128, 128, 255));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		
		this.player = new Player(this, this.keyHandler);
	}
	public void startGame() {
		if(difficulty.equals("HACKED")) {
			player.MAX_HP = 1000;
		}
		gameStarted = true;
		pickleInit = new PickleAdmin(this, 4);
		player.hp = player.MAX_HP;
	}
	/**
	 * Updates the game state. This method is responsible for updating the player's position,
	 * handling key inputs, and checking interactions between the player and the pickles.
	 * It also manages the removal of pickles when they collide with the player or go out of bounds.
	 * New pickles are generated after collisions.
	 *
	 * @see Player#update()
	 * @see Pickle#update()
	 */
	public void update() {
		if (keyHandler.BPressed) {
			System.exit(0);
		}
		if(running && gameStarted) {
			prefs.putInt("highscore", Math.max(player.points, highscore));
			highscore = prefs.getInt("highscore", 0);
			player.update();
			ArrayList<Pickle> toAdd = new ArrayList<>(); // Temporary list for new pickles
			synchronized (pickles) {
				Iterator<Entity> iterator = pickles.iterator();
				while (iterator.hasNext()) {
					Entity pickle = iterator.next();
					pickle.update();
					if (pickle.hitbox.intersects(player.hitbox) && pickle instanceof Pickle) {
						iterator.remove(); // Safe removal using Iterator
						for(int i = 0; i<pickleInit.randomE.nextInt(1,2); i++) {
							toAdd.add(pickleInit.getPickle());
						}
						player.points++;
					} else if (pickle.hitbox.y >= SCREEN_HEIGHT && pickle instanceof Pickle) {
						iterator.remove(); // Safe removal using Iterator
						for(int i = 0; i<pickleInit.randomE.nextInt(1,2); i++) {
							toAdd.add(pickleInit.getPickle());
						}
						if(player.powered==0){
							player.hp--;
						}
					}
					if (pickle.hitbox.intersects(player.hitbox) && pickle instanceof PowerUp) {
						pickle.power(true);
					}
					else if (pickle.hitbox.y >= SCREEN_HEIGHT && pickle instanceof PowerUp) {
						pickle.power(false);
					}
				}
			}
			synchronized (pickles) {
				pickles.addAll(toAdd); // Add new pickles after the iterations
			}
		}
	}
	public void reset() {
		pickles = new ArrayList<>();
		player = new Player(this, keyHandler);
		pickleInit = new PickleAdmin(this, difficultySettings.get(difficulty));
		running = true;
		gameStarted = false;
	}
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics;
		if(gameStarted) {
			synchronized (pickles) {
				player.draw(graphics2D);
				for (Entity pickle : pickles) {
					pickle.draw(graphics2D);
				}
			}
			ui.drawGameScreen(graphics2D);
		}
		else {ui.drawTitleScreen(graphics2D);}
		
		graphics2D.dispose();
	}
}
