
package Levels.Menus;
import javax.swing.*;

import Levels.Managers.Level2;

import java.awt.*;


public class PauseMenu extends Level2{ 
    JLabel title = new JLabel("PAUSE");
    JButton [] pauseMButtons;

    public PauseMenu(JButton [] pauseMButtons){
        super(null,null);
        setBg("images\\bg_pause02.jpg");

        // adding components to the screen
        this.pauseMButtons = pauseMButtons;

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title,constraints);
     
        int i = 1;
        for(JButton button : pauseMButtons){ 
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
        for(JButton button : pauseMButtons){button.repaint();}
       
        
    }

    
}
