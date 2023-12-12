package Sprites.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Utils.Rect;
import Sprites.Animation;

public class BlueMonster extends Rect {
	
	public Rect sight;
	public Rect damageZone;
	public double delay = 0;
	
	/*
	 * if(p1.overlaps(mon)) {
            	
            	p1.pushedOutOf(mon);
            }
            
            if(p1.overlaps(mon.sight)) {
    			
            	mon.chasePlayer(p1);
    		}
            
            if(p1.overlaps(mon.damageZone) && mon.delay >= 30) {
            	
            	//change this with health
            	p1.setColor(Color.RED);
            }
	 */
	
	private boolean movingDown = true;
	private boolean facingUP = false;
	private boolean facingDN = false;
	private boolean chasing = true;
	
	Animation monsterUP = new Animation("Images/BlueMonster/UP", 20, 5);
	
	Animation monsterDN = new Animation("Images/BlueMonster/DN", 20, 5);
	
	Animation monsterATK_UP = new Animation("Images/BlueMonsterATK/ATK_UP", 30, 2);
	
	Animation monsterATK_DN = new Animation("Images/BlueMonsterATK/ATK_DN", 30, 2);

	public BlueMonster(int x, int y, int w, int h) {
		super(x, y, 50, 50);
		
		sight = new Rect(x - 15, y, 75, 500);
		
		damageZone =  new Rect(x - 15, y, 75, 100);
	}
	
	public void patrol() {
		
		if(chasing) {
			
		if(movingDown) {
			
			super.moveBy(0, 1);
			damageZone.moveBy(0, 1);
			
			if(getY() >= sight.getY() + 250 + 200) movingDown = false;
		}
		
		if(!movingDown) {
			
			super.moveBy(0, -1);
			damageZone.moveBy(0, -1);
			
			if(getY() <= sight.getY() - 15) movingDown = true;
		}
		}
	}
	
	public void chasePlayer(Rect r) {
		
		if(getY() < r.getY() && facingDN && chasing) {
			
			super.chase(r);
			damageZone.chaseDamage(r);
			
			if(r.overlaps(this)) chasing = false;
			
		}
		else if(getY() > r.getY() && facingUP && chasing) {

			super.chase(r);
			damageZone.chaseDamage(r);
			
			if(r.overlaps(this)) chasing = false;
			
		}
		else patrol();	
	}
	
	public void draw(Graphics pen) {
		
		damageZone.setColor(Color.RED);
		damageZone.draw(pen);
		
		if(movingDown && chasing) {
		pen.drawImage(monsterDN.getCurrentImage(), (int) getX() - 75, (int) getY() - 75, 200, 200, null);
		
		facingUP = false;
		facingDN = true;
		}
		
		if(!chasing && facingDN) {
			pen.drawImage(monsterATK_DN.getCurrentImage(), (int) getX() - 75, (int) getY() - 75, 200, 200, null);
			
			delay++;
			
			if(delay >= 50) {
				chasing = true;
				delay = 0;
			}
		}
		if(!chasing && facingUP) {
			pen.drawImage(monsterATK_UP.getCurrentImage(), (int) getX() - 75, (int) getY() - 75, 200, 200, null);
			
			delay++;
			
			if(delay >= 50) {
				chasing = true;
				delay = 0;
			}
		}
		
		if(!movingDown && chasing) {
		pen.drawImage(monsterUP.getCurrentImage(), (int) getX() - 75, (int) getY() - 75, 200, 200, null);
		
		facingUP = true;
		facingDN = false;
		}
		super.draw(pen);
		
		patrol();
		
		sight.draw(pen);
	}

}
