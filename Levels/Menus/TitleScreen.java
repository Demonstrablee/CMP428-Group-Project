package Levels.Menus;
import javax.swing.*;

import Levels.Managers.Level;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends Level implements ActionListener{ 
    //drawing methods
    JButton button = new JButton("START");
    JLabel title = new JLabel("UNTITLED GAME");
    int dispWidth;
    int dispHeight;


    public TitleScreen(){
        super(Color.GRAY);
        
        button.addActionListener(this);
        
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);

        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(button, constraints);

    }
    @Override
        public void paintComponent(Graphics pen){  //method for painting
            super.paintComponent(pen);//component that does the painting 

        }
        
    @Override
    public void actionPerformed(ActionEvent e){
        
        System.out.print("Button Clicked");
        System.exit(0);
    }
   
   
    

    
}