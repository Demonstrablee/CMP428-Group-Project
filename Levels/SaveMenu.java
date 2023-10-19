// package Levels.Menus;

// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.GridBagConstraints;

// import javax.swing.JButton;
// import javax.swing.JLabel;

// import Levels.Managers.Level;


// public class SaveMenu extends Level {
//     JLabel title = new JLabel("SAVE GAME");
//     JButton backButton;
//     public SaveMenu(JButton backButton){
//         super(Color.DARK_GRAY); 
//         this.backButton = backButton;

//         constraints.anchor = GridBagConstraints.PAGE_START;
//         constraints.gridx = 0;
//         constraints.gridy = 0;
//         add(title, constraints);

//         constraints.anchor = GridBagConstraints.PAGE_START;
//         constraints.gridx = 0;
//         constraints.gridy = 0;
//         add(backButton, constraints);

        
//     }
    
   

//      @Override
//     public void paint(Graphics pen){  //method for painting
//         pen.setColor(Color.RED);
//         pen.clearRect(0, 0, getWidth(), getHeight());
//         pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
//         title.repaint();
//         backButton.repaint();

//     }
// }
