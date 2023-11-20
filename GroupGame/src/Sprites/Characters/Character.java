package Sprites.Characters;
// import Levels.Managers.Level;
import Levels.Managers.Level2;


import java.awt.*;

import Sprites.Sprite;


public class Character extends Sprite{
    //private static int numStudent =+ 1; // track the student created
    private String name = ""; //

    private long pNum = 00000000L; // their phone number
    private Level2 location; // where they are
    private boolean canInterview = false; // seperates important and extra characters
    private boolean canCall = false; // can you call them
    

    //Animation animation;
    //Sprite sprites;

    public Character(String name, String[] pose, int imagecount, int start, String filetype, int x, int y, int w, int h){
        super(name, pose,imagecount, start, filetype, x, y, w, h);
        


    }
    public Character(String name, String[] pose, int imagecount, int start, String filetype,int x, int y , int h, int w, long pNum, Level2 location, boolean canInterview){
        this(name, pose,imagecount, start, filetype, x, y, w, h);
        this.name = name; // name of the character vs Name for the file
        this.pNum = pNum;
        this.location = location;
        this.canInterview = canInterview;
            
    }



    public String getName() {
        return name;
    }
    public long getpNum() {
        return pNum;
    }

    public Level2 getLevelLocation() {
        return location;
    }


    public boolean canBeInterviewed() {
        return canInterview;
    }


    public boolean canBeCalled() {
        return canCall;
    }

    public void setCanCall(boolean canCall) {
        this.canCall = canCall;
    }

    public void draw (Graphics pen){
        super.draw(pen);
    }




}
