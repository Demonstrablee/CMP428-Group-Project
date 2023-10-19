package Levels.GameLevels;
import java.awt.*;


import javax.swing.JButton;
import javax.swing.JPanel;

import Levels.Managers.Level2;
import Objects.Wall;

public class MurphysRoom2 extends Level2{ 
    JPanel health = new JPanel();
    JButton title = new JButton("MURPHYS ROOM");
    JButton b = new JButton("Button2");
    JButton csButton;
    //Level [] menus; // acess all the game menus
    GridBagConstraints constraints;

    int width = 1920;
    int height = 1080;
      
    
    public MurphysRoom2(Wall [] wall){
        super(wall); // add all walls to array via refrence
        //csButton = pauseButton;

        //setBg("images\\bg_classroom02.jpg");

    }
   
    
    @Override
    public void draw(Graphics pen){  //method for painting
        pen.setColor(Color.GREEN);
        pen.clearRect(0, 0, width, height);
        pen.drawImage(bg,0,0,width, height,null); // draw background
        pen.setColor(Color.RED);
        // b.repaint();
        // csButton.repaint();
        
        for(Wall wall : wall)
            wall.draw(pen);
        
    }


    


  

    
}
 