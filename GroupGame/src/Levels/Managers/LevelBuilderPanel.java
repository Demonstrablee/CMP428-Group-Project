package Levels.Managers;

import Sprites.Characters.Enemy;
import Sprites.Characters.PlayerCharacter;
import Sprites.Characters.Student;
import Levels.GameLevels.Hallway01;
import Levels.GameLevels.MurphysRoom2;
import Levels.Menus.GameOverMenu;

import Levels.Menus.OptionsMenu;
import Levels.Menus.SaveMenu;
import Levels.Menus.TitleScreen;
import Levels.Menus.OverlayLevels.CampusMap;
import Levels.Menus.OverlayLevels.Inventory;
import Levels.Menus.OverlayLevels.PausePhoneMenu;
import Objects.HealthBar;
import Objects.HealthStation;
import Objects.Rect;
import Objects.Wall;
import UI.InterviewMode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

/**
 * Initializes a level inheriting from JPanel.
 */
public class LevelBuilderPanel extends JLayeredPane implements KeyListener, Runnable, ActionListener, MouseListener {
    
    Thread t;
    
    //Buttons
    Image phone = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/icons/Phone_Blue.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
    ImageIcon phoneIcon = new ImageIcon(phone);
    JButton inGameMenuButton = new JButton(phoneIcon);
    JButton mapButton = new JButton();

    JButton [] titleButtons = new JButton[3]; // number of buttons on each menu
    JButton [] pauseMButtons = new JButton[7];
    JButton [] toPauseButtons = new JButton[2];
    JButton [] gameOverButtons = new JButton[3];
    JButton [] inventoryButtons = new JButton[7]; // how many items are in the game

    
    //Objects
    PlayerCharacter p1 = new PlayerCharacter(300,300, 50,50); // so all levels can share same character
    Wall [] wall; // get walls for refrence and collison detection
    int health;
    Enemy [] enemies;
    Student [] students;
    HealthStation healthStation;
    
    Rect exit;
    Rect enter;
    int nExitOrEnterX;
    int nExitOrEnterY;

    //Menus
    static TitleScreen titleScreen; // 0
    static PausePhoneMenu pauseMenu; //1
    static OptionsMenu optionsMenu; // 2
    static SaveMenu saveMenu; //3
    static GameOverMenu gameOverMenu;
    static Inventory inventory;

    //Levels
    static MurphysRoom2 murphysRoom2 = new MurphysRoom2(null, null); //4
    static Hallway01 hallway01 = new Hallway01(null, null); //5
    
    static CampusMap campusMap; // 6

    //UI
    InterviewMode interviewMode = new InterviewMode();

    /**States when the game is paused state (happens while in any menu)*/
    boolean isPaused; 
    /** States whether or not the game is over  */
    boolean isOver = false;


    static final int UP = KeyEvent.VK_UP;
    static final int DN = KeyEvent.VK_DOWN;
    static final int LT = KeyEvent.VK_LEFT;
    static final int RT = KeyEvent.VK_RIGHT;
    static final int Q = KeyEvent.VK_Q;

    
    static final int W = KeyEvent.VK_W;
    static final int A = KeyEvent.VK_A;
    static final int S = KeyEvent.VK_S;
    static final int D = KeyEvent.VK_D;
    static final int U = KeyEvent.VK_U;
    
    /**the currrent level that is being displayed  */
    Level2 currLevel; // for paintmethod
    /**the level index for the current level being displayed used in the cardlayout*/
    String levIndex; // for layout manager
    /** the current non-menu level selected in the game */
    Level2 gameRoom;
    /** boolean represneting if the options menu will direct to the title screen or the pause menu */
    boolean titleOrPause;

   

    public void init()
        {   
           //setDoubleBuffered(true); (redundant)

           System.out.println("Init method in LevelBuilder activated");
            
           loadElements();
           
           addKeyListener(this);

           addMouseListener(this);

           requestFocus();
            
           t = new Thread(this);
            
           t.start();
        

        }

