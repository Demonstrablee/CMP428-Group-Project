package Sprites.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Sprites.Sprite;


public class Enemy extends Sprite{
	static String pose [] = new String[] {"ATTACKRT","ATTACKLT","RT","LT","IDLE"};
	
	public Enemy(String type, int x, int y, int h, int w) {
		super(type,pose,6,0,"png",x, y, w, h);
		c = Color.GRAY;

	}
	
	
	public void draw(Graphics pen) {
		
		super.draw(pen);
	}

}
