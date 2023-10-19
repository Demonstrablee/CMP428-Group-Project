// package Levels.Menus;
// import javax.swing.*;

// import Levels.Managers.Level;
// import java.awt.*;


// public class TitleScreen extends Level{ 
//     JButton [] titleButtons;
//     JLabel title = new JLabel("TITLE SCREEN");
//     int dispWidth;
//     int dispHeight;



//     public TitleScreen(JButton [] titleButtons){
//         super(Color.GRAY);
//         setBg("images\\bg_classroom01.jpg");
//         this.titleButtons = titleButtons;

//         constraints = new GridBagConstraints();  
//         constraints.gridx = 0;
//         constraints. gridy = 0;
//         add(title, constraints);

//         int i = 1;
//         for(JButton button : titleButtons){ 
//                 constraints = new GridBagConstraints();  
//                 constraints.gridx = 0;
//                 constraints. gridy = i;
//                 add(button, constraints);
//                 i++;
//             }
        



//     }
//     @Override
//         public void paint(Graphics pen){  //method for painting
//             pen.clearRect(0, 0, getWidth(), getHeight());
//             pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
//             //title.repaint();
//             for(JButton button : titleButtons){button.repaint();}
//         }
    
        
  
   
   
    

    
// }
