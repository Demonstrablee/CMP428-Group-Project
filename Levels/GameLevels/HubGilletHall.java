package Levels.GameLevels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Levels.Managers.Level;

public class HubGilletHall extends Level implements ActionListener { 

    public HubGilletHall(){
        super(Color.gray);

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
 