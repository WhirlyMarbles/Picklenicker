package explosion;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class ExplosionHandler {
	private ArrayList<Explosion> explosions = new ArrayList<>();
	private final GamePanel gamePanel;
	public ExplosionHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public void add(int x, int y) {
		explosions.add(new Explosion(x, y));
	}
	public void updateAndDraw(Graphics2D graphics2D) {
		explosions = optimize();
		for(int i = 0;i < explosions.size();i++) {
			explosions.get(i).drawAndUpdate(graphics2D);
			if(explosions.get(i).lifetime >= Explosion.MAX_LIFETIME) {
				explosions.set(i, null);
			}
		}
	}
	private ArrayList<Explosion> optimize() {
		ArrayList<Explosion> optimized = new ArrayList<>();
		for(Explosion explosion : explosions) {
			if(explosion != null) {
				optimized.add(explosion);
			}
		}
		return(optimized);
	}
}
