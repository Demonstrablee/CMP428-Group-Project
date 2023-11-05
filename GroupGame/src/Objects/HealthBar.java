package Objects;

import java.awt.Color;
import java.awt.Graphics;

import Levels.Managers.SimpleSoundPlayer;

public class HealthBar extends Rect {
	
	int health = 3;
	
	int maxHealth = 3;
	
	boolean notInvincible = true;
	
	public HealthBar(int x, int y, int w, int h) {
		
		super(x, y, w, h);
		
	}
	/**Get players health */
	public int getCurrentHealth() {
		return health;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public boolean damage() {
		
		return notInvincible = true;
	}
	
	public boolean noDamage() {
		
		return notInvincible = false;
	}
	
	public void increaseHealth(int x) {
		
		if(health < maxHealth){ // if not at full health
			health++;
			//SimpleSoundPlayer.playSound("GroupGame/src/music/zelda_heartRestore.wav"); // causes lag
			try{
				Thread.sleep(500);
			}catch(Exception e){

			}
		}
	}

	public void useHealingItem(Item item){ 
		health =+ item.healVal;
	}
	
	
	public void damageTaken() {
	
		if(notInvincible) {
			
		health--;
		
		noDamage();
		
		}
	    
	}
	
		
	public void refillHealth() {
		
		health = maxHealth;
	}
	
	public void draw(Graphics pen) {
		
		pen.setColor(Color.RED);
		
	    for (int i = 0; i < health; i++) { // draw one less rectangle for the health of the player
	        pen.fillRect((int)(this.x + i * 25), (int)(this.y), (int)(this.w), (int)(this.h));
	    }
	    
	}

}
