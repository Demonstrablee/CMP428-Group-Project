package Levels.Menus;
import javax.swing.*;

import Levels.Managers.Level;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends Level implements ActionListener{ 
    JLabel title = new JLabel("UNTITLED GAME");
    int dispWidth;
    int dispHeight;


    public TitleScreen(){
        super(Color.GRAY);
        
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);

    }
    @Override
        public void paintComponent(Graphics pen){  //method for painting
            super.paintComponent(pen);//component that does the painting 

        }
        
    @Override
    public void actionPerformed(ActionEvent e){
   
    }
   
   
    

    
}
