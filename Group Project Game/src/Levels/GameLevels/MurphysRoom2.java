package Levels.GameLevels;
import java.awt.*;


import javax.swing.JButton;
import javax.swing.JPanel;

//import Characters.Characters.PlayerCharacter;
import Levels.Managers.Level2;
import Objects.Wall;

public class MurphysRoom2 extends Level2{ 
    JPanel health = new JPanel();
    JButton title = new JButton("MURPHYS ROOM");
    JButton b = new JButton("Button2");
 
    JButton pauseButton;
    

   

    //Level [] menus; // acess all the game menus
    GridBagConstraints constraints;

    int width = 1920;
    int height = 1080;
      
    
    public MurphysRoom2(JButton pauseButton, Level2 enter, Level2 exit){
        super(enter, exit);
        this.pauseButton = pauseButton;
        setBg("images\\bg_classroom02.jpg");
        //this.p1 = p1;
        wall = new Wall[]{new Wall(0, 50, 1920, 80), new Wall(0, 500, 1920, 80)};
        
        this.pauseButton = pauseButton;
        constraints = new GridBagConstraints();
        setBackground(Color.BLACK); // set pane to black
        constraints.gridx = 0;
        constraints.gridy = 0;

        add(this.pauseButton, constraints);
   
    }
    

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
        pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        pauseButton.repaint();
  
        for(Wall walls : wall){
            walls.setColor(Color.GREEN);
            walls.draw(pen);
        }
        // p1.setColor(Color.RED);
        // p1.draw(pen);
    }

    


  

    
}
 