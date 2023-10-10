package Characters;
import Levels.Managers.Level;
import java.awt.*;

public class Character {
    private static int student;
    private String name; //

    private long pNum; // their phone number
    private Level location; // where they are
    private boolean canInterview; // seperates important and extra characters
    private boolean canCall; // can you call them
    
    Animation animation;
        Sprite sprites;

        public Character(String name, long pNum, Level location, boolean canInterview){
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

    public Level getLocation() {
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
        pen.drawRect(100,100,10,10);
    }



}
