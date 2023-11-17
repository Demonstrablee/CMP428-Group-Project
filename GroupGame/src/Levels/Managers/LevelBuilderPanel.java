package Levels.Managers;

import Characters.Characters.Enemy;
import Characters.Characters.PlayerCharacter;
import Characters.Characters.Student;
import Levels.GameLevels.Hallway01;
import Levels.GameLevels.CampusMap;
import Levels.GameLevels.MurphysRoom2;
import Levels.Menus.GameOverMenu;

import Levels.Menus.OptionsMenu;
import Levels.Menus.PausePhoneMenu;
import Levels.Menus.SaveMenu;
import Levels.Menus.TitleScreen;
import Objects.HealthBar;
import Objects.HealthStation;
import Objects.MenuButton;
import Objects.Rect;
import Objects.Wall;

 
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

;

/** Initialize a level inhertiting from JPanel*/
public class LevelBuilderPanel extends JLayeredPane implements KeyListener, Runnable, ActionListener, MouseListener{
    
    Thread t;
 

    
    //Buttons
    Image phone = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/Phone_Blue.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
    ImageIcon phoneIcon = new ImageIcon(phone);
    JButton inGameMenuButton = new JButton(phoneIcon);
   
    JButton [] titleButtons = new JButton[3];
    JButton [] pauseMButtons = new JButton[5];
    JButton [] toPauseButtons = new JButton[2];
    JButton [] gameOverButtons = new JButton[3];

    //Layout
    CardLayout cLayout0 = new CardLayout();

    
    //Objects
    PlayerCharacter p1 = new PlayerCharacter(300,300, 50,50); // so all levels can share same character
    Wall [] wall; // get walls for refrence and collison detection
    HealthBar healthBar = new HealthBar(100, 100,20, 20);
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

    //Levels
    static MurphysRoom2 murphysRoom2 = new MurphysRoom2(null, null); //4
    static Hallway01 hallway01 = new Hallway01(null, null); //5
    static CampusMap campusMap = new CampusMap(null, null);; // 6
    Level2 level [] = new Level2[] {titleScreen,pauseMenu,optionsMenu,saveMenu,gameOverMenu, murphysRoom2,hallway01,campusMap};
    // Movement vars
    boolean[] pressing = new boolean[1024];

    /**States when the game is paused state (happens while in any menu)*/
    boolean isPaused; 
    /** States whether or not the game is over  */
    boolean isOver = false;
    
    Timer timer;

    static final int UP = KeyEvent.VK_UP;
    static final int DN = KeyEvent.VK_DOWN;
    static final int LT = KeyEvent.VK_LEFT;
    static final int RT = KeyEvent.VK_RIGHT;
    static final int Q = KeyEvent.VK_Q;

    static final int W = KeyEvent.VK_W;
    static final int A = KeyEvent.VK_A;
    static final int S = KeyEvent.VK_S;
    static final int D = KeyEvent.VK_D;
    
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
            
           //NullRepaintManager.install(); // ignore repaint calls from individual Swing Components

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
             
            setBounds(0, 0, 1280, 720); // display size

            inGameMenuButton.addActionListener(this);
            inGameMenuButton.setFocusable(false);  // so important  stops the button form stealing focus from the keyboard
            inGameMenuButton.setBounds(1130, 70,64,64);
            add(inGameMenuButton);
            inGameMenuButton.setVisible(false);
       
          
            // initiallizing buttons
            String [] buttonN = new String[] {"RESUME", "OPTIONS", "QUIT", "RETURN TO TITLE", "BACK", "SAVE GAME", "START", "TRY AGAIN"};
            createButton(new String [] {buttonN[6],buttonN[1],buttonN[2]}, titleButtons); // TITLE SCREEN
            createButton(new String [] {buttonN[0],buttonN[5],buttonN[1],buttonN[3], buttonN[2]}, pauseMButtons); //PAUSE MENU 
            createButton(new String [] {buttonN[4],buttonN[4]}, toPauseButtons); // SAVE AND OPTIONS
            createButton(new String [] {buttonN[7],buttonN[3],buttonN[2]}, gameOverButtons);

           

            //Intializing
            titleScreen = new TitleScreen(titleButtons);
            pauseMenu = new PausePhoneMenu(pauseMButtons);
            optionsMenu = new OptionsMenu(toPauseButtons[1]);
            saveMenu = new SaveMenu(toPauseButtons[0]); 
            gameOverMenu = new GameOverMenu(gameOverButtons);



            //MURPHYS ROOM
            murphysRoom2.setExit(hallway01); // the address initially was null 

            //HALLWAY01
            hallway01.setEnterance(murphysRoom2);
            hallway01.setExit(campusMap);

            // CAMPUS MAP
            campusMap.setEnterance(hallway01);
            
            
            //ADDING Levels To Level builder Panel

