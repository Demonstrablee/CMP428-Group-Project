package Levels.GameLevels;
import javax.swing.*;

import Characters.Characters.Enemy;
//import Characters.Characters.PlayerCharacter;
import Levels.Managers.Level2;
import Objects.HealthStation;
import Objects.Wall;

import java.awt.*;

public class Hallway01 extends Level2 { 
    GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
    JLabel title = new JLabel("HALLWAY"); 
    
    public Hallway01(Level2 enter, Level2 exit){
        super(enter, exit, "hallway01");
        setBg("GroupGame\\src\\images\\bg_hallway01.jpg"); // set pane to black
        wall = new Wall[]{new Wall(0, 50, 1920, 80), new Wall(0, 500, 1920, 80)};
       
        //ENTERANCE AND EXITS
        setLevelEnterPos(new int[] {200,490,100,25});
        setLevelExitPos(new int[] {900,300,25,100});
        
        setHealthStation(new HealthStation(100, 300));
        // Setting enemys array
        enemies = new Enemy[] {new Enemy(900, 100, 100, 100),new Enemy(900, 400, 100, 100)} ;

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
       
  
        for(Wall walls : wall){
            walls.setColor(Color.ORANGE);
            walls.draw(pen);
        }

         for(Enemy enemy : enemies){
            enemy.draw(pen);
        }

        // draw healthstation
        healthStation.draw(pen);
        
       // draw enterance and exits
        dRectEnter.draw(pen);
        dRectEx.draw(pen);

    }
    }

    

 