        /** Loads all the elements of the game, creating buttons menus, and levels, and adding 
         * them to the JPanel of the LevelBuilder Class
         */
        public void loadElements(){
            // GameWindow2.setPlayMusic(true); // play
            setBounds(0, 0, 1280, 720); // display size

            inGameMenuButton.addActionListener(this);
            inGameMenuButton.setFocusable(false);  // so important  stops the button form stealing focus from the keyboard
            inGameMenuButton.setBounds(1130, 70,62,62); // w and h are smaller than the image size so that the white background doesnt appear arround the button
            

            add(inGameMenuButton);
            inGameMenuButton.setVisible(false);
       
            mapButton.addActionListener(this);
            mapButton.setFocusable(false);  // so important  stops the button form stealing focus from the keyboard
            mapButton.setBounds(1130, 70,62,62); // w and h are smaller than the image size so that the white background doesnt appear arround the button
            
          
            // initiallizing buttons
            String [] buttonN = new String[] {"RESUME", "OPTIONS", "QUIT", "RETURN TO TITLE", "BACK", "SAVE GAME", "START", "TRY AGAIN", "INVENTORY", "MAP"};
            createButton(new String [] {buttonN[6],buttonN[1],buttonN[2]}, titleButtons); // TITLE SCREEN
            createButton(new String [] {buttonN[0],buttonN[5],buttonN[1],buttonN[3], buttonN[2],buttonN[8], buttonN[9]}, pauseMButtons); //PAUSE MENU 
            createButton(new String [] {buttonN[4],buttonN[4]}, toPauseButtons); // SAVE AND OPTIONS
            createButton(new String [] {buttonN[7],buttonN[3],buttonN[2]}, gameOverButtons); // GAME OVER

            createButton(new String[] {"BACK","WALLET","GATE 5 KEY","CHICKEN SAMMIE"}, inventoryButtons); // inventory items
           

            //Intializing
            titleScreen = new TitleScreen(titleButtons);
            pauseMenu = new PausePhoneMenu(pauseMButtons);
            optionsMenu = new OptionsMenu(toPauseButtons[1]);
            saveMenu = new SaveMenu(toPauseButtons[0]); 
            gameOverMenu = new GameOverMenu(gameOverButtons);
            campusMap = new CampusMap(mapButton); // technically a menu sort of
            inventory = new Inventory(inventoryButtons); // TODO NOT FINISHED
            // CAMPUS MAP
            


            //MURPHYS ROOM
            murphysRoom2.setExit(hallway01); // the address initially was null 

            //HALLWAY01
            hallway01.setEnterance(murphysRoom2);
            hallway01.setExit(campusMap);

           
            //campusMap.setEnterance(hallway01);
            
            
            //ADDING Levels To Level builder Panel
            
            add(inventory);
            add(campusMap);
            add(pauseMenu);

            add(titleScreen);
            add(optionsMenu);
            add(saveMenu);
            add(murphysRoom2);
            add(hallway01);
            
            add(gameOverMenu);
            
            add(interviewMode);
            
            
            // Game State variables AT START
            currLevel = titleScreen; // which room to draw currLevel and levLevel index are one to one (default: titleScreen)
            gameRoom =  murphysRoom2; // track of the ingame rooms that player traverses with p1 (default; murphysRoom)

            //levIndex = currLevel.toString(); // which room to display
            isPaused =  true; // is the game paused or not (default: true)
            titleOrPause = true; // at game start options goes to pause menu (default: true)
            isOver = false; // is the game over? (only have to change this for gameover window debug) (default: false)
             
            currLevel.setVisible(true); // whatever the stating level is set it to visible
            //campusMap.setVisible(true);
        }

      
    @Override
        public void run() {
            System.out.println("Game Started");
            while(true){ //game loop
                 
                gameLoop();

                repaint(); // draw the panel

                try{
                    Thread.sleep(15);

                }catch(Exception e){

                }  
            }
             
            
        }

