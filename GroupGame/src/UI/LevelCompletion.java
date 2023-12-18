package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;


public class LevelCompletion {
	
	Game game;
	Graphics pen;
	Font arial_40, arial_80B;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 1;
	BufferedImage heart_blank, heart_half, heart_full;
	
	double playTime;
//	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	public LevelCompletion(Game game) {
		this.game = game;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics pen) {
		this.pen = pen;
		
		pen.setFont(arial_40);
		pen.setColor(Color.white);
		
		//INITIAL
		if(game.safePuzzle.openSafe()) {
			levelCompletedScreen();
		}
		
	}
	
	public void levelCompletedScreen() {
		
		//Game Title
		pen.setFont(arial_80B);
		String text = "Level Completed";
		
		int x = getScreenXValue(text);
		int y = Game.GAME_WIDTH;
		
		//SHADOW TEXT
		pen.setColor(Color.BLACK);
		pen.drawString(text, x + 5, y + 5);
		//Change color when I change BACKGROUND color, can be done here
		pen.setColor(Color.white);
		
		pen.drawString(text, x, y);
		
		
		
		
	}
	
	public int getScreenXValue(String text) {
		int length = (int) pen.getFontMetrics().getStringBounds(text, pen).getWidth();
		return game.GAME_WIDTH/2 - length/2;
	}

}
