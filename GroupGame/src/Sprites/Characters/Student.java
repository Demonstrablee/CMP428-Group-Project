package Sprites.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Student extends Character{ // christan

	private final static String[] POSE = new String[] { "IDLE", "LT", "RT", "UP", "DN" };
	
	private String phrase;
	private boolean appear;
	double ay = G;
	
	static final double G = 0.4;

	public Student(String name, int x, int y) {
		super(null, name, POSE, 4, "png",x,y,50,50, 0, 0);
		type = 1;
	}
    
	public Student(String name, int imageCount, String filetype, int x, int y , int h, int w, long pNum, boolean canInterview) {
		super(null, name, POSE, imageCount, filetype, x, y, w, h, pNum, canInterview);
		type = 1;
	}

	public void talk(Graphics g) {
		g.setColor(Color.WHITE);
		
		if(appear) g.drawString(phrase, (int) (this.x), (int) (this.y - 10));
		
		super.draw(g);
	}

	public void talk(Graphics g, String phrase) {
		this.phrase = phrase;
		
		g.setColor(Color.WHITE);
		
		if(appear) g.drawString(phrase, (int) (this.x), (int) (this.y - 10));
		
		super.draw(g);
	}
	
	public void speaking() {
		appear = true;
	}
	
	public void silent() {
		appear = false;
	}
	
	public void draw(Graphics pen) {
		super.setColor(Color.yellow);
		super.draw(pen);
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public void update() {
		
		applyFrictionWithFloor();
		actionLockCounter++;
		
		if(actionLockCounter == 120 ) { // ~2 seconds --> 60 FPS
			Random r = new Random();
			int i = r.nextInt(60)+1;
			
			
			if(i <= 15) {
				pose = UP;
				goUP(1);
			}
			if(i > 15 && i <= 30) {
				pose = DN;
				goDN(1);
			}
			if(i > 30 && i <= 45) {
				pose = LT;
				goLT(1);
			}
			if(i > 45 && i <= 60) {
				pose = RT;
				goRT(1);
			}
			
			actionLockCounter = 0;
		}
		move();
		
	}
	
	public void move() {
		x += vx;
		y += vy;
		
		ay += vy;
	}
	
}

