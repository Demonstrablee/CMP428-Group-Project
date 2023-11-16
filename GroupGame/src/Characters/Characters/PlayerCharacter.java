package Characters.Characters;


import java.awt.Color;
import java.awt.Graphics;


public class PlayerCharacter extends Character{
    // THESE POSES MUST BE IN THE CORRECT ORDER TO WORK IN THE ANIMATION CLASS {UP, DOWN, LEFT, RIGHT, IDLE} YOU Can name them whatever but the order contributes to what animation is played
    static String [] pose = new String[] {"UP", "DOWN", "LT", "RT","IDLE"}; 

    public PlayerCharacter(int x, int y, int w, int h) {
        super("MC",pose,7,0,"png",x, y, w, h);
        c = Color.RED;
    }

    @Override
    public void draw(Graphics pen){
            super.draw(pen);
    }

    
   
    

    
}
