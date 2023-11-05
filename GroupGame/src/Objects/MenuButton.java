package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
 


public class MenuButton extends Rect {
    Image image = Toolkit.getDefaultToolkit().getImage("GroupGame\\src\\images\\RetroWindowsGUI\\Windows_Button_Inactive.png");
    Label label;

    char [] type = {'p','t','o','s'};

    public MenuButton(int x, int y, String btext) {
            super(x, y, 100, 50);
            label = new Label(btext);
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
         
        pen.setColor(Color.ORANGE);
        pen.drawImage(image,(int)x,(int)y,(int)w,(int)h,null);
		pen.drawRect((int)x, (int)y, (int)w, (int)h);
        label.repaint();

    }

    
    
}
