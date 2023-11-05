package Levels.GameLevels;
import java.awt.*;

import Characters.Characters.Student;
import Levels.Managers.Level2;
import Objects.Wall;

public class MurphysRoom2 extends Level2{ 
    //Students
    Student harvey = new Student(300, 370, 50, 50);
    
    public MurphysRoom2(Level2 enter, Level2 exit){
        super(enter, exit, "murphysRoom");
        
        harvey.setPhrase("The hell you want?");
        setBg("GroupGame\\src\\images\\bg_classroom02.jpg");
        
        students = new Student[]{harvey};

        //UNCOMMENT THIS ITS IMPORTANT
        setLevelExitPos(new int[] {800,490,100,25});
        
        wall = new Wall[]{new Wall(0, 50, 1920, 80), new Wall(0, 500, 1920, 80), new Wall(100, 50, 80, 1000),new Wall(1000, 50, 80, 1000)};

   
    }
    

   
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
        //pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        
        
        for(Wall walls : wall){
            walls.setColor(Color.GREEN);
            walls.draw(pen);
        }

        // draw the exit (theres no enterance)
        dRectEx.draw(pen);
      
        // DRAW THE STUDENTS
        //harvey.draw(pen);
        for(Student s : students){
            //s.setColor(Color.GREEN);
            s.draw(pen);
        }
  
    }

    


  

    
}
 