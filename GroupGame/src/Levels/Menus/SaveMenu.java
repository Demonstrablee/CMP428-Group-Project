package Levels.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JLabel;

import Levels.Managers.Level2;




public class SaveMenu extends Level2 {
    JLabel title = new JLabel("SAVE GAME");
    JButton backButton;
    GridBagConstraints constraints;

    public SaveMenu(JButton backButton){
        super(null,null, "saveMenu");
        //wall = new Wall[]{new Wall(10, 101, 100, 80)}; // temp to make errors stop in game loop
       
        this.backButton = backButton;

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(backButton, constraints);

        
    }
    
   

     @Override
    public void paintComponent(Graphics pen){  //method for painting
        pen.setColor(Color.RED);
        //pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        //title.repaint();
        backButton.repaint();

    }
}