        public void gameLoop(){
            if(isOver){
                    isPaused = true; // so it works in testing and in game
                    //gameRoom = gameOverMenu; // its a menu after all
                    currLevel.setVisible(false);
                    currLevel = gameOverMenu;
                    currLevel.setVisible(true);
                    
                }
                if(isPaused){ 
                    inGameMenuButton.setVisible(false);

                
                }

                // if you are not in a game menu    
                else if(isPaused == false || isOver == false){ // Game Menu exit
                    // System.out.println("P1: Xpos: "+ (p1.x) + " Ypos: "+ (p1.y));
                    // MUST BE GAME ROOM since the menus dont have exits and entrances
                    wall = gameRoom.getWalls();
                    enter = gameRoom.getEnterRect();
                    exit = gameRoom.getExitRect();
                    enemies = gameRoom.getEnemies();
                    health = p1.getHealthBar().getCurrentHealth(); // get the players health
                    students = gameRoom.getStudents();
                    healthStation = gameRoom.getHealthStation();
                    p1.setColor(Color.RED); // Player color will change depending on if hit or not
                    //inGameMenuButton.setVisible(true); // how to open the phone

                    //GAME OVER CONDITIONS
                    if(health == 0){
                        isOver = true;
                        isPaused = true;
                    }

                    // Player Movement
                    if (p1.isPressing(UP)){ p1.moveBy(0,-4);}
                    if (p1.isPressing(LT)){p1.goLT(-4); p1.moveBy(-4,0);}
                    if (p1.isPressing(DN)){ p1.moveBy(0,4);}
                    if (p1.isPressing(RT)){ p1.goRT(4); p1.moveBy(4,0);}

                    if (p1.isPressing(W)){ p1.moveBy(0,-4);}
                    if (p1.isPressing(A)){ p1.moveBy(-4,0);}
                    if (p1.isPressing(S)){ p1.moveBy(0,4);}
                    if (p1.isPressing(D)){ p1.moveBy(4,0);}
                    if(p1.isPressing(U)) p1.getInventory().useSelectedItem();


                     // TODO collision detection STILL NOT WORKING

                    if (wall != null)
                        for(int i = 0; i < wall.length; i++){
                            if(p1.overlaps(wall[i])){
                                //System.out.println("Pushing player out of wall "+ i);
                                 p1.pushedOutOf(wall[i]);}
                            }

                    // Player Damage
                    if (enemies != null) {
                        Enemy e1 = enemies[1]; // temporary a loop will kill the player immediately
                            if (p1.overlaps(e1)) {
                                p1.setColor(Color.GREEN);
                                p1.getHealthBar().damageTaken();
                            }

                           if (!p1.overlaps(e1)) p1.getHealthBar().damage();

                        // Player Healing
                        if(healthStation != null && p1.overlaps(healthStation))
                            p1.getHealthBar().increaseHealth(1);
                    }

                    // Student interactions
                    if(students != null) {
                        Student s = students[0];
                            if(p1.overlaps(s)) s.isSpeaking();
                            else s.isNotSpeaking();
                            // TODO: FIX NOT DISPLAYING ON JPANEL
                    }

                    // Going to different levels based on currLevel and position
                    // Exiting current level to new level

                    if(exit != null) {
                            if(exit.overlaps(p1)) {

                            //Put player intoi the exit to the next level
                            int [] exitPosition = currLevel.getExit().getLevelEntrancePos();//get position of enterance in next level

                            nExitOrEnterX = exitPosition[0] + exitPosition[2]/3;
                            nExitOrEnterY = exitPosition[1]- (int)(p1.h +20);
                            p1.setLocation(nExitOrEnterX, nExitOrEnterY); //  make play start infront of the exit

                            // THIS MUST HAPPEN AFTER PLAYER PLACEMENT
                            gameRoom = currLevel.getExit(); //

                             // Changing the level displayed on screen
                                if (!gameRoom.equals(campusMap)) {
                                    currLevel.setVisible(false);
                                    currLevel = gameRoom;
                                    currLevel.setVisible(true);
                                } else {
                                    // adjust the map so that when it is displayed it shows where the player actually is
                                    campusMap.setLocation(currLevel.toString());
                                    isPaused = true; // so that th3e hud doesnt get drawn
                                    campusMap.setVisible(true);
                                }

                            System.out.println("Entering " + currLevel.toString());}
                    }

                    //Entering a next level

                        if(enter != null) {

                            if(enter.overlaps(p1)) {

                                //put player infront of the entrance to the previous level
                                int [] enterPositon = currLevel.getEnterance().getLevelExitPos();//get position of exit in previous level
                                nExitOrEnterX = enterPositon[0] + enterPositon[2] / 3 ; //x + width/3
                                nExitOrEnterY = enterPositon[1] - (int)(p1.h + 20); //y
                                p1.setLocation(nExitOrEnterX, nExitOrEnterY); //  make play start infront of the exit


                                // THIS MUST HAPPEN AFTER PLAYER PLACEMENT
                                gameRoom = currLevel.getEnterance();

                                // Changing the level displayed on screen
                                if (!gameRoom.equals(campusMap)) {
                                    currLevel.setVisible(false);
                                    currLevel = gameRoom;
                                    currLevel.setVisible(true);
                                } else {
                                    campusMap.setVisible(true);
                                }

                                System.out.println("Entering "+ currLevel.toString());}

                        }

                        // Map Adjustments

                        // if (currLevel.equals(campusMap)){
                        //     campusMap.setLocation("cafeteria");
                        //     isPaused = true; // so that th3e hud doesnt get drawn
                        // }
                
                    }
        }
    @Override
        public void paint(Graphics pen){ // WOW rather than paint AWT make this paintComponent and everything is good  
            pen.clearRect(0, 0, getWidth(), getHeight());

            
            super.paint(pen);
             

            if(!isPaused) { // draw player character if you are playing (drawn on every gameRoom)
                // Draw the player
                p1.draw(pen);
                
                 // Student NPC Talk
                if(students != null) {
                    for(Student student: students)
                        student.talk(pen);
                }

                // PLAYER HUD
                p1.getHealthBar().draw(pen);
                p1.getInventory().draw(pen);
                inGameMenuButton.setVisible(true);
                
                
            }
	    }
    
