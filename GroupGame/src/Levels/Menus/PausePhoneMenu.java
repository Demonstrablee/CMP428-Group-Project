
package Levels.Menus;
import javax.swing.*;

import Levels.Managers.Level2;

import Objects.Wall;

import java.awt.*;

import javax.swing.border.Border;

public class PausePhoneMenu extends Level2{ 
    JLabel title = new JLabel("PHONE");
    JButton[] pauseMButtons;
    Image phoneOutline;
    public PausePhoneMenu(JButton [] pauseButtons){ // This is the Phone
        super(null,null, "pauseMenu");

        //BACKGROUND
        setBg("bg_pause02.jpg");
        phoneOutline = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/outline_phone.png");
        wall = new Wall[]{new Wall(10, 101, 100, 80)}; // temp to make errors stop

        // adding components to the screen
        this.pauseMButtons = pauseButtons;

        //Adding elements to the panel
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(title,constraints);
     
        int i = 1;
        for(JButton button : this.pauseMButtons){ 
                constraints = new GridBagConstraints();  
                constraints.gridx = 0;
                constraints. gridy = i;
                constraints.insets = new Insets(3, 5, 5, 5);
                add(button, constraints);
                i++;
            }
        Border phone = BorderFactory.createLineBorder(Color.RED); //https://www.youtube.com/watch?v=Eb2QydjQvV4
        this.setBorder(phone); // add the boarder to this JPanel
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
       // pen.clearRect(0, 0, getWidth(), getHeight());
        
        //Draw Background
        pen.drawImage(bg,getWidth()/2 - 250,0,500, getHeight(),null);
        pen.drawImage(phoneOutline, getWidth()/2 - 250,0, 500, getHeight(), null);
        // pen.drawRect(getWidth()/2 - 250,0,500,getHeight());
        //Draw Buttons and Title
        title.repaint();
        for(JButton button : pauseMButtons){
            pen.drawString("QUIT", button.getX()+ 5, button.getY()+10);
            button.repaint();
            
        }
       
        
    }

    
}
