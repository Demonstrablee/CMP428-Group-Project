package Characters.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Levels.Managers.Level2;
//import Objects.Rect;

public class Student extends Character{ // christan
	
	String phrase;
	
	
	boolean appear = false;

	static String[] pose = new String[] {"IDLE"}; // all students have just one pose

	public Student(String name, int x, int y) {
		super(name,pose,4,0,"png",x,y,50,50);
	}
    
    public Student(String name, int imagecount, int start, String filetype,int x, int y , int h, int w, long pNum, Level2 location, boolean canInterview){
		super(name,pose,imagecount,start,filetype,x,y,w,h,pNum, location, canInterview);
	}
	
	// Setters
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	/** */
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
	
	public boolean isSpeaking() {
		
		return appear = true;
	}
	
	public boolean isNotSpeaking() {
		
		return appear = false;
	}
	
	public void draw(Graphics pen)
	{
		super.setColor(Color.yellow);
		super.draw(pen);
		
	}
	
}

