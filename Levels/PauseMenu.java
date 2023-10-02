package Levels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends JPanel implements ActionListener{ 
    JLabel title = new JLabel("PAUSE");
    
    public PauseMenu(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
        setBackground(Color.ORANGE); // set pane to black
// 
        //nButtons = arr;
        // adding components to the screen
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);
    
        
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    
}
 