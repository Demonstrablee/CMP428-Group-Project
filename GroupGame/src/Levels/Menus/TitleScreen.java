package Levels.Menus;
import javax.swing.*;

import Levels.Managers.Level2;

import java.awt.*;


public class TitleScreen extends Level2{ 
    JLabel title = new JLabel("TITLE SCREEN");
    JButton[] titleButtons;

    public TitleScreen(JButton[] menuButtons){
        super(null,null, "titleScreen");
        setBg("GroupGame\\src\\images\\bg_classroom01.jpg");
        
        //wall = new Wall[]{new Wall(10, 101, 100, 80)}; // temp to make errors stop in game loop
        this.titleButtons = menuButtons;

        constraints = new GridBagConstraints();  
        constraints.gridx = 0;
        constraints. gridy = 0;
        add(title, constraints);

        int i = 1;
        for(JButton button : menuButtons){ 
                constraints = new GridBagConstraints();  
                constraints.gridx = 0;
                constraints. gridy = i;
                add(button, constraints);
                i++;
            }
        



    }
    @Override
        public void paintComponent(Graphics pen){  //method for painting
            super.paintComponent(pen);
            pen.clearRect(0, 0, getWidth(), getHeight());
            pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
            title.repaint();
            
            for(JButton button : titleButtons){button.repaint();}
        }
    
        
  
   
   
    

    
}
