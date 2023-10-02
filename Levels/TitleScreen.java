package Levels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JPanel implements ActionListener{ 
    //drawing methods
    JButton button = new JButton("START");
    JLabel title = new JLabel("TITLE SCREEN UNTITLED GAME");
    
    int dispWidth;
    int dispHeight;


    public TitleScreen(){
 

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
        
        setBackground(Color.GRAY); // set pane to black
        
        button.addActionListener(this);
        
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title, constraints);

        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(button, constraints);

       

    }

    @Override
    public void actionPerformed(ActionEvent e){
        
        System.out.print("Button Clicked");

        System.exit(0);
    }
   
   
    

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 
       
        //pen.setColor(Color.RED);
        pen.drawRect(100,100, 700, 60);
       

        

    }
}
