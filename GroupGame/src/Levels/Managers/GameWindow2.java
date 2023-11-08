package Levels.Managers;
import javax.swing.JFrame;

import Characters.Characters.Enemy;
import Characters.Characters.PlayerCharacter;
import Characters.Characters.Student;
import Objects.HealthBar;
import Objects.HealthStation;
import Objects.Rect;

import Objects.Wall;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow2 extends JFrame implements KeyListener, Runnable, ActionListener{
    Thread t;
    
    static String os = System.getProperty("os.name"); // check os of user


    // Screen Managment Variables  
    int currResMode = 1; // change to change the resolution
    int prevResMode = 1; // what to revert to 
    boolean fullScreen = false; // use full screen?

    /// Managers
    SimpleScreenManager screen = new SimpleScreenManager(); // the screen manager
    LevelBuilderPanel lbPane = new LevelBuilderPanel();



    DisplayMode displayFullScreenModes [] = {
            new DisplayMode (640, 480, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (1280, 720, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (1920, 1080, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (2560, 1440, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
                
        };

    final int [][] winRes = {{640,480},{1280,720},{1920,1080},{2560,1440}};
    
    // App icon 

    static String appFolder = os == "Mac" ? "GroupGame/src/images/appIcon/" :"GroupGame\\src\\images\\appIcon\\";
    /**
     * Get the path to the image folder for the game file path changes depending on the users Operation System
     * @return the appFolder
     */
    static public String getAppFolder() {
        return appFolder;
    }


    public Image appIcon = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/appIcon/");

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
    int nExitX;
    int nExitY;

        boolean gameStarted = false; // have you started to actually play
        

        
        // Movement Vars
        boolean[] pressing = new boolean[1024];

        static final int UP = KeyEvent.VK_UP;
        static final int DN = KeyEvent.VK_DOWN;
        static final int LT = KeyEvent.VK_LEFT;
        static final int RT = KeyEvent.VK_RIGHT;
 
    //Levels
 
    
    public GameWindow2(){

        this.setIconImage(appIcon); // set the app icon for the game https://stackoverflow.com/questions/209812/how-do-i-change-the-default-application-icon-in-java
        setTitle("Untitled Game V2");

        setSize(winRes[currResMode][0], winRes[currResMode][1]);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        setVisible(true); 
         
        getContentPane().add(lbPane);

        lbPane.init();// comment in and out to allow for updates to post or just run with F5
        
        //SimpleSoundPlayer.playSoundForever("GroupGame/src/music/title_screen_track01(loop).wav");
           
       
       
    }



    public void init()
	{		
	
		addKeyListener(this);

		requestFocus();

		t = new Thread(this);

		t.start();


 	}

    @Override // Game loop
    public void run() {

        while(true){

             
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
         
    } 
    
    @Override
    public void paint(Graphics pen){   
        //pen.clearRect(0, 0, getWidth(), getHeight());
        //setContentPane(lbPane);
    }  

  
    
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
       
       
 
    }
}
   

