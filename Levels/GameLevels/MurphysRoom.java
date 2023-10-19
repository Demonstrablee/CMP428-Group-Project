// package Levels.GameLevels;
// import java.awt.*;


// import javax.swing.JButton;
// import javax.swing.JPanel;


// import Levels.Managers.Level;

// import Objects.Wall;

// public class MurphysRoom extends Level{ 
//     JPanel health = new JPanel();
//     JButton title = new JButton("MURPHYS ROOM");
//     JButton b = new JButton("Button2");
//     JButton csButton;
//     //Level [] menus; // acess all the game menus

//     public MurphysRoom(Color c, Level entLevel, Level exLevel, JButton pauseButton){
//         super(c,entLevel,exLevel);
//         csButton = pauseButton;

//         setBg("images/bg_classroom02.jpg");
        
//         constraints = new GridBagConstraints();
//         constraints.gridx = 0;
//         constraints.gridy = -1;
//         //title.setVisible(false);
        
        
//         constraints = new GridBagConstraints();
//         constraints.gridx = 0;
//         constraints.gridy = -2;
//         //add(b,constraints);

//         constraints = new GridBagConstraints();
//         health.setBackground(Color.RED);
//         health.setSize(1000, 600);

//         constraints.gridx = 0;
//         constraints.gridy = 0;
//         constraints.ipadx = 900;
//         constraints.ipady = 110;
//         //constraints.fill = GridBagConstraints.VERTICAL;
//         //constraints.anchor = GridBagConstraints.PAGE_START;
//         add(csButton,constraints);



//     }
   
    
//     @Override
//     public void paint(Graphics pen){  //method for painting
//         pen.setColor(Color.GREEN);
//         pen.clearRect(0, 0, getWidth(), getHeight());
//         pen.drawImage(bg,0,0,getWidth(), getHeight(),null); // draw background
//         pen.setColor(Color.RED);
//         b.repaint();
//         csButton.repaint();
        
//         for(Wall wall : wall)

//             wall.draw(pen);

//         //p1.draw(pen); // player from level class
//     }


    


  

    
// }
 