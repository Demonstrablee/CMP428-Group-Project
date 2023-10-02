package Levels;
import javax.swing.*;
import java.awt.*;

public class MurphysRoom extends JPanel { 
    //drawing methods
    GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
    JLabel title = new JLabel("MURPHYS ROOM");
   
    public MurphysRoom(){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK); // set pane to black
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);
        
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 

    }

    
}
 