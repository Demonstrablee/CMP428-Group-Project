package Sprites.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Levels.Managers.Level2;

public class Student extends Character{ // christan

	private final static String[] POSE = new String[] { "IDLE" };
	
	private String phrase;
	private boolean appear;

	public Student(String name, int x, int y) {
		super(null, name, POSE, 4, "png",x,y,50,50, 0, 0);
	}
    
	public Student(String name, int imageCount, String filetype, int x, int y , int h, int w, long pNum, boolean canInterview) {
		super(null, name, POSE, imageCount, filetype, x, y, w, h, pNum, canInterview);
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
}

