package Levels.Managers;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Characters.PlayerCharacter;
import Levels.GameLevels.Hallway01;
import Levels.GameLevels.HubGilletHall;
import Levels.GameLevels.MurphysRoom;
import Levels.Menus.OptionsMenu;
import Levels.Menus.PauseMenu;
import Levels.Menus.SaveMenu;
import Levels.Menus.TitleScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener, Runnable, ActionListener{
    Thread t;
    PlayerCharacter p1 = new PlayerCharacter("Player", 000000000L, null, false); 
    
    //Levels
    MurphysRoom murphysRoom;
    Hallway01 hall01;
    HubGilletHall hubGillet;

    //Menus
    TitleScreen titleScreen = new TitleScreen(); 
    PauseMenu pauseMenu = new PauseMenu();
    OptionsMenu optionsMenu = new OptionsMenu();
    SaveMenu saveMenu = new SaveMenu();

    // Screen Managment Variables  
    int currResMode = 1; // change to change the resolution
    int prevResMode = 1; // what to revert to 
    boolean fullScreen = false; // use full screen?

    /// Managers
    SimpleScreenManager screen = new SimpleScreenManager(); // the screen manager
    DisplayMode displayFullScreenModes [] = {
            new DisplayMode (640, 480, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (1280, 720, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (1920, 1080, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (2560, 1440, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
                
        };
        final int [][] windowResolutions = {{640,480},{1280,720},{1920,1080},{2560,1440}};
    
        // Constraints
        GridBagConstraints constraints;
        CardLayout cLayout0 = new CardLayout();
        CardLayout cLayout1 = new CardLayout();
        CardLayout cLayout2 = new CardLayout();

        //Objects
        JPanel gameWindowJPanel = new JPanel(cLayout0);
        JPanel menuLPanel = new JPanel(cLayout1); // menuMangment pane
        JPanel gameLevelPanel = new JPanel(cLayout2); // in game levels
        Image offscreenImage;
        Graphics offscreenPen;

        boolean gameStarted = false; // have you started the actual game play
        
        JButton [] pauseMButtons = {new JButton("RESUME"), new JButton("SAVE GAME"), new JButton("OPTIONS"), new JButton("RETURN TO TITLE SCREEN"),new JButton("QUIT GAME")};
        JButton [] backToPauseButtons = {new JButton("BACK"),new JButton("BACK"),new JButton("PAUSE")};
        JButton [] titleButtons = {new JButton("START"), new JButton("OPTIONS")};

    
   
    public GameWindow(){
        setTitle("Untitled Game");
        setSize(windowResolutions[currResMode][0], windowResolutions[currResMode][1]);
        menuLPanel.setBackground(Color.BLACK);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  
        init();
 
        // initialize levels
        murphysRoom = new MurphysRoom(Color.BLUE,null, hall01);
        hall01 = new Hallway01(Color.GRAY,murphysRoom, hubGillet);
        hubGillet = new HubGilletHall(Color.DARK_GRAY, hall01, null);

        //Action Listners to buttons

        for(JButton button : titleButtons){button.addActionListener(this);}
        for(JButton button : backToPauseButtons){button.addActionListener(this);}

    // NOTE ADD EVERYTHING TO YOU PANELS BEFORE ADDING THEM TO THE CARDMANAGER

     //PAUSE MENU 
        int i = 1;
        for(JButton button : pauseMButtons){ 
                constraints = new GridBagConstraints();  
                constraints.gridx = 0;
                constraints. gridy = i;
                button.addActionListener(this);
                pauseMenu.add(button, constraints);
                i++;
            }

        // //SAVE MENU
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        saveMenu.add(backToPauseButtons[0], constraints); 

        // //OPTIONS MENU
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 9;
        optionsMenu.add(backToPauseButtons[1], constraints); 

        //TITLE SCREEN
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridx = 0;
        constraints.gridy = 1;
        titleScreen.add(titleButtons[0], constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        titleScreen.add(titleButtons[1], constraints); 
 
        //Murphys Room
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        murphysRoom.add(backToPauseButtons[2], constraints); 

        //Menu Managment pane
        menuLPanel.add(titleScreen, "0");
        menuLPanel.add(pauseMenu, "1"); // not a button in final game a keypress maybe
        menuLPanel.add(optionsMenu, "2");
        menuLPanel.add(saveMenu, "3");

        //Game Levels Management pane
        gameLevelPanel.add(murphysRoom, "0");
        gameLevelPanel.add(hall01,"1");
        gameLevelPanel.add(hubGillet, "2");

        // Game Window Jpanel
        gameWindowJPanel.add(menuLPanel, "0");
        gameWindowJPanel.add(gameLevelPanel, "1");


       
       
        // Set defaults on game start for staring windows
        cLayout0.show(gameWindowJPanel, "0"); // show menu panel
        cLayout1.show(menuLPanel, "0"); // show the title screen
        cLayout2.show(gameLevelPanel, "0"); // show murphys room first level of the game

    }


    public void init()
	{		
		//setDoubleBuffered(true);
		
		addKeyListener(this);

		requestFocus();
		
		t = new Thread(this);

		t.start();

        offscreenImage = createImage(windowResolutions[currResMode][0], windowResolutions[currResMode][1]);
        offscreenPen = offscreenImage.getGraphics(); // aquire pen for the offscreen
	}


    
    @Override // Game loop
    public void run() {

        while(true){

            repaint();
            try{
                Thread.sleep(15);

            }catch(Exception e){

            }
        }
         
    }

    @Override
    public void update(Graphics pen){//stops the flicker
        offscreenPen.clearRect(0,0,windowResolutions[currResMode][0], windowResolutions[currResMode][1]);
        paint(offscreenPen); // call paint method to draw on offscreen
        pen.drawImage(offscreenImage,0,0,null); // draw offcreen image onto screen
    }

    @Override
    public void paint(Graphics pen){  
       //Graphics pen2 = menuLPanel.getGraphics();
        //pen.setColor(Color.RED);
       
        setContentPane(gameWindowJPanel); // add the content pane to the jframe
        
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

    //             setSize(windowResolutions[currResMode][0],windowResolutions[currResMode][1]);
    //             setVisible(true); 
    //             System.out.println("Resolution set to: "+ windowResolutions[currResMode][0] + " x "+ windowResolutions[currResMode][1]);
    //             try{
    //                 Thread.sleep(5000); // set full screen for 5 sec
                    
    //             }catch(Exception e){}

    //         }finally{
    //             setSize(windowResolutions[prevResMode][0],windowResolutions[prevResMode][1]);
    //             System.out.println("Resolution reverted to: "+ windowResolutions[prevResMode][0] + " x "+ windowResolutions[prevResMode][1]);
    //         }
    //     }
         
    // }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
      
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

       @Override
    public void actionPerformed(ActionEvent e) {
        Object buttonClicked = e.getSource();
      

            if(buttonClicked == titleButtons[0] || buttonClicked == pauseMButtons[0]) {// go to resume
                cLayout0.show(gameWindowJPanel, "1"); //go to the gamelevels panel
                gameStarted = true;
            } 

       else if(buttonClicked == pauseMButtons[1]) cLayout1.show(menuLPanel, "3"); // go to save 

       else if(buttonClicked == titleButtons[1]||buttonClicked == pauseMButtons[2])// go to options
            cLayout1.show(menuLPanel, "2");

       else if(buttonClicked == pauseMButtons[3] || buttonClicked == backToPauseButtons[1] && !gameStarted){ // back to title screen
            gameStarted = false;
            cLayout1.show(menuLPanel, "0");
        
        }
       else if(buttonClicked == pauseMButtons[4]) System.exit(0); // quit

       else if(buttonClicked == backToPauseButtons[1] && gameStarted||buttonClicked == backToPauseButtons[1]|| buttonClicked == backToPauseButtons[2]){ // pause screen
        cLayout0.show(gameWindowJPanel, "0"); // go to menuPanel
        cLayout1.show(menuLPanel, "1"); // show the pause menu
                

            } 
       
        
    }
}

