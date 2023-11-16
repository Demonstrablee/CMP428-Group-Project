package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Objects.Rect;

public class Phone extends Rect  implements MouseListener{
	
	Rect [] buttons = new Rect [3];
	
	public boolean phoneOut = false;
	
	private boolean drawMap = false;
	
	private int buttonWidth;
	
	private int buttonHeight;
	
	int mx;
	
	int my;
	
	Image map = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/Lehman_Map.png");
	
	Image back = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/GoBack.png");
	
	Rect goBack;
	
	public Phone(int x, int y, int w, int h) {
		
		super(x,y,w,h);
		
		buttonWidth = 150;
        buttonHeight = 50;

        for (int i = 0; i < buttons.length; i++) {
        	
        	buttons[i] = new Rect(x + 20, y  + i * 70, buttonWidth, buttonHeight);
        }
        
        goBack = new Rect(x, y, 50, 50);

    }
	
	public boolean takeOut() {
		
		return phoneOut = true;
	}
	
	public boolean putAway() {
		
		return phoneOut = false;
	}
	
	public void draw(Graphics pen) {
		
		if(phoneOut) {
			if(!drawMap ){
				
		pen.setColor(Color.GREEN);
		pen.fillRect((int)x, (int)y, (int)w, (int)h);
		
		
		for(int i = 0; i < buttons.length; i++) {
			
             buttons[i].draw(pen);
             
             if (i == 0) pen.drawString("Map",  (int)(buttons[i].x + 15), (int)(buttons[i].y + 30));
             
             if (i == 1) pen.drawString("Interface", (int)(buttons[i].x + 15), (int)(buttons[i].y + 30));
             
             if (i == 2) pen.drawString("Pause", (int)(buttons[i].x + 15), (int)(buttons[i].y + 30));
             
		}
		}
			else{
				pen.drawImage(map,  (int)this.x, (int)this.y, null);
				pen.drawImage(back, (int)this.x, (int)this.y, null);
				
//				goBack.draw(pen);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		mx = e.getX();
		my = e.getY();
		
		//goes back to phone
		if(goBack.contains(mx, my)) {
			System.out.println("Went back");
			drawMap = false;
		}
		//Map
		if(buttons[0].contains(mx, my))	{
			System.out.println("Map");
			drawMap = true;
		}
		
		//TODO Add Interface and Pause menu
		
		//Interface
		if(buttons[1].contains(mx, my))
			System.out.println("Interface");
		//Pause
		if(buttons[2].contains(mx, my))
			System.out.println("Pause");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}