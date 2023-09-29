import javax.swing.JFrame;
import javax.swing.JPanel;

import Levels.TitleScreen;

import java.awt.*;

public class gameWindow extends JFrame{
    public static void main (String [] args){
        // change these variables to abjust the window size   
        int currResMode = 1; // change to change the resolution
        int prevResMode = 1; // what to revert to 
        boolean fullScreen = false; // use full screen?

        simpleScreenManager screen = new simpleScreenManager(); // the screen manager
        DisplayMode displayFullScreenModes [] = {
            new DisplayMode (640, 480, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            // new DisplayMode (640, 480, 16, DisplayMode.REFRESH_RATE_UNKNOWN),
             new DisplayMode (1280, 720, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            // new DisplayMode (1280, 720, 16, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (1920, 1080, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            // new DisplayMode (1920, 1080, 16, DisplayMode.REFRESH_RATE_UNKNOWN),
            new DisplayMode (2560, 1440, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            // new DisplayMode (2560, 1440, 16, DisplayMode.REFRESH_RATE_UNKNOWN),       
        };
        int [][] windowResolutions = {{640,480},{1280,720},{1920,1080},{2560,1440}};

        JFrame window = new JFrame("Untitled Group Game");
        TitleScreen titleScreen = new TitleScreen(windowResolutions[currResMode][0],windowResolutions[currResMode][1]); // CANVAS
      

        window.setSize(windowResolutions[currResMode][0],windowResolutions[currResMode][1]);
        // attach canvas to frame
        window.setContentPane(titleScreen); // add the content pane to the jframe
        window.setVisible(true);  

        if (fullScreen){ // RESOLUTION IN FULLSCREEN MODE
            try{ // so that full screen doesnt lock me in
            
            screen.setFullScreen(displayFullScreenModes[currResMode], window);
            window.setVisible(true);

                try{
                    Thread.sleep(5000); // set full screen for 5 sec
                    
                }catch(Exception e){}

            }finally{screen.setFullScreen(displayFullScreenModes[prevResMode], window);
            }
        }else{

        // RESOLUTION SELECTION IN WINDOWED MODE
            try{ // so that full screen doesnt lock me in

                window.setSize(windowResolutions[currResMode][0],windowResolutions[currResMode][1]);
                window.setVisible(true); 
                System.out.println("Resolution set to: "+ windowResolutions[currResMode][0] + " x "+ windowResolutions[currResMode][1]);
                try{
                    Thread.sleep(5000); // set full screen for 5 sec
                    
                }catch(Exception e){}

            }finally{
                window.setSize(windowResolutions[prevResMode][0],windowResolutions[prevResMode][1]);
                System.out.println("Resolution reverted to: "+ windowResolutions[prevResMode][0] + " x "+ windowResolutions[prevResMode][1]);
            }
        }
         

       
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      
       

    }

    
}

