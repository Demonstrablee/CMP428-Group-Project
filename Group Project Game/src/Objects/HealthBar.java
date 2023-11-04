package Objects;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar extends Rect {
	
	int health = 3;
	
	int maxHealth = 3;
	
	boolean notInvincible = true;
	
	public HealthBar(int x, int y, int w, int h) {
		
		super(x, y, w, h);
		
	}
	
	public boolean damage() {
		
		return notInvincible = true;
	}
	
	public boolean noDamage() {
		
		return notInvincible = false;
	}
	
	public void increaseHealth(int x) {
		
		maxHealth = x;
		
		health = maxHealth;
	}
	
	public void damageTaken(Graphics pen) {
	
		if(notInvincible) {
			
		health--;
		
		noDamage();
		
		}
	    
	}
		
	public void refillHealth(Graphics pen) {
		
		health = maxHealth;
	}
	
	public void draw(Graphics pen) {
		
		pen.setColor(Color.RED);
		
	    for (int i = 0; i < health; i++) {
	        pen.fillRect((int)(this.x + i * 25), (int)(this.y), (int)(this.w), (int)(this.h));
	    }
	    
	}

}
