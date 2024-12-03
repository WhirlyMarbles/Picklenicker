package main;

import entity.Pickle;
import entity.PowerUp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class PickleAdmin {
	GamePanel gamePanel;
	Random randomE;
	BufferedImage pickleImg;
	public PickleAdmin(GamePanel gamePanel, int pickles) {
		this.gamePanel = gamePanel;
		try {
			pickleImg = ImageIO.read(getClass().getResourceAsStream("/img/pickle/0.png"));
		}
		catch(IOException error) {
			System.out.println("unable to load pickle images. avoi!");
			pickleImg = null;
		}
		randomE = new Random();
		for(int i = 0;i < pickles;i++) {
			gamePanel.pickles.add(new Pickle(gamePanel, gamePanel.keyHandler, pickleImg,
					randomE.nextInt(0, gamePanel.SCREEN_WIDTH - 16),
					randomE.nextInt(-500, -48), randomE.nextInt(16,48))
			);
		}
		gamePanel.pickles.add(new PowerUp(gamePanel.keyHandler, gamePanel, randomE.nextInt(0, gamePanel.SCREEN_WIDTH-16), randomE.nextInt(-1000, -16), "anonymous powerup"));
	}
	public Pickle getPickle() {
		return(new Pickle(gamePanel, gamePanel.keyHandler, pickleImg,
				randomE.nextInt(0, gamePanel.SCREEN_WIDTH),
				randomE.nextInt(-1000, -48),
				randomE.nextInt(16, 48)
		));
	}
}