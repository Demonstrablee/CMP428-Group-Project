package Levels.Managers;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Objects.Rect;
//import Characters.PlayerCharacter;
// import Levels.GameLevels.Hallway01;
// import Levels.GameLevels.HubGilletHall;
// import Levels.GameLevels.MurphysRoom;
// import Levels.Menus.OptionsMenu;
// /import Levels.Menus.PauseMenu;
// import Levels.Menus.SaveMenu;
// import Levels.Menus.TitleScreen;
// import Objects.Rect;
import Objects.Wall;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow3 extends JFrame implements KeyListener, Runnable, ActionListener{
    Thread t;
     
   
    //Level [] menus = {titleScreen, pauseMenu, optionsMenu, saveMenu};

    // Screen Managment Variables  
    int currResMode = 1; // change to change the resolution
    int prevResMode = 1; // what to revert to 
    boolean fullScreen = false; // use full screen?

    // Constraints
        GridBagConstraints constraints;
        CardLayout cLayout0 = new CardLayout();

    /// Managers
    //SimpleScreenManager screen = new SimpleScreenManager(); // the screen manager
    JPanel gameWindowJPanel = new JPanel(cLayout0);

    LevelBuilderPanel lbPane = new LevelBuilderPanel();

    DisplayMode displayFullScreenModes [] = {
            new DisplayMode (640, 480, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (1280, 720, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (1920, 1080, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (2560, 1440, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
                
        };
        final int [][] winRes = {{640,480},{1280,720},{1920,1080},{2560,1440}};
    
      
      

        //Objects
        
        Rect p1 = new Rect(10, 10, 200, 200);
        Wall [] wall = {new Wall(100, 10, 600, 80),new Wall(150, 500, 100, 150),new Wall(790, 70, 100, 150)};
        
        boolean gameStarted = false; // have you started to actually play
        
        JButton [] pauseMButtons = {new JButton("RESUME"), new JButton("SAVE GAME"), new JButton("OPTIONS"), new JButton("RETURN TO TITLE SCREEN"),new JButton("QUIT GAME")};
        JButton [] backToPauseButtons = {new JButton("BACK"),new JButton("BACK"),new JButton("PAUSE")};
        JButton [] titleButtons = {new JButton("START"), new JButton("OPTIONS"), new JButton("QUIT")};

        
        // Movement Vars
        boolean[] pressing = new boolean[1024];

        static final int UP = KeyEvent.VK_UP;
        static final int DN = KeyEvent.VK_DOWN;
        static final int LT = KeyEvent.VK_LEFT;
        static final int RT = KeyEvent.VK_RIGHT;
 
    //Levels
    

    //MurphysRoom murphysRoom = new MurphysRoom(Color.BLUE,null, null,backToPauseButtons[2]);
    //Hallway01 hall01 = new Hallway01(Color.GRAY,murphysRoom, null);
    //HubGilletHall hubGillet = new HubGilletHall(Color.DARK_GRAY, hall01, null);

    //Menus
    // TitleScreen titleScreen = new TitleScreen(titleButtons);
    // PauseMenu pauseMenu= new PauseMenu(pauseMButtons);
    // OptionsMenu optionsMenu = new OptionsMenu(backToPauseButtons[0]);
    // SaveMenu saveMenu = new SaveMenu(backToPauseButtons[1]);

    
    public GameWindow3(){
        
        setTitle("Untitled Game V2");

        setSize(winRes[currResMode][0], winRes[currResMode][1]);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        setVisible(true); 
  

        //loadElements(); // issues within
        

        //gameWindowJPanel.add(murphysRoom,"0"); // put level into panel
        //gameWindowJPanel.add(pauseMenu,"1");

        //getContentPane().add(gameWindowJPanel); // put panel into jframe pane
        getContentPane().add(lbPane);
        lbPane.init(); // comment in and out to allow for updates to post

    }

    // public void loadElements(){
 
    //     // initialize levels
        

    //     //Action Listners to buttons

    //     for(JButton button : titleButtons){button.addActionListener(this);}
    //     for(JButton button : backToPauseButtons){button.addActionListener(this);}
    //     for(JButton button : pauseMButtons){button.addActionListener(this);}
 
        
    //     //Menu Managment pane
    //     gameWindowJPanel.add(titleScreen, "0");
    //     gameWindowJPanel.add(pauseMenu, "1"); // not a button in final game a keypress maybe
    //     gameWindowJPanel.add(optionsMenu, "2");
    //     gameWindowJPanel.add(saveMenu, "3");
    //     gameWindowJPanel.add(murphysRoom, "4");
    //     gameWindowJPanel.add(hall01,"5");
    //     gameWindowJPanel.add(hubGillet, "6");

        
       
       
    //     // Set defaults on game start for staring windows
    //    //cLayout0.show(gameWindowJPanel, "1"); // show menu panel
       

    // }
    

 
    public void init()
	{		
		//System.out.println("init in Game Window2 activated");
		addKeyListener(this);

		requestFocus();

		t = new Thread(this);

		t.start();


 	}


    
    @Override // Game loop
    public void run() {

        while(!gameStarted){
            //System.out.println("Game not started ");
             
            if (pressing[UP]) p1.moveBy(0,-5);
            if (pressing[DN]) p1.moveBy(0,5);
            if (pressing[RT]) p1.moveBy(5,0);
            if (pressing[LT]) p1.moveBy(-5,0);

            for(int i = 0; i < wall.length; i++)
			{
				if(p1.overlaps(wall[i]))
				{
                    
					p1.pushedOutOf(wall[i]);
				
				}
            }
            repaint();

            try{
                Thread.sleep(15);

            }catch(Exception e){

            }

        }
        System.out.println("Game Started"); 
    } 
    
    @Override
    public void paint(Graphics pen){   
        pen.clearRect(0, 0, getWidth(), getHeight());
    }  

    
    public void moveToScreen(){

    }
    

    // public void changeResolution(){

    //     if (fullScreen){ // RESOLUTION IN FULLSCREEN MODE
    //         try{ // so that full screen doesnt lock me in
            
    //         screen.setFullScreen(displayFullScreenModes[currResMode], this); // make this screen fullscreen
    //         setVisible(true);

    //             try{
    //                 Thread.sleep(5000); // set full screen for 5 sec
                    
    //             }catch(Exception e){}

    //         }finally{screen.setFullScreen(displayFullScreenModes[prevResMode], this);}
    //     }else{

    //     // RESOLUTION SELECTION IN WINDOWED MODE
    //         try{ // so that full screen doesnt lock me in

    //             setSize(winRes[currResMode][0],winRes[currResMode][1]);
    //             setVisible(true); 
    //             System.out.println("Resolution set to: "+ winRes[currResMode][0] + " x "+ winRes[currResMode][1]);
    //             try{
    //                 Thread.sleep(5000); // set full screen for 5 sec
                    
    //             }catch(Exception e){}

    //         }finally{
    //             setSize(winRes[prevResMode][0],winRes[prevResMode][1]);
    //             System.out.println("Resolution reverted to: "+ winRes[prevResMode][0] + " x "+ winRes[prevResMode][1]);
    //         }
    //     }
         
    // }
    
    @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            pressing[e.getKeyCode()] = true;

        }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
   
    @Override
    public void keyReleased(KeyEvent e) {
        pressing[e.getKeyCode()] = false;

    }

       @Override
    public void actionPerformed(ActionEvent e) {
       
        Object buttonClicked = e.getSource();
      
            if(buttonClicked == titleButtons[0] || buttonClicked == pauseMButtons[0]) {// go to resume
                
                cLayout0.show(gameWindowJPanel, "4"); //show murphys room
                gameStarted = true;
            } 

       else if(buttonClicked == pauseMButtons[1]) cLayout0.show(gameWindowJPanel, "3"); // go to save 

       else if(buttonClicked == titleButtons[1]||buttonClicked == pauseMButtons[2])// go to options
            cLayout0.show(gameWindowJPanel, "2");

       else if(buttonClicked == pauseMButtons[3] || buttonClicked == backToPauseButtons[1] && !gameStarted){ // back to title screen
            gameStarted = false;
            cLayout0.show(gameWindowJPanel, "0");
        
        }
       else if(buttonClicked == pauseMButtons[4]|| buttonClicked == titleButtons[2]) System.exit(0); // quit

       else if(buttonClicked == backToPauseButtons[0]|| buttonClicked == backToPauseButtons[1] && gameStarted|| buttonClicked == backToPauseButtons[2]){ // pause screen
        cLayout0.show(gameWindowJPanel, "1"); // show the pause menu
                

            } 
       
        

        }
    }
   

