package Levels.GameLevels;
import Levels.Managers.Level;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hallway01 extends Level implements ActionListener { 

    public Hallway01(Color c, Level entLevel, Level exLevel){
        super(c,entLevel,exLevel); 
        
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    
}
 