            add(pauseMenu);
            add(titleScreen);
            add(optionsMenu);
            add(saveMenu);
            add(murphysRoom2);
            add(hallway01);
            add(campusMap);
            add(gameOverMenu);

            
            
            // Game State variables AT START
            currLevel = titleScreen; // which room to draw currLevel and levLevel index are one to one (default: titleScreen)
            gameRoom = murphysRoom2; // track of the ingame rooms that player traverses with p1 (default; murphysRoom)

            levIndex = currLevel.toString(); // which room to display
            isPaused =  true; // is the game paused or not (default: true)
            titleOrPause = true; // at game start options goes to pause menu (default: true)
            isOver = false; // is the game over? (only have to change this for gameover window debug) (default: false)
             
            currLevel.setVisible(true);
            
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
                    health = healthBar.getCurrentHealth(); // get the players health 
                    students = gameRoom.getStudents();
                    healthStation = gameRoom.getHealthStation();
                    p1.setColor(Color.RED); // Player color will change depending on if hit or not
                    inGameMenuButton.setVisible(true);

                    //GAME OVER CONDITIONS
                    if(health == 0){
                        isOver = true;
                        isPaused = true;
                    }  
                          
                    //MOVEMENT
                    if (pressing[UP]){ p1.moveBy(0,-4);}
                    if (pressing[LT]){p1.goLT(-4); p1.moveBy(-4,0);}
                    if (pressing[DN]){ p1.moveBy(0,4);}
                    if (pressing[RT]){ p1.goRT(4); p1.moveBy(4,0);}
                    

                    if (pressing[W]){ p1.moveBy(0,-4);}
                    if (pressing[A]){ p1.moveBy(-4,0);}
                    if (pressing[S]){ p1.moveBy(0,4);}
                    if (pressing[D]){ p1.moveBy(4,0);}
                   
                        
                     // TODO collision detection STILL NOT WORKING 
                        
                    if (wall != null)
                        for(int i = 0; i < wall.length; i++){
                            if(p1.overlaps(wall[i])){
                                //System.out.println("Pushing player out of wall "+ i);
                                 p1.pushedOutOf(wall[i]);}      
                            }

                    //Player Damage
			
                    if (enemies != null){
                        Enemy e1 = enemies[1]; // temporary a loop will kill the player immediately
                            if (p1.overlaps(e1)){
                                p1.setColor(Color.GREEN);
                                healthBar.damageTaken();
                            }

                           if (!p1.overlaps(e1)) healthBar.damage();
                        
                        ///PLAYER HEALING
                        if (healthStation != null){ // theres like one healthStation in specific locations
                            if (p1.overlaps(healthStation)) {
                               
                                healthBar.increaseHealth(1);
                                 
                                }
                            }
                    }

                    // Student interactions
                    if(students != null){
                        Student s = students[0];
                        // for (Student s1: students){
                            if(p1.overlaps(s)){
                                s.isSpeaking();} //TODO FIX NOT DISPLAYING ON JPANEL
                            else{s.isNotSpeaking();}
                
                       
                    }
                    // Going to diffrent levels based on currLevel and position
                    
                    // Exiting current level to new level
                       
                    if(exit != null){ 
                            if(exit.overlaps(p1)){
                            
                            //Put player intoi the exit to the next level
                            int [] exitPosition = currLevel.getExit().getLevelEntrancePos();//get position of enterance in next level
                          
                            nExitOrEnterX = exitPosition[0] + exitPosition[2]/3;
                            nExitOrEnterY = exitPosition[1]- (int)(p1.h +20); 
                            p1.setLocation(nExitOrEnterX, nExitOrEnterY); //  make play start infront of the exit
                            
                            // THIS MUST HAPPEN AFTER PLAYER PLACEMENT
                            gameRoom = currLevel.getExit(); // 
                            
                            currLevel.setVisible(false);
                            currLevel = gameRoom;
                            currLevel.setVisible(true);

                             
                            System.out.println("Entering "+ currLevel.toString());}   
                    }

                    //Entering a next level
                            
