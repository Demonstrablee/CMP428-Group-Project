package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
 


public class MenuButton extends Rect {
    Image image = Toolkit.getDefaultToolkit().getImage("");
    String label;
    
    char [] type = {'p','t','o','s'};

    public MenuButton(int x, int y, String btext) {
            super(x, y, 100, 50);
            label = btext;
            // switch (type){
            //     case 'p':
            //     label = new JLabel("PAUSE");
            //     break;
            //     case 't':
            //     label = new JLabel("TITLE SCREEN");
            //     break;
            //     case 'o':
            //     break;
            //     case 's':
            //     break;
            //     case 'q':
            //     break;
            //     default:

            //     break;
            //}
            

        }

    @Override
    public void draw(Graphics pen){
         
        pen.setColor(Color.YELLOW);
        pen.drawImage(image,(int)x,(int)y,(int)w,(int)h,null);
		pen.drawRect((int)x, (int)y, (int)w, (int)h);
        
        pen.setColor(Color.WHITE);
        pen.drawString(label, (int)x + 34, (int)y + 28);
        //label.repaint();

    }

    
    
}
