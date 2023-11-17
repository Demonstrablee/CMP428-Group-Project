package UI;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

import Levels.Managers.Level2;

public class InterviewMode extends Level2 { // basically a video player
        JLabel title = new JLabel("Interveiw");
         JButton[] titleButtons;
         
    public InterviewMode(String name, JButton [] menuButtons){ //
        super(null,null, "interviewMode");
        
        //BACKGROUND
        //setBg("bg_classroom01.jpg");
        setBounds(0, 0, 1280, 720);
       
        
        // Getting the buttons for the Menu
        this.titleButtons = menuButtons;

        //Adding buttons to panel
        constraints = new GridBagConstraints(); 
        constraints.gridx = 0;
        constraints. gridy = 0;
        add(title, constraints);

        int i = 1;
        for(JButton button : menuButtons){ 
                constraints = new GridBagConstraints();  
                constraints.gridx = 0;
                constraints. gridy = i;
                constraints.ipadx = 30;
                constraints.ipady = 3;
                constraints.insets = new Insets(3, 5, 5, 5);
                add(button, constraints);
                i++;
            }
        



    }
    @Override
        public void paintComponent(Graphics pen){  //method for painting
            super.paintComponent(pen);
            //pen.clearRect(0, 0, getWidth(), getHeight());
            pen.drawImage(bg,0,0,getWidth(), getHeight(),null);

            pen.fillRect(0, 0, 1280, 40);//TOP BAR
            pen.fillRect(0, 0, 1280, 40); // BOTTOM
            //repaint(100,100,4000,4000);
            
            //for(JButton button : titleButtons){button.repaint();}
        }
    }


    

