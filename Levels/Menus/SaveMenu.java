package Levels.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;

import Levels.Managers.Level;


public class SaveMenu extends Level {
    JLabel title = new JLabel("SAVE GAME");
    public SaveMenu(){
        super(Color.DARK_GRAY); 

        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);




        
    }
   

   
     @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 


    }
}
