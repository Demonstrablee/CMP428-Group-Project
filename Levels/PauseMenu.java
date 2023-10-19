// package Levels.Menus;
// import javax.swing.*;

// import Levels.Managers.Level;

// import java.awt.*;


// public class PauseMenu extends Level{ 
//     //JLabel title = new JLabel("PAUSE");
//     JButton [] pauseMButtons;

//     public PauseMenu(JButton [] pauseMButtons){
//         super(); // set pane to black
//         setBg("images/bg_pause02.jpg");
//         // adding components to the screen
//         this.pauseMButtons = pauseMButtons;

//         // constraints.anchor = GridBagConstraints.PAGE_START;
//         // constraints.gridx = 0;
//         // constraints.gridy = 0;
//         // add(title, constraints);
     
//         int i = 0;
//         for(JButton button : pauseMButtons){ 
//                 constraints = new GridBagConstraints();  
//                 constraints.gridx = 0;
//                 constraints. gridy = i;
                
//                 add(button, constraints);
     
//                 i++;
//             }
//     }
   

//     @Override
//     public void paint(Graphics pen){  //method for painting
//         pen.setColor(Color.RED);
//         pen.clearRect(0, 0, getWidth(), getHeight());
//         pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
//         for(JButton button : pauseMButtons){ button.repaint();}
//         // p1.draw(pen); // player from level class
        
//     }

    
// }
 