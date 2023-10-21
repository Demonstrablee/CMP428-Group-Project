package Levels.Managers;

import Objects.Wall;

import java.awt.*;

import javax.swing.JPanel;

//import Characters.Characters.PlayerCharacter;

/** Initialize a level inhertiting from JPanel*/
public abstract class Level2 extends JPanel{

    //Level Vars
    private Level2 enterance = null; // node ahead
    private Level2 exit = null; // node behind
    
    protected GridBagConstraints constraints = new GridBagConstraints();

    //Background
    protected Image bg;
    String bgPath;

    //Level Variables
    protected Wall [] wall;
    //public PlayerCharacter p1;


    public Level2(Level2 enter, Level2 exit){
        setLayout(new GridBagLayout());
        this.enterance = enter;
        this.exit = exit;
    }




    public Wall[] getWalls() {
        return wall;
    }



 @Override
    public void paintComponent(Graphics pen){
           
	}

         

    // getters and setters
    public Level2 getEnterance() {
        return enterance;
    }
    public Level2 getExit() {
        return exit;
    }  

    public void setEnterance(Level2 enterance) {
        this.enterance = enterance;
    }
    public void setExit(Level2 exit) {
        this.exit = exit;
    }

    public String getBgPath() {
        return bgPath;
    }

    

    /** Set the background image for a level */
    public void setBg(String path) {
        bgPath = path;
        bg = Toolkit.getDefaultToolkit().getImage(path);;
    }
   
}
