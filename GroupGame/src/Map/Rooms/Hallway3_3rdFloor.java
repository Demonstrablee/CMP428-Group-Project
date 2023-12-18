package Map.Rooms;

import java.awt.Graphics;

import Game.Game;
import Map.Room;
import Sprites.Characters.Enemy;

public class Hallway3_3rdFloor extends Room{
	public Hallway3_3rdFloor(Game game) {
		super(game, "3rdfloorhallway3");
		setRoomEnterPos(new int[] { 53, 630, 50, 100});
		setRoomExitPos(new int[] { 900, 680, 50, 100});
//		setHealthStation(new HealthStation(100, 500));
		enemies = new Enemy[] {new Enemy("REDWOLF",600, 700, 100, 100), new Enemy("REDWOLF",200, 500, 100, 100)} ;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(Enemy enemy : enemies) {
			if(enemy.hits <= 2)
			enemy.draw(g);
		}

		dRectEnter.draw(g);
		dRectExit.draw(g);
	}


}
