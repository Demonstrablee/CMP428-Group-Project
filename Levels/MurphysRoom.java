package Levels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Levels.Managers.Level;

public class MurphysRoom extends Level implements ActionListener { 

    public MurphysRoom(){
        super(Color.BLACK); // set pane to black
        
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
 