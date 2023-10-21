package Levels.Menus;
import java.awt.Graphics;
import java.awt.GridBagConstraints;


import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JRadioButton;
import javax.swing.JSlider;

import Levels.Managers.Level2;

public class OptionsMenu extends Level2 {

    JLabel title = new JLabel("OPTIONS");
    JLabel resoLabel = new JLabel("1920 x 1080"); // TEMP VAL
    JLabel musicLabel = new JLabel("BGM");
    JLabel diaLabel = new JLabel("DIALOGUE");

    JRadioButton subs = new JRadioButton("Subtitles");
     
    JButton resoR = new JButton();
    JButton resoL = new JButton();
    JButton confirmButton = new JButton("Set");


    JSlider musicSlider = new JSlider(0,100);
    JSlider diaSlider = new JSlider(0,100);

    GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
    
    JButton backButton;

    public OptionsMenu(JButton backButton){
        super(null,null); // no enterance exit logic just using card manager in levelbuilder

         setBg("images\\bg_classroom03.jpg"); 

         this.backButton = backButton;
        //TITLE
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(title, constraints);

        //SUBTITLES
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(subs, constraints);

        // RESOLUTION ADJUST
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 3;
        add(resoR, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(resoLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(resoL, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        add(confirmButton, constraints);

        // VOLUME ADJUST
         constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        add(musicLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 6;
        add(musicSlider, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 7;
        add(diaLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 8;
        add(diaSlider, constraints);


        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 9;
        add(this.backButton, constraints);

        
    }

   
     @Override
    public void paintComponent(Graphics pen){  //method for painting called with repaint method
        super.paintComponent(pen);
        title.repaint();
        pen.clearRect(0, 0, getWidth(), getHeight()); 
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        title.repaint();
        subs.repaint();
        resoR.repaint();
        resoLabel.repaint();
        resoL.repaint();
        confirmButton.repaint();
        musicLabel.repaint();
        musicSlider.repaint();
        diaLabel.repaint();
        diaSlider.repaint();
        backButton.repaint();

    }
}
