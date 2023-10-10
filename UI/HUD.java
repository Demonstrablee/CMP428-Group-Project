package UI;

import java.awt.Color;
import java.awt.Frame;

import java.awt.Window;

public class HUD extends Window{

    public HUD(Frame owner) {
        super(owner);
        this.setAlwaysOnTop(true);
        this.setBounds(this.getGraphicsConfiguration().getBounds());
        this.setBackground(new Color(0, true));
        this.setVisible(true);
    }

     

    

 
}
