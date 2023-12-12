package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

import Game.Game;
import Game.GameScreen;
import Levels.Managers.Level2;

public class InterviewMode extends GameScreen { // basically a video player
        JLabel title = new JLabel("Interveiw");
        JButton[] choiceButtons = new JButton [] {new JButton("1"),new JButton("2"),new JButton("3"), new JButton("4")};
        
    public InterviewMode(Game game){ //
        super(game, "interviewMode");
        
        //BACKGROUND
        //setBg("bg_classroom01.jpg");
        setBounds(0, 0, 1280, 720);
        setBackground(Color.GREEN);
        
        // Getting the buttons for the Menu
         

        //Adding buttons to panel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints. gridy = 0;
        add(title, constraints);


         for(int i = 0; i < choiceButtons.length; ++i){ 
                    //pauseButtons[i].setIcon(phoneApp[i]); //set the icons for the phone
                    //choiceButtons[i].setToolTipText(choiceButtons[i].getText()); // make tool tips for the icons
                    
                    //Button Design

                    //choiceButtons[i].setText("");
                    //choiceButtons[i].setBorder(null); // get rid of the border on the button
                    //choiceButtons[i].setContentAreaFilled(false); // makes button transparent so imageICon can be only showing
                    constraints = new GridBagConstraints(); 

                
                    if(i >= 2){ // stacking the buttons
                        constraints.gridx = i - 2;
                        constraints.gridy = 2;
                    
                    }else{
                        constraints.gridx = i; //TODO MAKE THE ICONS APPEAR ON THE SCREEN LIKE A PHONE also add under text for app name
                        constraints.gridy = 1;
                    }
                    
                    constraints.insets = new Insets(3, 5, 5, 5);
                    add(choiceButtons[i], constraints);
                
                }
        



    }
    @Override
        public void paintComponent(Graphics pen){  //method for painting
            super.paintComponent(pen);
            //pen.clearRect(0, 0, getWidth(), getHeight());
           // pen.drawImage(bg,0,0,getWidth(), getHeight(),null);

          
            pen.fillRect(0, 0, 1280, 140);//TOP BAR
            pen.fillRect(0, 540, 1280, 270); // BOTTOM
            //repaint(100,100,4000,4000);
            
            //for(JButton button : titleButtons){button.repaint();}
        }
    }


    

