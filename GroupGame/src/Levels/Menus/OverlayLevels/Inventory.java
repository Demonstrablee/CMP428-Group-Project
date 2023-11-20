package Levels.Menus.OverlayLevels;
import javax.swing.*;

import Levels.Managers.GameWindow2;
import Levels.Managers.Level2;
import Objects.Item;

import java.awt.*;

import javax.swing.border.Border;

public class Inventory extends Level2{ 
    JLabel title = new JLabel("5:35 PM");
    JButton[] item;
   
    String folderPath;

  

 

    public Inventory(JButton [] itemButtons){ // This is the Phone
        super(null,null, "Inventory");

        //BACKGROUND
        setBg("black01.jpg");
        setBounds(450,10,500,720); // set the bounds of the panel

        if(GameWindow2.getOs().contains("Mac")){
            folderPath = "GroupGame/src/images/icons/";
        }else{
            folderPath = "GroupGame\\src\\images\\icons\\";
        }
       
       
    //Grab Icons from Folder 
        Image toPause = Toolkit.getDefaultToolkit().getImage(folderPath + "Arrow Left_Lightgreen.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image wallet = Toolkit.getDefaultToolkit().getImage(folderPath + "Writing_Gray.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image gateKey = Toolkit.getDefaultToolkit().getImage(folderPath + "Settings_Black.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image titleI = Toolkit.getDefaultToolkit().getImage(folderPath + "House_Yellow.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image quit = Toolkit.getDefaultToolkit().getImage(folderPath + "Power_Blue.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image inventory = Toolkit.getDefaultToolkit().getImage(folderPath + "Handbag_Red.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        Image map = Toolkit.getDefaultToolkit().getImage(folderPath + "Location_Purple.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
        
        ImageIcon toPause2 = new ImageIcon(toPause);
        ImageIcon wallet2 = new ImageIcon(wallet);
        ImageIcon gateKey2 = new ImageIcon(gateKey);

        ImageIcon titleI2 = new ImageIcon(titleI);
        ImageIcon quit2 = new ImageIcon(quit);
        ImageIcon inventory2 = new ImageIcon(inventory);
        ImageIcon map2 = new ImageIcon(map);

        ImageIcon [] phoneApp = new ImageIcon[] {toPause2,wallet2,gateKey2,titleI2,quit2,inventory2, map2};

        // adding components to the screen
        this.item = itemButtons;
        JButton g = new Item(); 
        setVisible(false);
        //Adding elements to the panel
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 2;
        constraints.gridy = -1;
        title.setForeground(Color.RED);
        add(title,constraints);

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 2;
        constraints.gridy = 1;
        item[0].setIcon(phoneApp[0]);
        item[0].setToolTipText(item[0].getText()); // make tool tips for the icons
        item[0].setText("");

        add(item[0],constraints);


     
        
         
            for(int i = 1; i < item.length; ++i){

                if(item[i] != null){
                    
                    item[i].setIcon(phoneApp[i]); //set the icons for the phone
                    item[i].setToolTipText(item[i].getText()); // make tool tips for the icons
                    
                    item[i].setText("");
                    
                    item[i].setBorder(null); // get rid of the border on the button
                    item[i].setContentAreaFilled(false); // makes button transparent so imageICon can be only showing
                    constraints = new GridBagConstraints(); 

                    // 3 apps per row // maybe later unlocking new appas
                    if(i <= 3){ // first row
                        constraints.gridx = i; //TODO MAKE THE ICONS APPEAR ON THE SCREEN LIKE A PHONE also add under text for app name
                        constraints.gridy = 2;
                    }
                    else if(i >= 4 && i <= 6){ // second row of apps
                        constraints.gridx = i - 3;
                        constraints.gridy = 3;
                    
                    }
                    else if (i >= 7 && i<= 9){ // third row of apps
                        constraints.gridx = i - 7;
                        constraints.gridy = 4;
                    }
                    
                    
                    constraints.insets = new Insets(3, 5, 5, 5);
                    add(item[i], constraints);
                }
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
