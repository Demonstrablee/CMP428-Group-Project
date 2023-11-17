package Levels.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import Levels.Managers.Level2;
// import Objects.Wall;

public class GameOverMenu extends Level2 {

    JLabel title = new JLabel("GAME OVER");
    JButton[] gameOverOptions;

    public GameOverMenu(JButton [] gameOverOptions){
        super(null,null, "gameOverMenu");

        //BACKGROUND
        //setBg("black01.jpg");
        setBounds(0, 0, 1280, 720);
        

        
        // Add Buttons 
        this.gameOverOptions = gameOverOptions;
        // adding components to the screen
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title,constraints);
     
        int i = 1;
        for(JButton button : this.gameOverOptions){ 
                constraints = new GridBagConstraints();  
                constraints.gridx = 0;
                constraints. gridy = i;
                constraints.insets = new Insets(3, 5, 5, 5);
                add(button, constraints);
                i++;
            }
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
        //pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        pen.setColor(Color.BLACK);
        // Draw the button
        //title.repaint();
        //for(JButton button : gameOverOptions){button.repaint();}
        
        
    }
    
}
