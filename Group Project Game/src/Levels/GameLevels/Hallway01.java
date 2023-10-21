package Levels.GameLevels;
import javax.swing.*;

//import Characters.Characters.PlayerCharacter;
import Levels.Managers.Level2;
import Objects.Wall;

import java.awt.*;

public class Hallway01 extends Level2 { 
    GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
    JLabel title = new JLabel("HALLWAY"); 

    public Hallway01(Level2 enter, Level2 exit){
        super(enter, exit);
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK); // set pane to black
        wall = new Wall[]{new Wall(0, 50, 1920, 80), new Wall(0, 500, 1920, 80)};
        //this.p1= p1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);
        
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 
        super.paintComponent(pen);
        pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        //pauseButton.repaint();
  
        for(Wall walls : wall){
            walls.setColor(Color.GREEN);
            walls.draw(pen);
        }
        //p1.setColor(Color.RED);
        //p1.draw(pen);
    }
    }

    

 