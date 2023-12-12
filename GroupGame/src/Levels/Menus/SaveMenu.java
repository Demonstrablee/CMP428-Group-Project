package Levels.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

import Game.Game;
import Game.GameScreen;
import Levels.Managers.Level2;




public class SaveMenu extends GameScreen {
    JLabel title = new JLabel("SAVE");


    public SaveMenu(Game game, JButton backButton){
        super(game, "saveMenu");

        //BACKGROUND
        //setBg("black01.jpg");
        setBounds(0, 0, 1280, 720);
        setBackground(Color.lightGray);


        // adding components to the screen
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title,constraints);

        
        constraints = new GridBagConstraints();  
        constraints.gridx = 0;
        constraints. gridy = 1;
        constraints.insets = new Insets(3, 5, 5, 5);
        add(backButton, constraints);
        
            
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
       
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        pen.setColor(Color.BLACK);
      
        
        
    }
}
