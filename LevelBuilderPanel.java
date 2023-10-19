package Levels.Managers;
//import Characters.PlayerCharacter;
import Levels.GameLevels.MurphysRoom2;
import Objects.Rect;
import Objects.Wall;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/** Initialize a level inhertiting from JPanel*/
public class LevelBuilderPanel extends JPanel implements KeyListener, Runnable{
    
    Thread t;
    // movement vars
    boolean[] pressing = new boolean[1024];

    static final int UP = KeyEvent.VK_UP;
    static final int DN = KeyEvent.VK_DOWN;
    static final int LT = KeyEvent.VK_LEFT;
    static final int RT = KeyEvent.VK_RIGHT;
    static final int Q = KeyEvent.VK_Q;

    //Level Vars
    private LevelBuilderPanel enterance = null; // node ahead
    private LevelBuilderPanel exit = null; // node behind
    protected GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
    protected Rect p1 = new Rect(100,100, 50,50 ); 
    
    //
    protected Wall [] wall = new Wall[]{new Wall(100, 10, 700, 80),new Wall(150, 500, 100, 150),new Wall(790, 70, 100, 150)};
    MurphysRoom2 murphysRoom2 = new MurphysRoom2(wall);
     
    

    // background
    protected Image bg;
    
   
    public void init()
        {		
            setLayout(new GridBagLayout());
           
           System.out.println("Init method in LevelBuilder activated");
            addKeyListener(this);

            requestFocus();
            
            t = new Thread(this);

            t.start();
        }
        
    @Override
        public void run() {

            while(!pressing[Q]){ // if there is a player character on this level
                //System.out.println("P1: Xpos: "+ (p1.x) + " Ypos: "+ (p1.y));
                if (pressing[UP]) p1.moveBy(0,-5);
                if (pressing[DN]) p1.moveBy(0,5);
                if (pressing[RT]) p1.moveBy(5,0);
                if (pressing[LT]) p1.moveBy(-5,0);

                // colision detection
                for(int i = 0; i < wall.length; i++)
			{
				if(p1.overlaps(wall[i]))
				{
                   //System.out.println("Pushing player out of wall "+ i);
					p1.pushedOutOf(wall[i]);
				
				}
			}
			
                repaint();
                try{
                    Thread.sleep(15);

                }catch(Exception e){

                }
            }
            System.exit(0);
            
        }
    @Override
        public void paintComponent(Graphics pen){
            super.paintComponent(pen);
            pen.clearRect(0, 0, getWidth(), getHeight()); // temp val
            
            murphysRoom2.draw(pen); // draw the level
            pen.setColor(Color.GREEN);
            p1.draw(pen);
	    }
       

    // getters and setters
    public LevelBuilderPanel getEnterance() {
        return enterance;
    }
    public LevelBuilderPanel getExit() {
        return exit;
    }  

    public void setEnterance(LevelBuilderPanel enterance) {
        this.enterance = enterance;
    }
    public void setExit(LevelBuilderPanel exit) {
        this.exit = exit;
    }
     //KEY LISTENER
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_Q)
            pressing[Q] = true;

        pressing[e.getKeyCode()] = true;

    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressing[e.getKeyCode()] = false;

    }

    public Thread getT() {
        return t;
    }

    public boolean[] getPressing() {
        return pressing;
    }

    public static int getUp() {
        return UP;
    }

    public static int getDn() {
        return DN;
    }

    public static int getLt() {
        return LT;
    }

    public static int getRt() {
        return RT;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    // public PlayerCharacter getP1() {
    //     return p1;
    // }

    public Image getBg() {
        return bg;
    }

    /** Set the background image for a level */
    public void setBg(String path) {
       bg = Toolkit.getDefaultToolkit().getImage(path);;
    }
   
}
