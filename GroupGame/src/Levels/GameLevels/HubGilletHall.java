package Levels.GameLevels;
import javax.swing.*;

import Levels.Managers.Level2;
import Objects.Wall;

import java.awt.*;

public class HubGilletHall extends Level2 { 
    //JLabel title = new JLabel("HUB GILLET");
    
    public HubGilletHall(Level2 enter, Level2 exit){
        super(enter, exit, "hubGilletHall");
        
        //BACKGROUND
        setBackground(Color.BLACK); // set pane to black

        //Level Exit and Enterance Set

        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // add(title, constraints);
        
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 

    }
}
 