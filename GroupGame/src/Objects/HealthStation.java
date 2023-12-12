package Objects;

import Utils.Rect;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Refills the player's health at this location.
 */
public class HealthStation extends Rect {

	public HealthStation(int x, int y) {
		super(x, y, 40, 40);
	}
	
	public void draw(Graphics pen) {
		super.setColor(Color.PINK);
		super.draw(pen);
	}
}
