package Sprites.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Sprites.Sprite;
import Utils.Rect;


public class Enemy extends Sprite {

	private final static String[] POSE = new String[] { "ATTACKRT", "ATTACKLT", "LT", "RT", "IDLE" };
	
    private boolean moveLT = true;
    private boolean chaseRT = false;
    private boolean chaseLT = false;
    
    public Rect sight;
    public int hitDelay = 0;
    public int hits = 0;
	
	public Enemy(String type, int x, int y, int h, int w) {
		super(type, POSE, 6, "png", x, y, w, h);
		c = Color.GRAY;
		
		sight = new Rect(x, y, 300, h);
	}
	
	public void patrol() {
		
		if(moveLT) {
			super.goLT(0);
			super.moveBy(-1, 0);
			chaseRT = false;
			chaseLT = true;
			 
			if(getX() <= sight.getX()) moveLT = false;
		}
		else {
			super.goRT(0);
			super.moveBy(1, 0);
			chaseRT = true;
			chaseLT = false;
			
			if(getX() >= (sight.getX() + sight.getWidth())) moveLT = true;
		}
	}
	
	public void chase(Rect r) {
		
		patrol();
		
		if(r.overlaps(sight)) {
		if(chaseRT && getX() <= r.getX()) {
		super.chase(r);
		super.goUP(0);
		}
		if(chaseLT && getX() >= r.getX()) {
		super.chase(r);
		super.goDN(0);
		}
	}
}
}
