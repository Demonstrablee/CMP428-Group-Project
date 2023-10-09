package Levels.Menus;
import javax.swing.*;

import Levels.Menus.Managers.Level;

import java.awt.*;

public class PauseMenu extends Level implements ActionListener{ 
    JLabel title = new JLabel("PAUSE");
    JButton [] nButtons = {new JButton("RESUME"), new JButton("SAVE GAME"), new JButton("OPTIONS"), new JButton("RETURN TO TITLE SCREEN"),new JButton("QUIT GAME"), new JButton("PAUSE") };
    
    public PauseMenu(){
        super(Color.ORANGE); // set pane to black

        // adding components to the screen
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);
     
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 

      // the canvas is in Class02
       //pen.setColor(Color.RED);
        //pen.drawRect(50,3,1,100);
        //drawDot(50,50, pen);
        //drawHLine(50,500,70,pen);
        //drawVline(70,100,200,pen);

        // pen.setColor(Color.GREEN);
        //drawDILine(200, 500, 200, 300, pen);
        // drawDILine(200, 200, 400, 800, pen); // straight line

    }

    
}
 