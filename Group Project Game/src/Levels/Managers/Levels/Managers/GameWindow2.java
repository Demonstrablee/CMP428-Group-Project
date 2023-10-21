package Levels.Managers;
import javax.swing.JFrame;

import Objects.Rect;

import Objects.Wall;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow2 extends JFrame implements KeyListener, Runnable{
    Thread t;
     
    // Screen Managment Variables  
    int currResMode = 1; // change to change the resolution
    int prevResMode = 1; // what to revert to 
    boolean fullScreen = false; // use full screen?

    // Constraints
        GridBagConstraints constraints;
        CardLayout cLayout0 = new CardLayout();

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
    
      

        //Objects
        Rect p1 = new Rect(10, 10, 200, 200);
        Wall [] wall = {new Wall(100, 100, 600, 80),new Wall(150, 500, 100, 450),new Wall(3, 70, 100, 350)};
        
        boolean gameStarted = false; // have you started to actually play
        

        
        // Movement Vars
        boolean[] pressing = new boolean[1024];

        static final int UP = KeyEvent.VK_UP;
        static final int DN = KeyEvent.VK_DOWN;
        static final int LT = KeyEvent.VK_LEFT;
        static final int RT = KeyEvent.VK_RIGHT;
 
    //Levels
 
    
    public GameWindow2(){
        
        setTitle("Untitled Game V2");

        setSize(winRes[currResMode][0], winRes[currResMode][1]);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        setVisible(true); 
         
        getContentPane().add(lbPane);

        lbPane.init();// comment in and out to allow for updates to post or just run with F5
        

       
    }



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

        while(true){
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
 
    }
   

