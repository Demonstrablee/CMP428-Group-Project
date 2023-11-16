package Levels.GameLevels;
import javax.swing.*;

import Levels.Managers.Level2;


import java.awt.*;

public class CampusMap extends Level2 { 
    //JLabel title = new JLabel("HUB GILLET");
    
    public CampusMap(Level2 enter, Level2 exit){
        super(enter, exit, "campusMap");
        
        //BACKGROUND
        //setBackground(Color.BLACK); // set pane to black
        setBg("Lehman_Map.png");
        //Level Exit and Enterance Set

        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // add(title, constraints);
        
    }
   

   
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 

        //Draw Background
        pen.drawImage(bg,0,0,getWidth()/2, getHeight()/2,null);
       
    }
}
 