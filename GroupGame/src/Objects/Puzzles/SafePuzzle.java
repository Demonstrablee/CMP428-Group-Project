package Objects.Puzzles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Utils.Rect;
import Sprites.Animation;

public class SafePuzzle extends Rect implements MouseListener {
	
//    if(p1.overlaps(safe)) {
//    	
//    	p1.pushedOutOf(safe);
//    }
//	if (pressing[E] && p1.overlaps(safe.interactZone)) {
//		
//		safe.interact = true;
//	}
//	else if(pressing[ESC]) safe.interact = false;

	private int mx;
	private int my;
	private boolean open = false;
    
    public boolean interact = false;
    public Rect interactZone;
	   
	private Image safe = Toolkit.getDefaultToolkit().getImage("Images/Safe/safe_image.png");
    private Image keyPad = Toolkit.getDefaultToolkit().getImage("Images/Safe/blank_keypad_wide.png");
    private Image safeClose = Toolkit.getDefaultToolkit().getImage("Images/Safe/safe_wide_green_closed.png");
    private Image safeOpen = Toolkit.getDefaultToolkit().getImage("Images/Safe/safe_wide_green_open.png");
    
    private Animation [] buttons = new Animation [] {
    		new Animation("Images/SafeButton/0_pressed", 2, 10),
    		new Animation("Images/SafeButton/01_pressed", 2, 10),
    		new Animation("Images/SafeButton/02_pressed", 2, 10),
    		new Animation("Images/SafeButton/03_pressed", 2, 10),
    		new Animation("Images/SafeButton/04_pressed", 2, 10),
    		new Animation("Images/SafeButton/05_pressed", 2, 10),
    		new Animation("Images/SafeButton/06_pressed", 2, 10),
    		new Animation("Images/SafeButton/07_pressed", 2, 10),
    		new Animation("Images/SafeButton/08_pressed", 2, 10),
    		new Animation("Images/SafeButton/09_pressed", 2, 10),
    		new Animation("Images/SafeButton/cross_pressed", 2, 10),
    		new Animation("Images/SafeButton/tick_pressed", 2, 10),};
    
    private Rect [] buttonLocation = new Rect[] {
    		new Rect(610, 250, 45, 45),
    		new Rect(655, 250, 45, 45),
    		new Rect(700, 250, 45, 45),
    		new Rect(745, 250, 45, 45),
    		new Rect(610, 295, 45, 45),
    		new Rect(655, 295, 45, 45),
    		new Rect(700, 295, 45, 45),
    		new Rect(745, 295, 45, 45),
    		new Rect(610, 340, 45, 45),
    		new Rect(655, 340, 45, 45),
    		new Rect(700, 340, 45, 45),
    		new Rect(745, 340, 45, 45),
    };
    
    private Image [] number = new Image []{
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/00.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/01.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/02.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/03.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/04.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/05.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/06.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/07.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/08.png"),
    		Toolkit.getDefaultToolkit().getImage("Images/SafeButton/09.png"),
    };
    
    private int[] numberEntered = new int[12];
    private int numberLocation = 0;
    private int buttonClicked = -1;
    private int[] correctCode = {8, 6, 4, 0, 1};

    public SafePuzzle(int x, int y, int w, int h) {
        super(x, y, w, h);
        
        interactZone = new Rect((int) x, (int) y, (int) w, (int)h + 10);
    }

    public void draw(Graphics pen) {
    	
    	interactZone.draw(pen);
    	
    	if(interact) {
    		
    	if(!open) {
    	pen.drawImage(safeClose, 400, 50, 500, 500, null);
    		
    	pen.drawImage(keyPad, 600, 200, 200, 200, null);
    	
    	for(int i = 0; i < buttons.length; i++) {
    		
    	if(buttonClicked == i)  pen.drawImage(buttons[i].getCurrentImage(), (int)buttonLocation[i].getX(), (int)buttonLocation[i].getY(), (int)buttonLocation[i].getWidth(), (int)buttonLocation[i].getHeight(), null);
    	
    	if(buttons[i].animationFinish()) buttonClicked = -1;
    	
    	else pen.drawImage(buttons[i].getStaticImage(), (int)buttonLocation[i].getX(), (int)buttonLocation[i].getY(), (int)buttonLocation[i].getWidth(), (int)buttonLocation[i].getHeight(), null);
    	}
    	
    	for(int i = 0; i < 5; i++) 
    	pen.drawImage(number[numberEntered[i]], 745 - (i * 30), 218, 25, 25, null);
    	
    	}
    	else pen.drawImage(safeOpen, 400, 50, 500, 500, null);
    	
    	}
    	else pen.drawImage(safe, (int) x, (int) y, (int) w, (int) h, null);
    }
    
    public boolean openSafe() {
    	
    	for(int i = 0; i < correctCode.length; i++) 
    		
    	if(numberEntered[i] != correctCode[i]) {
    		
    		return false;
    	
    	}
    	return true;
    }
    
    public void interactPrompt(Graphics pen) {
    	
    	pen.drawString("Interact", (int) x, (int) y);
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {
		
		mx = e.getX();
		my = e.getY();
		
		for(int i = 0; i < buttonLocation.length; i++) {
			
		if(buttonLocation[i].contains(mx, my)) {
			
			buttonClicked = i;
			numberEntered[numberLocation++] = i;
		}
		}
		
		if(buttonLocation[10].contains(mx, my)) {
			
			for (int i = 0; i < numberEntered.length; i++) {
	            
				numberEntered[i] = 0;
		        
			}
			 
			numberLocation = 0;
		}	

		
		if(buttonLocation[11].contains(mx, my)) {
			
			if(openSafe()) {
				
				open = true;
			}
			 
			for (int i = 0; i < numberEntered.length; i++) {
		            
				numberEntered[i] = 0;
		        
			}
			 
			numberLocation = 0;
		}	
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

