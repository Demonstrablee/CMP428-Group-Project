package Levels.Managers;

import javax.swing.JPanel;
import java.awt.*;

public class Level extends JPanel{
    //linked list
    private Level enterance = null; // node ahead
    private Level exit = null; // node behind
    protected GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
    
    public Level(String Title){
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK); // set pane to black
        
    }   
    
    // secondary constructors
    public Level(Color color){
        setLayout(new GridBagLayout());
        setBackground(color); // set pane to black
    } 
    public Level(Color color, Level entLevel, Level exLevel  ){
        setLayout(new GridBagLayout());
        setBackground(color); // set pane to black
        enterance = entLevel;
        exit = exLevel;
    } 

    // getters and setters
    public Level getEnterance() {
        return enterance;
    }
    public Level getExit() {
        return exit;
    }  

    public void setEnterance(Level enterance) {
        this.enterance = enterance;
    }
    public void setExit(Level exit) {
        this.exit = exit;
    }
     
}
