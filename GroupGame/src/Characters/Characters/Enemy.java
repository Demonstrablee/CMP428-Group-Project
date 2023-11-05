package Characters.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Objects.Rect;

public class Enemy extends Rect{
	
	public Enemy(int x, int y, int h, int w) {
		
		super(x, y, w, h);
	}
	
	public void draw(Graphics pen) {
		this.setColor(Color.GRAY);
		super.draw(pen);
	}

}
