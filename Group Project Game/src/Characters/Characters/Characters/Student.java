package Characters.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Objects.Rect;

public class Student extends Character{
	
	String phrase;
	
	boolean appear = false;

	public Student(int x, int y, int w, int h) {
		
		super(x, y, w, h);
		
	}
	
	public void talk(Graphics g, String phrase) {
		
		this.phrase = phrase;
		
		g.setColor(Color.BLACK);
		
		if(appear) g.drawString(phrase, (int) (this.x), (int) (this.y - 10));
		
		super.draw(g);
	}
	
	public boolean display() {
		
		return appear = true;
	}
	
	public boolean notDisplay() {
		
		return appear = false;
	}
	
	public void draw(Graphics pen)
	{
		super.setColor(Color.GREEN);
		super.draw(pen);
		
	}
	
}