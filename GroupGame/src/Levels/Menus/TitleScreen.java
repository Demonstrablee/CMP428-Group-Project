package Levels.Menus;
import javax.swing.*;

import Levels.Managers.Level2;

import java.awt.*;


public class TitleScreen extends Level2{ 
    JLabel title = new JLabel("TITLE SCREEN");
    JButton[] titleButtons;
    public TitleScreen(JButton[] menuButtons){
       
        super(null,null, "titleScreen");
        setDoubleBuffered(true);
        
        //BACKGROUND
        setBg("bg_classroom01.jpg");
        
        // Getting the buttons for the Menu
        this.titleButtons = menuButtons;

        //Adding buttons to panel
        constraints = new GridBagConstraints();  
        constraints.gridx = 0;
        constraints. gridy = 0;
        add(title, constraints);

        int i = 1;
        for(JButton button : menuButtons){ 
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
            
            repaint(100,100,4000,4000);
            
            //for(JButton button : titleButtons){button.repaint();}
        }
    
        
  
   
   
    

    
}
