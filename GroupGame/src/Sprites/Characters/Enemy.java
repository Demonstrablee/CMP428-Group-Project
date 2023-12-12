package Sprites.Characters;

import java.awt.Color;
import java.awt.Graphics;

import Sprites.Sprite;


public class Enemy extends Sprite {

	private final static String[] POSE = new String[] { "ATTACKRT", "ATTACKLT", "RT", "LT", "IDLE" };
	
	public Enemy(String type, int x, int y, int h, int w) {
		super(type, POSE, 6, "png", x, y, w, h);
		c = Color.GRAY;
	}
}
