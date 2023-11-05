package Levels.Managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Characters.Characters.Enemy;
import Characters.Characters.Student;
import Levels.GameLevels.MurphysRoom2;
import Objects.HealthBar;
import Objects.HealthStation;
import Objects.Rect;

public class Classroom extends JFrame implements KeyListener, Runnable{
	
	Thread t;
	
	Rect p1 = new Rect(100, 200, 50, 50);
	
	boolean healable = false;

	//Player health stuff
	
	  HealthBar h1 = new HealthBar(100,100,20,20);
	 
	  Enemy e1 = new Enemy(900, 100, 100, 100);
	 
	  HealthStation b1 = new HealthStation(500, 300);
	 
	  HealthStation b2 = new HealthStation(200, 300);
	 
	MurphysRoom2 murphysRoom2 = new MurphysRoom2(null, null);
	
	Student s1 = new Student(500, 300, 50, 50);
	
	Student s2 = new Student(900, 100, 50, 50);
	
	Student s3 = new Student(1100, 700, 50, 50);
	
	
	boolean[] pressing = new boolean[1024];

    static final int UP = KeyEvent.VK_UP;
    static final int DN = KeyEvent.VK_DOWN;
    static final int LT = KeyEvent.VK_LEFT;
    static final int RT = KeyEvent.VK_RIGHT;
	
	public Classroom() {
		        
		setTitle("Spooky Classroom Test Lab");
		
		setSize(1500, 800);
		
		setVisible(true);
		
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
            
            
            //Player Damage
			
			 if(h1.getCurrentHealth() >= h1.getMaxHealth()) healable = false;

			 if (p1.overlaps(e1)) p1.setColor(Color.RED);
			 
			 else p1.setColor(Color.BLACK);
			
			 if (p1.overlaps(e1)){h1.damageTaken();
				healable = true;} // why was Graphics object needed in this method
			 
			
			 if (!p1.overlaps(e1)) h1.damage();
			
			//Player Healing
			 
			if(healable){
			 if (p1.overlaps(b1)) h1.refillHealth(); // why was graphics getGraphics() passed in?
			 
			 if (p1.overlaps(b2)) h1.increaseHealth(1);
			 
			}
            //Student NPC Overlap
            
			
			 if(p1.overlaps(s1)) s1.isSpeaking(); else s1.isNotSpeaking();
			 
			 if(p1.overlaps(s2)) s2.isSpeaking(); else s2.isNotSpeaking();
			 
			 if(p1.overlaps(s3)) s3.isSpeaking(); else s3.isNotSpeaking();
			 
    	
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
	
		//health, enemy, and healthStation
		
		h1.draw(g);
		e1.draw(g);
		b1.draw(g);
		
		b2.draw(g);
		
		// Students Speech
	
		s1.draw(g);
		s1.talk(g , "Hi");
		
		s2.draw(g);
		s2.talk(g , "Can you stop staring at me!");
		
		s3.draw(g);
		s3.talk(g , "Hey, I have something for you");
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