package Levels.GameLevels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Sprites.Characters.BlueMonster;
import Sprites.Characters.Enemy;
import Sprites.Characters.Student;
import Objects.HealthBar;
import Objects.HealthStation;
import Objects.Rect;
import Objects.Puzzles.CodePuzzle;
import Objects.Puzzles.SafePuzzle;

public class Classroom01 extends JFrame implements KeyListener, Runnable{
	
	Thread t;
	
	Rect p1 = new Rect(500, 500, 50, 50);
	
	BlueMonster mon = new BlueMonster(60, 60, 100, 100);
	
	SafePuzzle safe = new SafePuzzle(500,200,100,100);
	
	CodePuzzle code = new CodePuzzle(200,200,100,100);
	
	boolean healable = false;

	//Player health stuff
	
	  HealthBar h1 = new HealthBar(100,100,20,20, 3);
	 
	  Enemy e1 = new Enemy("REDWOLF",900, 100, 100, 100);
	 
	  HealthStation b1 = new HealthStation(500, 300);
	 
	  HealthStation b2 = new HealthStation(200, 300);
	 
	MurphysRoom2 murphysRoom2 = new MurphysRoom2(null, null);
	
	Student s1 = new Student("dan",500, 300);
	
	Student s2 = new Student("shan",900, 100);
	
	Student s3 = new Student("Margret",1100, 700);


	boolean[] pressing = new boolean[1024];

    static final int UP = KeyEvent.VK_UP;
    static final int DN = KeyEvent.VK_DOWN;
    static final int LT = KeyEvent.VK_LEFT;
    static final int RT = KeyEvent.VK_RIGHT;
    
    static final int E = KeyEvent.VK_E;
    static final int ESC = KeyEvent.VK_ESCAPE;
	
	public Classroom01() {
		        
		setTitle("Spooky Classroom Test Lab");
		
		setSize(1500, 800);
		
		setVisible(true);
		
		//code puzzle
		addMouseListener(code);
		addMouseMotionListener(code);
		
		//safe
		addMouseListener(safe);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	
	public void init() {
		
		addKeyListener(this);
		
		requestFocus();
		
		t = new Thread(this);
		
		t.start();
		
	}

	@Override
	public void run() {
		
		while(true) {
			
			//murphysRoom2.getExitRect();

			if (pressing[UP]) p1.moveBy(0,-5);
            if (pressing[DN]) p1.moveBy(0,5);
            if (pressing[RT]) p1.moveBy(5,0);
            if (pressing[LT]) p1.moveBy(-5,0);
            
            if(p1.overlaps(code)) {
            	
            	p1.pushedOutOf(code);
            }
            
            if (pressing[E] && p1.overlaps(code.interactZone)) {
        		
        		code.interact = true;
        	}
        	else if(pressing[ESC]) code.interact = false;
            
          if(p1.overlaps(safe)) {
        	
        	p1.pushedOutOf(safe);
        }
    	if (pressing[E] && p1.overlaps(safe.interactZone)) {
    		
    		safe.interact = true;
    	}
    	else if(pressing[ESC]) safe.interact = false;
            
            //Monster chase
            
            if(p1.overlaps(mon)) {
            	
            	p1.pushedOutOf(mon);
            }
            if(p1.overlaps(mon.sight)) {
    			
            	mon.chasePlayer(p1);
    		}
            
            if(p1.overlaps(mon.damageZone) && mon.delay >= 30) {
            	
            	p1.setColor(Color.RED);
            }			 
    	
			repaint();
			
			try
			{
				Thread.sleep(15);
			}
			
			catch(Exception x) {}			
		}
	}
	
	public void paint(Graphics g) {
		
		g.clearRect(0, 0, 1900, 1000);
		
		p1.draw(g);
		
		code.draw(g);
		
		safe.draw(g);
		
//		mon.draw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		pressing[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		pressing[e.getKeyCode()] = false;
		
	}

}
