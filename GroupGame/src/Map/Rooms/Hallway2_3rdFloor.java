package Map.Rooms;

import java.awt.Graphics;

import Game.Game;
import Map.Room;
import Objects.HealthStation;
import Sprites.Characters.Enemy;

public class Hallway2_3rdFloor extends Room{
	
	public Hallway2_3rdFloor(Game game) {
		super(game, "3rdfloorhallway2");
		setRoomEnterPos(new int[] { 0, 670, 50, 100});
		setRoomExitPos(new int[] { 425, 0, 100, 50});
//		setHealthStation(new HealthStation(100, 500));
//		enemies = new Enemy[] {new Enemy("REDWOLF",600, 700, 100, 100), new Enemy("REDWOLF",200, 500, 100, 100)} ;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//		for(Enemy enemy : enemies)
//			enemy.draw(g);

		dRectEnter.draw(g);
		dRectExit.draw(g);
	}

}
