package Levels.Managers;

import java.awt.*;
  import javax.swing.JFrame;

public class SimpleScreenManager { // from the book 
    
    private GraphicsDevice device;
        public SimpleScreenManager(){
            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            device = environment.getDefaultScreenDevice();
        }
    

        public void setFullScreen(DisplayMode displayMode, JFrame window){
            
            window.setUndecorated(true); // get rid of jframe boarder
            window.setResizable(false);

            device.setFullScreenWindow(window); // make the frame the full screen window
            if(displayMode != null && device.isDisplayChangeSupported()){
                    try{
                        device.setDisplayMode(displayMode);
                    }catch(IllegalArgumentException e){
                        //ignore it
                    }
                }
            }

            public Window getFullScreenWindow(){
                return device.getFullScreenWindow();
            }
            
            public void restoreScreen(){
                Window window = device.getFullScreenWindow();
                if(window != null) window.dispose();

                device.setFullScreenWindow(null); // set the window back to normal
            }
        }


