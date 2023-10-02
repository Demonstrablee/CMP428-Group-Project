package Levels;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CardManagerPanel extends JPanel implements ActionListener {
  // Setting Levels
    TitleScreen titleScreen = new TitleScreen(); 
    PauseMenu pauseMenu = new PauseMenu();
    OptionsMenu optionsMenu = new OptionsMenu();
    SaveMenu saveMenu = new SaveMenu();
    
    // Game Levels
    MurphysRoom murphysRoom = new MurphysRoom();
    HubGilletHall hubGilletHall = new HubGilletHall();
    
   
    // objects and constraints

    CardLayout cLayout = new CardLayout();
    GridBagConstraints constraints;    

    JButton [] nButtons = {new JButton("RESUME"), new JButton("SAVE GAME"), new JButton("OPTIONS"), new JButton("RETURN TO TITLE SCREEN"),new JButton("QUIT GAME"), new JButton("PAUSE") };
    
   
    public CardManagerPanel(int windowW, int windowH){ 
        this.setLayout(cLayout);

// NOTE ADD EVERYTHING TO YOU PANELS BEFORE ADDING THEM TO THE CARDMANAGER
     //PAUSE MENU
    // the title is in the first row i = 0
    int i = 1;
    for(JButton button : nButtons){ 
         constraints = new GridBagConstraints();  
         constraints.gridx = 0;
         constraints. gridy = i;
         button.addActionListener(this);
         pauseMenu.add(button, constraints);
         i++;
     }
    
    
//      //SAVE MENU
//     constraints = new GridBagConstraints();
//     constraints.gridx = 0;
//     constraints.gridy = 1;
//     nButtons[5].setText("BACK");
//     saveMenu.add(nButtons[5], constraints); // adding these buttons from here doesnt work

   //OPTIONS MENU
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 9;
    optionsMenu.add(nButtons[5], constraints); // why does adding this 

    //TITLE SCREEN
    //TODO IF ADDING ANYTHING PUT IT ABOVE THE TITLE SCREENS ADDITIONS
    // constraints = new GridBagConstraints();
    // constraints.gridx = 0;
    // constraints.gridy = 1;
    // nButtons[5].setText("PAUSE");
    // titleScreen.add(nButtons[5], constraints); // WHY DOES THIS HAVE TO BE THE LAST THING ADDED (DOESNT WORK OTHERWISE)
 

    //add setting levels to cardmanager panel (panel, name in layoutmanager)
       //TODO add option 0 game resume
       this.add(saveMenu, "1");
       this.add(optionsMenu, "2");
       this.add(titleScreen, "3");
       // There is no quit screen so theres no 4 
       this.add(pauseMenu, "5"); // not a button in final game a keypress maybe

        

    // CHANGE to show one of the panels first
        cLayout.show(this, "2"); // show the title screen
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object buttonClicked = e.getSource();
      
          
            if(buttonClicked == nButtons[1]) cLayout.show(this, "1"); // save 
       else if(buttonClicked == nButtons[2]) cLayout.show(this, "2"); // options
       else if(buttonClicked == nButtons[3]) cLayout.show(this, "3");// back to title screen
       else if(buttonClicked == nButtons[4]) System.exit(0); // quit
       else if(buttonClicked == nButtons[5])cLayout.show(this, "5"); // pause screen
       
        
    }
    
}
