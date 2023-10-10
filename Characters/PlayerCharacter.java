package Characters;

import Levels.Managers.Level;
 

public class PlayerCharacter extends Character{
    int x = 0;
    int y = 0;
    
    public PlayerCharacter(String name, long pNum, Level location, boolean canInterview) {
            super(name, pNum, location, canInterview);
            
        }
    public void moveUP(int dy){ y += dy;}
    public void moveDN(int dy){ y -= dy;}
    public void moveLT(int dx){ x -= dx;}
    public void moveRT(int dx){ x += dx;}


    public int getxPos() {
        return x;
    }
    public void setxPos(int x) {
        this.x = x;
    }
    public int getyPos() {
        return y;
    }
    public void setyPos(int y) {
        this.y = y;
    }
    

    
}
