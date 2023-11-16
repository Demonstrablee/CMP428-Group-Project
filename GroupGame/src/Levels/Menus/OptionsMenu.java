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

    // Labels
    JLabel resoLabel = new JLabel("1920 x 1080"); // TEMP VAL
    JLabel musicLabel = new JLabel("BGM");
    JLabel diaLabel = new JLabel("DIALOGUE");

    //Subtitles
    JRadioButton subs = new JRadioButton("Subtitles");

    //Buttons
    JButton resoR = new JButton();
    JButton resoL = new JButton();
    JButton confirmButton = new JButton("Set");
    JButton backButton;

    //Sliders
    JSlider musicSlider = new JSlider(0,100);
    JSlider diaSlider = new JSlider(0,100);

    GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
    


    public OptionsMenu(JButton backButtons){
        super(null,null, "optionsMenu"); // no enterance exit logic just using card manager in levelbuilder
        
        //BACKGROUND
        setBg("bg_classroom03.jpg"); 
        //Get Buttons
        this.backButton = backButtons;

        //TITLE
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(title, constraints);

        //SUBTITLES
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        constraints.gridy = 1;
        subs.setFocusable(false);
        add(subs, constraints);

        // RESOLUTION ADJUST
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 3;
        resoR.setFocusable(false);
        add(resoR, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(resoLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        resoL.setFocusable(false);
        add(resoL, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        confirmButton.setFocusable(false);
        add(confirmButton, constraints);

        // VOLUME ADJUST
         constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        add(musicLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 6;
        musicSlider.setFocusable(false);
        add(musicSlider, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 7;
        add(diaLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 8;
        diaSlider.setFocusable(false);
        add(diaSlider, constraints);


        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 9;
        // set not focusable in the array that created the buttons
        add(this.backButton, constraints);

        
    }

   
     @Override
    public void paintComponent(Graphics pen){  //method for painting called with repaint method
        super.paintComponent(pen);
       // pen.clearRect(0, 0, getWidth(), getHeight()); 
        pen.drawImage(bg,0,0,getWidth(), getHeight(),null);
        
        // Draw Components
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
