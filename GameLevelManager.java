// package Levels.Managers;

// import java.awt.CardLayout;
// import java.awt.Color;
// import java.awt.DisplayMode;
// import java.awt.Graphics;
// import java.awt.GridBagConstraints;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.JButton;
// import javax.swing.JPanel;

// import Levels.GameLevels.Hallway01;
// import Levels.GameLevels.HubGilletHall;
// import Levels.GameLevels.MurphysRoom;
// import Levels.Menus.OptionsMenu;
// import Levels.Menus.PauseMenu;
// import Levels.Menus.SaveMenu;
// import Levels.Menus.TitleScreen;
// /** Level Manager for the games Menus and other Levels */
// public class GameLevelManager extends JPanel implements ActionListener{

//     //Levels
//     MurphysRoom murphysRoom;
//     Hallway01 hall01;
//     HubGilletHall hubGillet;

//     //Menus
//     TitleScreen titleScreen; 
//     PauseMenu pauseMenu;
//     OptionsMenu optionsMenu;
//     SaveMenu saveMenu;


//     //Objects
//     JButton [] pauseMButtons = {new JButton("RESUME"), new JButton("SAVE GAME"), new JButton("OPTIONS"), new JButton("RETURN TO TITLE SCREEN"),new JButton("QUIT GAME")};
//     JButton [] backToPauseButtons = {new JButton("BACK"),new JButton("BACK"),new JButton("PAUSE")};
//     JButton [] titleButtons = {new JButton("START"), new JButton("OPTIONS")};
    
    
//     // Screen Managment Variables  
//     int currResMode = 1; // change to change the resolution
//     int prevResMode = 1; // what to revert to 
//     boolean gameStarted = false;

//     GridBagConstraints constraints = new GridBagConstraints();
//     CardLayout cLayout0 = new CardLayout();

//     //Window Resolutions
//     DisplayMode displayFullScreenModes [] = {
//         new DisplayMode (640, 480, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
//         new DisplayMode (1280, 720, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
//         new DisplayMode (1920, 1080, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
//         new DisplayMode (2560, 1440, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
            
//     };
//     final int [][] winRes = {{640,480},{1280,720},{1920,1080},{2560,1440}};

//     public GameLevelManager(){
        
//         setLayout(cLayout0);
        
//         // initialize levels
//         //murphysRoom = new MurphysRoom(Color.BLUE,null, null);
//         hall01 = new Hallway01(Color.GRAY,murphysRoom, hubGillet);
//         hubGillet = new HubGilletHall(Color.DARK_GRAY, hall01, null);

//         //Action Listners to buttons

//         for(JButton button : titleButtons){button.addActionListener(this);}
//         for(JButton button : backToPauseButtons){button.addActionListener(this);}

//     // NOTE ADD EVERYTHING TO YOU PANELS BEFORE ADDING THEM TO THE CARDMANAGER

//      //PAUSE MENU 
//         int i = 1;
//         for(JButton button : pauseMButtons){ 
//                 constraints = new GridBagConstraints();  
//                 constraints.gridx = 0;
//                 constraints. gridy = i;
//                 button.addActionListener(this);
//                 pauseMenu.add(button, constraints);
//                 button.repaint();
//                 i++;
//             }

//         // //SAVE MENU
//         constraints = new GridBagConstraints();
//         constraints.gridx = 0;
//         constraints.gridy = 1;
//         saveMenu.add(backToPauseButtons[0], constraints); 

//         // //OPTIONS MENU
//         constraints = new GridBagConstraints();
//         constraints.gridx = 0;
//         constraints.gridy = 9;
//         optionsMenu.add(backToPauseButtons[1], constraints); 

//         //TITLE SCREEN
//         constraints = new GridBagConstraints();
//         constraints.anchor = GridBagConstraints.PAGE_END;
//         constraints.gridx = 0;
//         constraints.gridy = 1;
//         titleScreen.add(titleButtons[0], constraints);

//         constraints = new GridBagConstraints();
//         constraints.gridx = 0;
//         constraints.gridy = 2;
//         titleScreen.add(titleButtons[1], constraints); 
 
//         //Murphys Room
//         constraints = new GridBagConstraints();
//         constraints.gridx = 0;
//         constraints.gridy = 2;
//         murphysRoom.add(backToPauseButtons[2], constraints); 
         
        
//         //Menu Managment pane
//         add(titleScreen, "0");
//         add(pauseMenu, "1"); // not a button in final game a keypress maybe
//         add(optionsMenu, "2");
//         add(saveMenu, "3");
//         add(murphysRoom, "4");
//         add(hall01,"5");
//         add(hubGillet, "6");

       

//         // Set defaults on game start for staring windows
//         cLayout0.show(this, "1"); // show menu panel
       
//     }
    
//     @Override
//     public void paint(Graphics pen){   
//        pen.clearRect(0,0,winRes[currResMode][0], winRes[currResMode][1]);
        
//        for(JButton button : titleButtons){button.repaint();}
//        for(JButton button : backToPauseButtons){button.repaint();}
//        for(JButton button : pauseMButtons){button.repaint();}

//     } 
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         Object buttonClicked = e.getSource();
      
//             if(buttonClicked == titleButtons[0] || buttonClicked == pauseMButtons[0]) {// go to resume
//                 //murphysRoom.init();
//                 cLayout0.show(this, "4"); //show murphys room
//                 gameStarted = true;
//             } 

//        else if(buttonClicked == pauseMButtons[1]) cLayout0.show(this, "3"); // go to save 

//        else if(buttonClicked == titleButtons[1]||buttonClicked == pauseMButtons[2])// go to options
//             cLayout0.show(this, "2");

//        else if(buttonClicked == pauseMButtons[3] || buttonClicked == backToPauseButtons[1] && !gameStarted){ // back to title screen
//             gameStarted = false;
//             cLayout0.show(this, "0");
        
//         }
//        else if(buttonClicked == pauseMButtons[4]) System.exit(0); // quit

//        else if(buttonClicked == backToPauseButtons[0]|| buttonClicked == backToPauseButtons[1] && gameStarted|| buttonClicked == backToPauseButtons[2]){ // pause screen
//         cLayout0.show(this, "1"); // show the pause menu
                

//             } 
       
        
//     }
// }

