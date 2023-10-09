package Levels.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class OptionsMenu extends JPanel {

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

    public OptionsMenu(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
        setBackground(Color.MAGENTA); 

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


        // constraints = new GridBagConstraints();
        // constraints.gridx = 1;
        // constraints.gridy = 9;
        // add(backButton, constraints);



        
    }
   

   
     @Override
    public void paintComponent(Graphics pen){  //method for painting
        super.paintComponent(pen);//component that does the painting 


    }
}
