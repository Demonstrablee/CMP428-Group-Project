package Objects;

import java.awt.Color;
import java.awt.Graphics;

/* Refill the players heath at these location */
public class HealthStation extends Rect {

	public HealthStation(int x, int y) {
		
		super(x, y, 40, 40);
	}
	
	public void draw(Graphics pen) {
		
		super.setColor(Color.PINK);
		super.draw(pen);
	}
}
