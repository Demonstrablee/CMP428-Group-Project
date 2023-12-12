package Objects;

import Game.Game;
import Utils.Rect;

import java.awt.*;

public class HealthBar extends Rect {
	
	private int health;
	private final int maxHealth;
	private boolean invincible;
	private final Image heart;
	
	public HealthBar(int x, int y, int w, int h, int maxHealth) {
		super(x, y, w * Game.SCALE, h * Game.SCALE);
		this.maxHealth = maxHealth;
		health = maxHealth;
		invincible = false;

		// Loads heart image & rescales it
		heart = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/heart.png");
	}

	/**
	 * @return The current value of health.
	 */
	public int getCurrentHealth() {
		return health;
	}

	/**
	 * @return The maximum amount of health.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Disables invincibility.
	 */
	public void damage() {
		invincible = false;
	}

	/**
	 * Disables damage.
	 */
	public void invincible() {
		
		invincible = true;
	}

	/**
	 * Increases health by one if not at full health.
	 */
	public void increaseHealth() {
		if(health >= maxHealth)return;
		health++;
		//SimpleSoundPlayer.playSound("GroupGame/src/music/zelda_heartRestore.wav"); // causes lag
	}

	/**
	 * Decreases health by one if invincibility is disabled.
	 */
	public void damageTaken() {
		if(invincible)return;
		health--;
		invincible();
	}

	/**
	 * Sets health back to full (max).
	 */
	public void refillHealth() {
		health = maxHealth;
	}
	
	public void draw(Graphics pen) {
		pen.setColor(Color.RED);

		// draw one less rectangle for the health of the player
	    for (int i = 0; i < health; i++)
				pen.drawImage(heart, (int)(this.x + i * (25 * Game.SCALE)), (int)(this.y), (int)w, (int)h, null);
	}
}
