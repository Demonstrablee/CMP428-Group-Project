package Levels.Managers;
import Characters.Characters.PlayerCharacter;
import Levels.GameLevels.Hallway01;
import Levels.GameLevels.HubGilletHall;
import Levels.GameLevels.MurphysRoom2;
import Levels.Menus.OptionsMenu;
import Levels.Menus.PauseMenu;
import Levels.Menus.SaveMenu;
import Levels.Menus.TitleScreen;
import Objects.Wall;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;

/** Initialize a level inhertiting from JPanel*/
public class LevelBuilderPanel extends JPanel implements KeyListener, Runnable, ActionListener{
    
    Thread t;

    //Level Vars
    LevelBuilderPanel enterance = null; // node ahead
    LevelBuilderPanel exit = null; // node behind

    //Buttons
    static JButton [] pauseMButtons = {new JButton("RESUME"), new JButton("SAVE GAME"), new JButton("OPTIONS"), new JButton("RETURN TO TITLE SCREEN"),new JButton("QUIT GAME")};
    static JButton [] backButtons = {new JButton("BACK"),new JButton("BACK"),new JButton("PAUSE")};
    static JButton [] titleButtons = {new JButton("START"), new JButton("OPTIONS"), new JButton("QUIT")};

    //Layout
    CardLayout cLayout0 = new CardLayout();
    
    //Objects
    static PlayerCharacter p1 = new PlayerCharacter(100,200, 50,50); // so all levels can share same character
    Wall [] wall; // get walls for refrence and collison detection

    //Menus
    static TitleScreen titleScreen = new TitleScreen(titleButtons); // 0
    static PauseMenu pauseMenu = new PauseMenu(pauseMButtons); //1
    static OptionsMenu optionsMenu = new OptionsMenu(backButtons[1]); // 2
    static SaveMenu saveMenu = new SaveMenu(backButtons[0]); //3
    

    //Levels
    static MurphysRoom2 murphysRoom2; //4
    static Hallway01 hallway01; //5
    static HubGilletHall hubGilletHall; // 6

    // Movement vars
    boolean[] pressing = new boolean[1024];
    boolean isPaused = false; // when the game starts in paused state while in any menu
    boolean isOver = false; // for later implentation 

    static final int UP = KeyEvent.VK_UP;
    static final int DN = KeyEvent.VK_DOWN;
    static final int LT = KeyEvent.VK_LEFT;
    static final int RT = KeyEvent.VK_RIGHT;
    static final int Q = KeyEvent.VK_Q;

    //change the displayed level
    Level2 currLevel; // for paintmethod
    String levIndex; // for layout manager
 

   

    public void init()
        {   
           //setDoubleBuffered(true);

           System.out.println("Init method in LevelBuilder activated");
            
           loadElements();
           
           addKeyListener(this);

            requestFocus();
            
            t = new Thread(this);
            

            t.start();

        }

        public void loadElements(){

            setLayout(cLayout0); 

            //Intializing
            murphysRoom2 = new MurphysRoom2(backButtons[2], null,hallway01);
            hallway01 = new Hallway01(murphysRoom2, hubGilletHall);
            hubGilletHall = new HubGilletHall(hallway01, null);

    

            add(titleScreen, "0");
            add(pauseMenu, "1");
            add(optionsMenu, "2");
            add(saveMenu, "3");
            add(murphysRoom2, "4");
            add(hallway01, "5");
            add(hubGilletHall, "6");
             

            

            //Add action listeners to each button
            for(JButton button : backButtons){button.addActionListener(this);}
            for(JButton button : titleButtons){ button.addActionListener(this);}
            for(JButton button : pauseMButtons){ button.addActionListener(this);}
            
            // game state variables
            currLevel = murphysRoom2;
            levIndex = "4";
            isPaused = false;
        }
      
    @Override
        public void run() {
            
            System.out.println("Game Started");
            while(true){ // if there is a player character on this level
            

                
            if(!isPaused){ // all the menus dont have walls for collisons
                //System.out.println("Unpaused");
                //System.out.println("P1: Xpos: "+ (p1.x) + " Ypos: "+ (p1.y));
                if (pressing[UP]) p1.moveBy(0,-5);
                if (pressing[DN]) p1.moveBy(0,5);
                if (pressing[RT]) p1.moveBy(5,0);
                if (pressing[LT]) p1.moveBy(-5,0);

                
                // colision detection
                wall = currLevel.getWalls();
                for(int i = 0; i < wall.length; i++){
                    if(p1.overlaps(wall[i]))
                    {
                    //System.out.println("Pushing player out of wall "+ i);
                        //p1.pushedOutOf(wall[i]);
                        p1.x = 100;
                        p1.y = 200;
                    }
                    
                }

                 

                } 
                    
                // Going to diffrent levels based on currLevel and position
                 // Exiting 
                if(p1.x == 300){    
                    if(currLevel.equals(murphysRoom2)){ 
                        levIndex = "5";
                        currLevel = hallway01;
                        System.out.println("Entering the Hallway...");
                    }
                   }

                    
                   //Entering
                    if(p1.x == 600){
                        if (currLevel.equals(hallway01)){
                        levIndex = "4";
                        currLevel = murphysRoom2;
                        System.out.println("Entering Murphy's Room...");
                        }
                    }


                repaint(); // draw the panel

                cLayout0.show(this, levIndex); // show the panel

                try{
                    Thread.sleep(15);

                }catch(Exception e){

                }
            }
             
            
        }
    @Override
        public void paint(Graphics pen){
            //pen.clearRect(0, 0, getWidth(), getHeight()); // temp val
            currLevel.paintComponent(pen); // draw the level using its own paint method
            if(!isPaused){ // draw player character if you are playing 
                p1.setColor(Color.RED);
                p1.draw(pen);
                
            }
	    }
    
       
     //KEY LISTENER
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());  

        pressing[e.getKeyCode()] = true;

    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressing[e.getKeyCode()] = false;

    }



    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        Object buttonClicked = e.getSource();
        
          
		if(buttonClicked == titleButtons[0] || buttonClicked == pauseMButtons[0]) {// go to resume
            isPaused = false;
            cLayout0.show(this, "4");
            currLevel = murphysRoom2;
            levIndex = "4";
            
            
        } 
       else if(buttonClicked == pauseMButtons[1]){ // go to save 
            cLayout0.show(this, "3"); 
            currLevel = saveMenu;
            levIndex = "3";
        }
       else if(buttonClicked == titleButtons[1] || buttonClicked == pauseMButtons[2]){// go to options
            cLayout0.show(this, "2");
            currLevel = optionsMenu;
            levIndex = "2";
       }
       else if(buttonClicked == pauseMButtons[3] || buttonClicked == backButtons[1] && isPaused){ // back to title screen
            cLayout0.show(this, "0");
            currLevel = titleScreen;
            levIndex = "0";  
            isPaused = true;
        }
       else if(buttonClicked == pauseMButtons[4]|| buttonClicked == titleButtons[2]){ // quit
            System.exit(0);
        } 
       else if(buttonClicked == backButtons[0]|| (buttonClicked == backButtons[1] && isPaused)|| buttonClicked == backButtons[2]){ // pause screen
            cLayout0.show(this, "1"); // show the pause menu
            currLevel = pauseMenu;
            levIndex = "1";
            isPaused = true;
            System.out.println("Paused");
        } 
       
        

        }
}
