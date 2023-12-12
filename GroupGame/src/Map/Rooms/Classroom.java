package Map.Rooms;

import Game.Game;
import Map.Room;

import java.awt.*;

/**
 * Written by Nicholas Cercos
 * Created on Dec 12 2023
 **/
public class Classroom extends Room {

	public Classroom(Game game) {
		super(game, "classroom");
		setRoomExitPos(new int[] { 900, 490, 50, 100});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the exit (there's no entrance)
		dRectExit.draw(g);
	}
}
