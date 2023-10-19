package Characters;
// import Levels.Managers.Level;
import Levels.Managers.Level2;
import Objects.Rect;

import java.awt.*;

public class Character extends Rect{
    //private static int numStudent =+ 1; // track the student created
    private String name = ""; //

    private long pNum = 00000000L; // their phone number
    private Level2 location; // where they are
    private boolean canInterview = false; // seperates important and extra characters
    private boolean canCall = false; // can you call them
    

    //Animation animation;
    //Sprite sprites;

    public Character(int x, int y , int w, int h){
        super(x, y, w, h);
        


    }
    public Character(int x, int y , int h, int w, String name, long pNum, Level2 location, boolean canInterview){
        this(x,y,h,w);
        this.name = name;
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
        pen.drawRect((int)x,(int)y,(int)w,(int)h);
    }



}
