
// package Levels.Managers;
// import Characters.PlayerCharacter;
// import Objects.Wall;

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// /** Initialize a level inhertiting from JPanel*/
// public class Level extends JPanel implements KeyListener, Runnable{
    
//     Thread t;
//     // movement vars
//     boolean[] pressing = new boolean[1024];

//     static final int UP = KeyEvent.VK_UP;
//     static final int DN = KeyEvent.VK_DOWN;
//     static final int LT = KeyEvent.VK_LEFT;
//     static final int RT = KeyEvent.VK_RIGHT;

//     //Level Vars
//     private Level enterance = null; // node ahead
//     private Level exit = null; // node behind
//     protected GridBagConstraints constraints = new GridBagConstraints(); // constraints you will add to each element
//     protected PlayerCharacter p1 = new PlayerCharacter(100,100, 100,100 ); 
//     protected Wall [] wall = {new Wall(100, 10, 600, 80),new Wall(150, 500, 100, 150),new Wall(790, 70, 100, 150)};

//     // background
//     protected Image bg;
    


//     public Level(){
//         setLayout(new GridBagLayout());
//         //setBackground(Color.BLUE); // set pane to black
//         addKeyListener(this);
//     } 
//     // secondary  constructors
//     public Level(Color color){
//         this();
//         setBackground(color); // set pane to black
       
//     } 
//     public Level(Color color, Level entLevel, Level exLevel){
//         this(color); // set pane to black
//         enterance = entLevel;
//         exit = exLevel;
        
//     } 

//     public void init()
//         {		
//             //setDoubleBuffered(true);
//             System.out.println("Init method in Level activated");
//             addKeyListener(this);

//             requestFocus();
            
//             t = new Thread(this);

//             t.start();
//         }
        
//     @Override
//         public void run() {

//             while(true){ // if there is a player character on this level
                
//                 if (pressing[UP]) p1.moveBy(0,-5);
//                 if (pressing[DN]) p1.moveBy(0,5);
//                 if (pressing[RT]) p1.moveBy(5,0);
//                 if (pressing[LT]) p1.moveBy(-5,0);

//                 // colision detection
//                 for(int i = 0; i < wall.length; i++)
// 			{
// 				if(p1.overlaps(wall[i]))
// 				{
                    
// 					p1.pushedOutOf(wall[i]);
				
// 				}
// 			}
			
//                 repaint();
//                 try{
//                     Thread.sleep(15);

//                 }catch(Exception e){

//                 }
//             }
            
//         }
//     @Override
//         public void paint(Graphics pen){
//             pen.clearRect(0, 0, getWidth(), getHeight()); // temp val
//             //p1.draw(pen);
// 	    }
        
//         public void update (Graphics pen){

//         }

         

//     // getters and setters
//     public Level getEnterance() {
//         return enterance;
//     }
//     public Level getExit() {
//         return exit;
//     }  

//     public void setEnterance(Level enterance) {
//         this.enterance = enterance;
//     }
//     public void setExit(Level exit) {
//         this.exit = exit;
//     }
//      //KEY LISTENER
//     @Override
//     public void keyTyped(KeyEvent e) {
        
//     }

//     @Override
//     public void keyPressed(KeyEvent e) {
//         //System.out.println(e.getKeyCode());
//         pressing[e.getKeyCode()] = true;

//     }
//     @Override
//     public void keyReleased(KeyEvent e) {
//         pressing[e.getKeyCode()] = false;

//     }

//     public Thread getT() {
//         return t;
//     }

//     public boolean[] getPressing() {
//         return pressing;
//     }

//     public static int getUp() {
//         return UP;
//     }

//     public static int getDn() {
//         return DN;
//     }

//     public static int getLt() {
//         return LT;
//     }

//     public static int getRt() {
//         return RT;
//     }

//     public GridBagConstraints getConstraints() {
//         return constraints;
//     }

//     public PlayerCharacter getP1() {
//         return p1;
//     }

//     public Image getBg() {
//         return bg;
//     }

//     /** Set the background image for a level */
//     public void setBg(String path) {
//        bg = Toolkit.getDefaultToolkit().getImage(path);;
//     }
   
// }

