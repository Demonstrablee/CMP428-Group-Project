package Objects.Puzzles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Utils.Rect;

public class CodePuzzle extends Rect implements MouseListener, MouseMotionListener{
	
	//code is 10468
	
//	if(p1.overlaps(code)) {
//    	
//    	p1.pushedOutOf(code);
//    }
//    
//    if (pressing[E] && p1.overlaps(code.interactZone)) {
//		
//		code.interact = true;
//	}
//	else if(pressing[ESC]) code.interact = false;
    
	private int mx;
	private int my;
	
	public boolean interact = false;
	public Rect interactZone;
	
	private Rect [] piece = new Rect [] {
					new Rect(50, 80, 99, 200),
					new Rect(200, 80, 99, 200),
					new Rect(350, 80, 99, 200),
					new Rect(50, 300, 99, 200),
					new Rect(200, 300, 99, 200)
	};
	
	private Rect [] location = new Rect [] {
			new Rect(500, 80, 10, 10),
			new Rect(600, 80, 10, 10),
			new Rect(700, 80, 10, 10),
			new Rect(800, 80, 10, 10),
			new Rect(900, 80, 10, 10)
	};
	
	Image image [] = new Image[] {
			Toolkit.getDefaultToolkit().createImage("Images/SafeCode/number_0.png"),
			Toolkit.getDefaultToolkit().createImage("Images/SafeCode/number_1.png"),
			Toolkit.getDefaultToolkit().createImage("Images/SafeCode/number_2.png"),
			Toolkit.getDefaultToolkit().createImage("Images/SafeCode/number_3.png"),
			Toolkit.getDefaultToolkit().createImage("Images/SafeCode/number_4.png"),
	};
	

	public CodePuzzle(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		interactZone = new Rect((int) x, (int) y, (int) w, (int)h + 10);	
	}
	
	public void draw(Graphics pen) {
		
		if(interact) {
			
		for(int i = 0; i < piece.length; i++) {
			
			piece[i].draw(pen);
			
			location[i].draw(pen);
			
			pen.drawImage(image[i], (int) piece[i].getX(), (int) piece[i].getY(), 100, 200, null);
		}
		}
		else {
			super.draw(pen);
			
			interactZone.draw(pen);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		mx = e.getX();
		my = e.getY();
		
		for(int i = 0; i < piece.length; i++) {
			
		if(piece[i].contains(mx, my)) {
			
			piece[i].grab();
			
		}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		for(int i = 0; i < piece.length; i++) {
			
				for(int j = 0; j < location.length; j++) {
					
					if(piece[i].overlaps(location[j])) {
						
						piece[i].setX(location[j].getX());
						piece[i].setY(location[j].getY());

					}
				}
				piece[i].drop();
			}	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		int nx = e.getX();
		int ny = e.getY();
		
		int dx = nx - mx;
		int dy = ny - my;
		
		for(int i = 0; i < piece.length; i++) {
			
		if(piece[i].isHeld()) {
			
			piece[i].moveBy(dx, dy);;
		}
		}
		
		mx = nx;
		my = ny;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
