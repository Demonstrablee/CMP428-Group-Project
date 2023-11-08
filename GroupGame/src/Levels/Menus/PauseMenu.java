
package Levels.Menus;
import javax.swing.*;

import Levels.Managers.Level2;

import Objects.Wall;

import java.awt.*;


public class PauseMenu extends Level2{ 
    JLabel title = new JLabel("PAUSE");
    JButton[] pauseMButtons;

    public PauseMenu(JButton [] pauseButtons){
        super(null,null, "pauseMenu");

        //BACKGROUND
        setBg("bg_pause02.jpg");
        
        wall = new Wall[]{new Wall(10, 101, 100, 80)}; // temp to make errors stop

        // adding components to the screen
        this.pauseMButtons = pauseButtons;

        //Adding elements to the panel
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title,constraints);
     
        int i = 1;
        for(JButton button : this.pauseMButtons){ 
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
       // pen.clearRect(0, 0, getWidth(), getHeight());
        
        //Draw Background
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        
        //Draw Buttons and Title
        title.repaint();
        for(JButton button : pauseMButtons){button.repaint();}
       
        
    }

    
}
