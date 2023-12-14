package Map.Rooms;

import Game.Game;
import Map.Room;
import Sprites.Characters.Enemy;
import Sprites.Characters.Student;

import java.awt.*;

public class Classroom extends Room {

	public Classroom(Game game) {
		super(game, "classroom");
		setRoomExitPos(new int[] { 900, 490, 50, 100});
		students = new Student[] {new Student("blueStudentBoy", 9, "png", 600, 400, 30, 55, 0L, false)};
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//
//		for(Student student : students)
//			student.draw(g);
		
		// Draw the exit (there's no entrance)
		dRectExit.draw(g);
		
	}
}
