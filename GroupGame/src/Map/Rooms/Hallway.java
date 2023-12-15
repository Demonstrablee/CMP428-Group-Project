package Map.Rooms;

import Game.Game;
import Map.Room;
import Objects.HealthStation;
import Sprites.Characters.Enemy;

import java.awt.*;

public class Hallway extends Room {
	public Hallway(Game game) {
		super(game, "hallway");
		setRoomEnterPos(new int[] { 0, 725, 50, 100});
		setRoomExitPos(new int[] { 900, 680, 50, 100});
		setHealthStation(new HealthStation(100, 500));
		enemies = new Enemy[] {new Enemy("REDWOLF",600, 700, 100, 100), new Enemy("REDWOLF",200, 500, 100, 100)} ;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(Enemy enemy : enemies)
			enemy.draw(g);

		healthStation.draw(g);
		dRectEnter.draw(g);
		dRectExit.draw(g);
	}
}
