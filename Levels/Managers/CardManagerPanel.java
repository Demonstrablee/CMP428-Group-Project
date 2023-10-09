package Levels.Managers;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Levels.Menus.OptionsMenu;
import Levels.Menus.PauseMenu;
import Levels.Menus.SaveMenu;
import Levels.Menus.TitleScreen;

public class CardManagerPanel extends JPanel implements ActionListener {
  // Setting Levels
    TitleScreen titleScreen = new TitleScreen(); 
    PauseMenu pauseMenu = new PauseMenu();
    OptionsMenu optionsMenu = new OptionsMenu();
    SaveMenu saveMenu = new SaveMenu();
   
    // objects and constraints
    JButton [] nButtons = {new JButton("RESUME"), new JButton("SAVE GAME"), new JButton("OPTIONS"), new JButton("RETURN TO TITLE SCREEN"),new JButton("QUIT GAME")};
    JButton [] backButtons = {new JButton("BACK"),new JButton("BACK"),new JButton("MENU")};
    CardLayout cLayout = new CardLayout();
    GridBagConstraints constraints;    

   
   
    public CardManagerPanel(){ 
        this.setLayout(cLayout);
        for(JButton button : backButtons){ button.addActionListener(this);} // add action listeners

// NOTE ADD EVERYTHING TO YOU PANELS BEFORE ADDING THEM TO THE CARDMANAGER
     //PAUSE MENU the title is in the first row i = 0
    int i = 1;
    for(JButton button : nButtons){ 
            constraints = new GridBagConstraints();  
            constraints.gridx = 0;
            constraints. gridy = i;
            button.addActionListener(this);
            pauseMenu.add(button, constraints);
            i++;
        }

    
    //SAVE MENU
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 1;
    saveMenu.add(backButtons[0], constraints); 

   //OPTIONS MENU
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 9;
    optionsMenu.add(backButtons[1], constraints); 

    //TITLE SCREEN
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 2;
    titleScreen.add(backButtons[2], constraints); 

    //add setting levels to cardmanager panel (panel, name in layoutmanager)
    //TODO add option 0 game resume this.add(gameLevelPanel, "0");
    this.add(saveMenu, "1");
    this.add(optionsMenu, "2");
    this.add(titleScreen, "3");
    // There is no quit screen so theres no 4th panel
    this.add(pauseMenu, "5"); // not a button in final game a keypress maybe

        

    // CHANGE to show one of the panels first
        cLayout.show(this, "3"); // show the title screen
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object buttonClicked = e.getSource();
      
        //  TODO if(buttonClicked == nButtons[0]) cLayout.show(this, "0") // resume
            if(buttonClicked == nButtons[1]) cLayout.show(this, "1"); // save 
       else if(buttonClicked == nButtons[2]) cLayout.show(this, "2"); // options
       else if(buttonClicked == nButtons[3]) cLayout.show(this, "3");// back to title screen
       else if(buttonClicked == nButtons[4]) System.exit(0); // quit
       else if(buttonClicked == backButtons[0]||
               buttonClicked == backButtons[1]||
               buttonClicked == backButtons[2]) cLayout.show(this, "5"); // pause screen
       
        
    }
    
}
