package Map.Rooms;

import java.awt.Graphics;

import Game.Game;
import Map.Room;
import Sprites.Characters.Student;

public class ClassroomB_3rdFloor extends Room{
	public ClassroomB_3rdFloor(Game game) {
		super(game, "3rdfloorclassroomB");
		setRoomEnterPos(new int[] { 0, 500, 50, 100});
//		students = new Student[] {new Student("blueStudentBoy", 9, "png", 600, 400, 30, 55, 0L, false)};
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//
//		for(Student student : students)
//			student.draw(g);
		
		game.safePuzzle.draw(g);
		game.safePuzzle.interactPrompt(g);

		dRectEnter.draw(g);	
	}

}
