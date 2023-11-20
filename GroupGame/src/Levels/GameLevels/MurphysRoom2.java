package Levels.GameLevels;
import java.awt.*;

import Levels.Managers.Level2;
import Objects.Wall;

public class MurphysRoom2 extends Level2{ 
    //Students
   //Student stacy = new Student("STACY",690, 172);  // ALL THE IMAGES ARE IN THE FOLDER ASSOCIATED WITH THE CHARACTER
   //Student marcus = new Student("MARCUS",630, 150);
    
    
    public MurphysRoom2(Level2 enter, Level2 exit){
        super(enter, exit, "murphysRoom");
        
      // stacy.setPhrase("The hell you want?");
        setBg("bg_classroom02.jpg");
        setBackground(Color.BLACK);
        setBounds(0,0,1280,720);
      
        // add students to 
       //students = new Student[]{stacy, marcus};

        // Level Exit Set
        setLevelExitPos(new int[] {800,490,100,25});
        
        // Set Walls
        wall = new Wall[]{new Wall(100, 500, 1080, 80), //bottom
            new Wall(100, 50, 1080, 80), //top
            new Wall(100, 50, 80, 530), // left
            new Wall(1100, 50, 80, 530)}; //right

   
    }
    

   
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
        //pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawImage(bg,97,53 ,1085, 530,null);
        
        
        for(Wall walls : wall){
            walls.setColor(Color.GREEN);
            walls.draw(pen);
        }

        // draw the exit (theres no enterance)
        dRectEx.draw(pen);
      
        // DRAW THE STUDENTS
        // for(Student s : students){
        //     s.draw(pen);
        // }
  
    }

    


  

    
}
 