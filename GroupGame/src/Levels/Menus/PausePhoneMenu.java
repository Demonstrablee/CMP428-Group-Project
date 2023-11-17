
package Levels.Menus;
import javax.swing.*;

import Levels.Managers.GameWindow2;
import Levels.Managers.Level2;

import Objects.Wall;

import java.awt.*;

import javax.swing.border.Border;

public class PausePhoneMenu extends Level2{ 
    JLabel title = new JLabel("5:35 PM");
    JButton[] pauseMButtons;
   
    String folderPath;

  

 

    public PausePhoneMenu(JButton [] pauseButtons){ // This is the Phone
        super(null,null, "phonePauseMenu");

        //BACKGROUND
        setBg("bg_pause02.jpg");
        setBounds(450,10,500,720); // set the bounds of the panel

        if(GameWindow2.getOs().contains("Mac")){
            folderPath = "GroupGame/src/images/icons/";
        }else{
            folderPath = "GroupGame\\src\\images\\icons\\";
        }
       
       
    //Grab Icons from Folder 
        Image resume = Toolkit.getDefaultToolkit().getImage(folderPath + "Arrow Left_Lightgreen.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image save = Toolkit.getDefaultToolkit().getImage(folderPath + "Writing_Gray.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image options = Toolkit.getDefaultToolkit().getImage(folderPath + "Settings_Black.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image titleI = Toolkit.getDefaultToolkit().getImage(folderPath + "House_Yellow.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image quit = Toolkit.getDefaultToolkit().getImage(folderPath + "Power_Blue.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image inventory = Toolkit.getDefaultToolkit().getImage(folderPath + "Handbag_Red.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        
        ImageIcon resume2 = new ImageIcon(resume);
        ImageIcon save2 = new ImageIcon(save);
        ImageIcon options2 = new ImageIcon(options);
        ImageIcon titleI2 = new ImageIcon(titleI);
        ImageIcon quit2 = new ImageIcon(quit);
        ImageIcon inventory2 = new ImageIcon(inventory);

        ImageIcon [] phoneApp = new ImageIcon[] {resume2,save2,options2,titleI2,quit2,inventory2};

        // adding components to the screen
        this.pauseMButtons = pauseButtons;
        setVisible(false);
        //Adding elements to the panel
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(title,constraints);
     
        
         
            for(int i = 0; i < pauseButtons.length; ++i){ 
                    pauseButtons[i].setIcon(phoneApp[i]); //set the icons for the phone
                    pauseButtons[i].setToolTipText(pauseButtons[i].getText()); // make tool tips for the icons
                    
                    pauseButtons[i].setText("");
                    
                    pauseButtons[i].setBorder(null); // get rid of the border on the button
                    pauseButtons[i].setContentAreaFilled(false); // makes button transparent so imageICon can be only showing
                    constraints = new GridBagConstraints(); 

                    
                
                    if(i >= 3){ // stacking the app buttons
                        constraints.gridx = i - 3;
                        constraints.gridy = 2;
                    
                    }else{
                        constraints.gridx = i; //TODO MAKE THE ICONS APPEAR ON THE SCREEN LIKE A PHONE also add under text for app name
                        constraints.gridy = 1;
                    }
                    
                    constraints.insets = new Insets(3, 5, 5, 5);
                    add(pauseButtons[i], constraints);
                
                }
        Border phone = BorderFactory.createLineBorder(Color.GRAY,10); //https://www.youtube.com/watch?v=Eb2QydjQvV4
        this.setBorder(phone); // add the boarder to this JPanel
    }
   

    @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);
       // pen.clearRect(0, 0, getWidth(), getHeight());
        
        //Draw Background
        pen.drawImage(bg,getWidth()/2 - 225,40,450, getHeight(),null);
        //pen.drawImage(phoneOutline, getWidth()/2 - 250,0, 500, getHeight(), null);
        // pen.drawRect(getWidth()/2 - 250,0,500,getHeight());
        //Draw Buttons and Title
        // title.repaint();
        // for(JButton button : pauseMButtons){
        //     button.repaint();
            
        // }
       
        
    }

    
}
