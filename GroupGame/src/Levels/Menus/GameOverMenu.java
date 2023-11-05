package Levels.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JLabel;
import Levels.Managers.Level2;
// import Objects.Wall;

public class GameOverMenu extends Level2 {

    JLabel title = new JLabel("GAME OVER");
    JButton[] gameOverOptions;

    public GameOverMenu(JButton [] gameOverOptions){
        super(null,null, "gameOverMenu");

        setBg("GroupGame\\src\\images\\black01.jpg");
        
        
        //wall = new Wall[]{new Wall(10, 101, 100, 80)}; // temp to make errors stop

        // adding components to the screen
        this.gameOverOptions = gameOverOptions;

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
                add(button, constraints);
                i++;
            }
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
        pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        pen.setColor(Color.BLACK);
       // pen.fillRect(0, 0, getWidth(), getHeight());
        title.repaint();
        for(JButton button : gameOverOptions){button.repaint();}
        
        
    }
    
}
