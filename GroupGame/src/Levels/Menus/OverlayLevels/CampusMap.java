package Levels.Menus.OverlayLevels;
import javax.swing.*;
import javax.swing.border.Border;

import Game.Game;
import Game.GameScreen;


import java.awt.*;

public class CampusMap extends GameScreen {
    //JLabel title = new JLabel("HUB GILLET");
    //Gillet Hall, Cafeteria, Near Quad
    int locations [][] = new int [][] {{370,270}, {310,210}, {530,240}};
    int loc; // Gillete Hall 

    Image phone = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/icons/Arrow Left_Lightgreen.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
    ImageIcon phoneIcon = new ImageIcon(phone);

    public CampusMap(Game game, JButton mapButton){
        super(game, "campusMap");
        
        //BACKGROUND
        setBackground(Color.BLACK); // set pane to black
        setBg("Lehman_Map.png");
        setBounds(60,90,1150,500); // set the bounds of the panel

        //Level Exit and Enterance Set

        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // add(title, constraints);
        
        Border phone = BorderFactory.createLineBorder(Color.GRAY,10); //https://www.youtube.com/watch?v=Eb2QydjQvV4
        this.setBorder(phone); // add the boarder to this JPanel

        // MAP BUTTON
        mapButton.setIcon(phoneIcon);
        mapButton.setBounds(130, 70,62,62); 

        add(mapButton);
    }

        
   
    /** Set the current location to appear on the map  */
    public void setLocation(String loString){
        switch (loString) {
            case "cafeteria":
            case "hallway01": // not working
                this.loc = 1;
                break;
        
            default: // location by default
                this.loc = 0;
                break;
        }
        
    }
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 

        //Draw Background
        pen.drawImage(bg,40,35,getWidth()-80, getHeight()-70,null);
        pen.setColor(Color.RED);
        // change the thickness 
        Graphics2D pen2D = (Graphics2D)pen; 
        pen2D.setStroke(new BasicStroke(5));

        
        // draw players current location
        pen.drawOval(locations[loc][0], locations[loc][1], 30, 30);
    }
}
 