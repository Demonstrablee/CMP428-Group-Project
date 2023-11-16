package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Toolkit;
 


public class MenuButton extends Rect {
    Image pause = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/keys2/pauseWhite.png");
    Image title;
    Image option;
    Image save;
    Image quit;

    String label;
    Image image;
    
    char [] type = {'p','t','o','s'};
    char buttontype;

  

    public MenuButton(int x, int y, String btext) {
            super(x, y, 100, 50);
       
            switch (btext){
                case "PAUSE":
                    image = pause;
                    buttontype = 'p';
                break;
                case "TITLE":
                    image = title;
                    buttontype = 't';
                break;
                case "OPTIONS":
                    image = option;
                    buttontype = 'o';
                break;
                case "SAVE":
                    image = save;
                    buttontype = 's';
                break;
                case "QUIT":
                    image = quit;
                    buttontype = 'q';
                break;

            }
            

        }
    /**
         * @return the buttontype
         */
        public char getButtontype() {
            return buttontype;
        }

    @Override
    public void draw(Graphics pen){
         
        pen.setColor(Color.YELLOW);
        pen.drawImage(image,(int)x,(int)y,(int)w,(int)h,null);
		//pen.drawRect((int)x, (int)y, (int)w, (int)h);
        
        //pen.setColor(Color.WHITE);
        //pen.drawString(label, (int)x + 34, (int)y + 28);
        //label.repaint();

    }

    
    
}
