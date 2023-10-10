package Levels.GameLevels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Levels.Managers.Level;

public class MurphysRoom extends Level implements ActionListener { 

    public MurphysRoom(Color c, Level entLevel, Level exLevel){
        super(c,entLevel,exLevel);
        
    }
    

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 
        //pen.setColor(Color.GREEN);
        //pen.drawRect(1000,1000, 40, 40);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    
}
 