                        if(enter != null){ 
                           
                            if(enter.overlaps(p1)){
                            
                                //put player infront of the entrance to the previous level
                                int [] enterPositon = currLevel.getEnterance().getLevelExitPos();//get position of exit in previous level
                                nExitOrEnterX = enterPositon[0] + enterPositon[2] / 3 ; //x + width/3
                                nExitOrEnterY = enterPositon[1] - (int)(p1.h + 20); //y
                                p1.setLocation(nExitOrEnterX, nExitOrEnterY); //  make play start infront of the exit
                                
                                
                                // THIS MUST HAPPEN AFTER PLAYER PLACEMENT
                                gameRoom = currLevel.getEnterance();  
                                
                                currLevel.setVisible(false);
                                currLevel = gameRoom;
                                currLevel.setVisible(true);

                                System.out.println("Entering "+ currLevel.toString());}         

                        }
                
                    }
        }
    @Override
        public void paint(Graphics pen){ // WOW rather than paint AWT make this paintComponent and everything is good  
            
            pen.clearRect(0, 0, getWidth(), getHeight());

            // Drawing Jpanels
         
            
            super.paint(pen);
             
                            
            //levIndex = currLevel.toString();

            if(!isPaused && !currLevel.equals(campusMap)){ // draw player character if you are playing (drawn on every gameRoom) 
               
               // Draw the player
                p1.draw(pen);
                
                 //Student NPC Talk
                if(students != null){
                    for(Student student: students)
                    student.talk(pen);
                }

                 // PLAYER HUD
                healthBar.draw(pen);
                //inGameMenuButton.repaint();
                //inGameMenuButton.draw(pen); 
                   
                pen.setColor(Color.BLACK);
                
            }
	    }
    
        /** Create the buttons that will be in the menus  */
        public void createButton(String [] arr, JButton [] buttons){
            JButton nButton;
            Border border = BorderFactory.createBevelBorder(0);
            // int width = 40;
            // int height = width/2;
            // Image img1 = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/WindowsGUI/Windows_Button_Inactive.png");
            // img1 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            // ImageIcon dIcon = new ImageIcon(img1);

            // Image img2 = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/WindowsGUI/Windows_Button_Pressed.png");
            // img2 = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            // ImageIcon rolloverIcon = new ImageIcon(img2);
            
            for (int i = 0 ; i < arr.length; ++i){
                nButton = new JButton(arr[i]);
                nButton.addActionListener(this);
                nButton.setFocusable(false);

                //Button Stylings

                nButton.setBorder(border);
                nButton.setBackground(Color.LIGHT_GRAY);
        
                nButton.setOpaque(true);
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

        pressing[e.getKeyCode()] = true;
       
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
        pressing[e.getKeyCode()] = false;
        p1.moving = false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

         }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) { 
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        Object buttonClicked = e.getSource();
        SimpleSoundPlayer.playSound("GroupGame/src/music/button_click01.wav");
          
		if(buttonClicked == titleButtons[0] || buttonClicked == pauseMButtons[0]|| buttonClicked == gameOverButtons[0]) {// go to resume
            //cLayout0.show(this, "4"); //paint method handels this
            currLevel.setVisible(false); // make previous room invisible
            currLevel = gameRoom; // last game room (currLevel is what is used to draw the levels in the paint method) change levels
            currLevel.setVisible(true); // make current room visible


            healthBar.refillHealth();   //NEED TO SET THIS TO FULL 
            // which level to switch to based on what the last game room you were in
            //levIndex = currLevel.equals(hallway01) ? "5":"4"; //murphys room or hallway 
            //levIndex = currLevel.toString();
            isPaused = false;
            titleOrPause = false; //game started make option menu go to the pause menu
            isOver = false;
        } 
       else if(buttonClicked == pauseMButtons[1]){ // go to save 
           // cLayout0.show(this, "3"); 
            currLevel.setVisible(false);
            currLevel = saveMenu;
            currLevel.setVisible(true);
            //levIndex = currLevel.toString();
        }
       else if(buttonClicked == titleButtons[1] || buttonClicked == pauseMButtons[2]){// go to options
            //cLayout0.show(this, "2");
            currLevel.setVisible(false);
            currLevel = optionsMenu;
            currLevel.setVisible(true);
            //levIndex = currLevel.toString();
       }
       else if(buttonClicked == pauseMButtons[3] || buttonClicked == toPauseButtons[1] && titleOrPause || buttonClicked == gameOverButtons[1]){ // back to title screen
            //cLayout0.show(this, "0");
            currLevel.setVisible(false);
            currLevel = titleScreen;
            gameRoom = murphysRoom2;
            currLevel.setVisible(true);

            //levIndex = currLevel.toString();
            healthBar.refillHealth();   //NEED TO SET THIS TO FULL   
            isPaused = true;
            isOver = false;
            titleOrPause = true; //game ended make pause menu go to the title menu
        }
       else if(buttonClicked == pauseMButtons[4]|| buttonClicked == titleButtons[2]|| buttonClicked == gameOverButtons[2] ){ // quit
            System.exit(0);
        } 
       else if(buttonClicked == inGameMenuButton|| buttonClicked == toPauseButtons[0]||(buttonClicked == toPauseButtons[1] && !titleOrPause)|| buttonClicked == toPauseButtons[2]){ // pause screen
            //cLayout0.show(this, "1"); // show the pause menu
            if(currLevel != gameRoom) // if the last level was a menu level
                currLevel.setVisible(false);
            currLevel = pauseMenu;
            currLevel.setVisible(true);
            //levIndex = currLevel.toString();
            isPaused = true; // pause the game
            System.out.println("Paused");
        } 
       
        

        }

  
}
