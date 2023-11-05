package Levels.GameLevels;
import javax.swing.*;

import Levels.Managers.Level2;
import Objects.Wall;

import java.awt.*;

public class HubGilletHall extends Level2 { 
    GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element    
    JLabel title = new JLabel("HUB GILLET");
    
    public HubGilletHall(Level2 enter, Level2 exit){
        super(enter, exit, "hubGilletHall");
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK); // set pane to black
        wall = new Wall[]{new Wall(0, 50, 1920, 80), new Wall(0, 500, 1920, 80)};

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);
        
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 

    }
}
 