        /** Create the buttons that will be in the menus  */
        public void createButton(String [] arr, JButton [] buttons){
            JButton nButton;
            Border border = BorderFactory.createBevelBorder(0);
            
        
            for (int i = 0 ; i < arr.length; ++i){
                nButton = new JButton(arr[i]);
        
                nButton.addActionListener(this);
                nButton.setFocusable(false);
                

                //Button Stylings

                nButton.setBorder(border);
                nButton.setBackground(Color.LIGHT_GRAY);
        
                nButton.setOpaque(true); // so that I can see the background
                // nButton.setRolloverEnabled(true);
                // nButton.setRolloverIcon(rolloverIcon);
                // nButton.setBorder(null); // get rid of the border on the button
                // nButton.setContentAreaFilled(false); // makes button transparent so imageICon can be only showing
                buttons[i] = nButton; // add button to array
            }
        }
       
     //KEY LISTENER
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        p1.setPressing(e.getKeyCode(), true);
       
        if(e.getKeyCode() == KeyEvent.VK_P){ // if P is pressed pause the game
            if(!isPaused){
                //currLevel.setVisible(false);
                currLevel = pauseMenu;
                currLevel.setVisible(true);
                //levIndex = currLevel.toString();
                isPaused = true; // pause the game
                System.out.println("Paused via key press");
            
            }else{ // go back to the game
                //currLevel.setVisible(false);
                //currLevel = pauseMenu;
                currLevel.setVisible(false); // should be pause menu
                //levIndex = currLevel.toString();
                isPaused = false; // pause the game
                System.out.println("UnPaused via key press");
        }
    }


    }
    @Override
    public void keyReleased(KeyEvent e) {
        p1.setPressing(e.getKeyCode(), false);
        p1.moving = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
       
        Object buttonClicked = e.getSource();
        SimpleSoundPlayer.playSound("GroupGame/src/music/button_click01.wav");// have to wait for audio to finish before action can happen
          
		if(buttonClicked == titleButtons[0] || buttonClicked == pauseMButtons[0]|| buttonClicked == gameOverButtons[0]) {// go to resume
          
            currLevel.setVisible(false); // make previous room invisible
            currLevel = gameRoom; // last game room (currLevel is what is used to draw the levels in the paint method) change levels
            currLevel.setVisible(true); // make current room visible

            if(buttonClicked == gameOverButtons[0]) // refill if it was a game over
                p1.getHealthBar().refillHealth();   //NEED TO SET THIS TO FULL otherwise game over condition will stay true

            // which level to switch to based on what the last game room you were in
            isPaused = false;
            titleOrPause = false; //game started make option menu go to the pause menu
            isOver = false;
        } 
       else if(buttonClicked == pauseMButtons[1]){ // go to save 
    
            currLevel.setVisible(false);
            currLevel = saveMenu;
            currLevel.setVisible(true);
       
        }
       else if(buttonClicked == titleButtons[1] || buttonClicked == pauseMButtons[2]){// go to options

            currLevel.setVisible(false);
            currLevel = optionsMenu;
            currLevel.setVisible(true);
  
       }
       else if(buttonClicked == pauseMButtons[3] || buttonClicked == toPauseButtons[1] && titleOrPause || buttonClicked == gameOverButtons[1]){ // back to title screen

            currLevel.setVisible(false);
            currLevel = titleScreen;
            gameRoom = murphysRoom2;
            currLevel.setVisible(true);

            p1.getHealthBar().refillHealth();   //NEED TO SET THIS TO FULL
            isPaused = true;
            isOver = false;
            titleOrPause = true; //game ended make pause menu go to the title menu
        }
       else if(buttonClicked == pauseMButtons[4]|| buttonClicked == titleButtons[2]|| buttonClicked == gameOverButtons[2] ){ // quit
            System.exit(0);
        } 
       else if(buttonClicked == inGameMenuButton|| 
       buttonClicked == toPauseButtons[0]||
       (buttonClicked == toPauseButtons[1] && !titleOrPause)|| 
       buttonClicked == toPauseButtons[0] || buttonClicked == mapButton || buttonClicked == inventoryButtons[0]){ // pause screen
      
            if(buttonClicked == mapButton){
                campusMap.setVisible(false);
                
             }
            else if(currLevel != gameRoom){ // if the last level was a menu level
                currLevel.setVisible(false);
            }
            currLevel = pauseMenu; // so the phone stacks ontop of the game level
            currLevel.setVisible(true);
            inventory.setVisible(false);

            isPaused = true; // pause the game
            System.out.println("Paused");
        }
        else if(buttonClicked == pauseMButtons[5]){
            System.out.println("Opening Player Inventory Coming Soon!");
            inventory.setVisible(true);
            pauseMenu.setVisible(false);
            //GameWindow2.setPlayMusic(false); // not working 

        }else if (buttonClicked == pauseMButtons[6]){ // open the map
            pauseMenu.setVisible(false);
            campusMap.setLocation(currLevel.toString());
            campusMap.setVisible(true);
        }
       
        

        }

  
}
