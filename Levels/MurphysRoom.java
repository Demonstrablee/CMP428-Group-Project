package Levels;
import javax.swing.*;
import java.awt.*;

public class MurphysRoom extends JPanel { 
    //drawing methods

    public MurphysRoom(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
        setBackground(Color.BLACK); // set pane to black

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
 