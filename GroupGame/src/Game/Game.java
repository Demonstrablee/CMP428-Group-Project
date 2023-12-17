package Game;

import Levels.Managers.SimpleSoundPlayer;
import Levels.Menus.GameOverMenu;
import Levels.Menus.OptionsMenu;
import Levels.Menus.OverlayLevels.CampusMap;
import Levels.Menus.OverlayLevels.Inventory;
import Levels.Menus.OverlayLevels.PausePhoneMenu;
import Levels.Menus.SaveMenu;
import Levels.Menus.TitleScreen;
import Map.Room;
import Map.RoomManager;
import Map.Rooms.Classroom;
import Map.Rooms.ClassroomB_3rdFloor;
import Map.Rooms.Hallway;
import Map.Rooms.Hallway2_3rdFloor;
import Map.Rooms.Hallway3_3rdFloor;
import Objects.HealthStation;
import Objects.Puzzles.SafePuzzle;
import Sprites.Characters.Enemy;
import Sprites.Characters.Player;
import Sprites.Characters.Student;
import UI.InterviewMode;
import Utils.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game implements Runnable, KeyListener, ActionListener, MouseListener {

	// Default Screen Dimensions
	private final GamePanel panel;
	public final static int TILES_DEFAULT_SIZE = 24;
	public final static float SCALE = 2.5f;
	public final static int TILES_IN_WIDTH = 16;
	public final static int TILES_IN_HEIGHT = 16;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	// Rooms
	private RoomManager roomManager;
	private GameScreen currLevel;
	private Room gameRoom;

	//TODO: CREATE ARRAY FOR CLASSROOM AND HALLWAY
	private Classroom classroom;
	private Hallway hallway;
	private CampusMap campusMap;
	private Hallway2_3rdFloor hallway2_3rdFloor;
	private Hallway3_3rdFloor hallway3_3rdFloor;
	private ClassroomB_3rdFloor classroomB_3rdFloor;

	// Entities
	public Player player;
	private Enemy[] enemies;
	private Student[] students;
	private HealthStation healthStation;

	// Objects
	Rect exit;
	Rect enter;
	int nExitOrEnterX;
	int nExitOrEnterY;
	public SafePuzzle safePuzzle = new SafePuzzle(this, 350, 225, 200, 150);

	//Menus
	static TitleScreen titleScreen;
	static PausePhoneMenu pauseMenu;
	static OptionsMenu optionsMenu;
	static SaveMenu saveMenu;
	static GameOverMenu gameOverMenu;
	static Inventory inventory;

	//Buttons
	Image phone = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/icons/Phone_Blue.png").getScaledInstance(66, 64, Image.SCALE_SMOOTH);
	ImageIcon phoneIcon = new ImageIcon(phone);
	JButton inGameMenuButton = new JButton(phoneIcon);
	JButton mapButton = new JButton();

	JButton [] titleButtons = new JButton[3]; // number of buttons on each menu
	JButton [] pauseMButtons = new JButton[7];
	JButton [] toPauseButtons = new JButton[2];
	JButton [] gameOverButtons = new JButton[3];
	JButton [] inventoryButtons = new JButton[7]; // how many items are in the game

	//UI
	InterviewMode interviewMode = new InterviewMode(this);

	// Utilities
	public static final String RESOURCE_URL = "GroupGame/res/";
	public boolean paused; /* the game's paused state */
	private boolean over; /* whether the game has ended or not */
	boolean titleOrPause; /* whether option menu will direct to title screen or pause menu */

	public Game(GamePanel panel) {
		this.panel = panel;
		loadElements();
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Everything that needs to be updated and re-rendered.
	 */
	public void update() {
		if(over) {
			paused = true;
			currLevel.setVisible(false);
			currLevel = gameOverMenu;
			currLevel.setVisible(true);
		}

		if(paused) inGameMenuButton.setVisible(false);
		else if(!over) {
			if(gameRoom != null) {
				enter = gameRoom.getEnterRect();
				exit = gameRoom.getExitRect();
				enemies = gameRoom.getEnemies();
				students = gameRoom.getStudents();
				healthStation = gameRoom.getHealthStation();
			} else {
				enter = null;
				exit = null;
				enemies = null;
				students = null;
				healthStation = null;
			}
			player.setColor(Color.RED);

			// Game Over Conditions
			if(player.getHealthBar().getCurrentHealth() == 0) {
				over = true;
				paused = true;
			}

			// Player Movement
			player.update();

			// Player Damage
			if (enemies != null) {
				for(Enemy enemy : enemies) { 
					enemy.chase(player); //enemies now move back and forth and chase player
				if(enemy.overlaps(player.bullet) && player.bulletShot) {
					enemy.hitDelay++;
					
					System.out.println(enemy.hitDelay);
					if(enemy.hitDelay >= 10) {
					enemy.hits++; //takes 3 hits to kill an enemy sometimes
					enemy.hitDelay = 0;
					}
				}
				}
				Enemy e1 = enemies[1]; // temporary a loop will kill the player immediately
				if (player.overlaps(e1)) {
					player.setColor(Color.GREEN);
					player.getHealthBar().damageTaken();
				}

				if (!player.overlaps(e1))
					player.getHealthBar().damage();

				// Player Healing
				if(healthStation != null && player.overlaps(healthStation))
					player.getHealthBar().increaseHealth();
			}

			// Student interactions
			if(students != null) {
				Student s = students[0];
				if(player.overlaps(s)) s.speaking();
				else s.silent();
				// TODO: FIX NOT DISPLAYING ON JPANEL
				
				s.update();
				
			}

			// Going to different levels based on currLevel and position
			// Exiting current level to new level

			if(exit != null && currLevel != null && currLevel instanceof Room r) {
				if(exit.overlaps(player)) {

					// Put player into the exit to the next level
					int [] exitPosition = r.getExit().getRoomEntrancePos(); // get position of entrance in next level

					nExitOrEnterX = exitPosition[0] + exitPosition[2]/3;
					nExitOrEnterY = exitPosition[1]- (int)(player.getHeight() +20);
					player.setLocation(nExitOrEnterX, nExitOrEnterY); // make play start in front of the exit

					// THIS MUST HAPPEN AFTER PLAYER PLACEMENT
					gameRoom = r.getExit();

					// Changing the level displayed on screen

					currLevel.setVisible(false);
					currLevel = gameRoom;
					currLevel.setVisible(true);

					System.out.println("Entering " + currLevel.toString());}
			}

			//Entering a next level

			if(enter != null && currLevel != null && currLevel instanceof Room r) {
				if(enter.overlaps(player)) {

					//put player infront of the entrance to the previous level
					int [] enterPositon = r.getEntrance().getRoomExitPos();//get position of exit in previous level
					nExitOrEnterX = enterPositon[0] + enterPositon[2] / 3 ; //x + width/3
					nExitOrEnterY = enterPositon[1] - (int)(player.getHeight() + 20); //y
					player.setLocation(nExitOrEnterX, nExitOrEnterY); //  make play start infront of the exit


					// THIS MUST HAPPEN AFTER PLAYER PLACEMENT
					gameRoom = r.getEntrance();

					// Changing the level displayed on screen
					currLevel.setVisible(false);
					currLevel = gameRoom;
					currLevel.setVisible(true);

					System.out.println("Entering " + currLevel.toString());}
			}
		}
	}

	/**
	 * Renders assets to the scene.
	 *
	 * @param g The graphics context.
	 */
	public void draw(Graphics g) {
		if(!paused) {
			if(player != null) player.draw(g);

			// Student NPC Talk
			if(students != null) {
				for(Student student: students)
					student.talk(g);
			}

			inGameMenuButton.setVisible(true);
		}
	}

	/**
	 * The game loop.
	 */
	@Override
	public void run() {
		while(true) {

			update();
			panel.repaint();

			try {
				Thread.sleep(15);
			} catch (InterruptedException ignored) {}
		}
	}

	/**
	 * Loads all the elements of the game, creating buttons menus, and levels, and adding
	 * them to the JPanel of the LevelBuilder Class
	 */
	public void loadElements() {
		roomManager = new RoomManager();
		player = new Player(this, 150, 500);
		classroom = new Classroom(this);
		hallway = new Hallway(this);
		hallway2_3rdFloor = new Hallway2_3rdFloor(this);
		hallway3_3rdFloor = new Hallway3_3rdFloor(this);
		classroomB_3rdFloor = new ClassroomB_3rdFloor(this);

		inGameMenuButton.addActionListener(this);
		inGameMenuButton.setFocusable(false);  // so important  stops the button form stealing focus from the keyboard
		inGameMenuButton.setBounds(1130, 70,62,62); // w and h are smaller than the image size so that the white background doesnt appear arround the button


		panel.add(inGameMenuButton);
		inGameMenuButton.setVisible(false);

		mapButton.addActionListener(this);
		mapButton.setFocusable(false);  // so important  stops the button form stealing focus from the keyboard
		mapButton.setBounds(1130, 70,62,62); // w and h are smaller than the image size so that the white background doesnt appear arround the button


		// initiallizing buttons
		String [] buttonN = new String[] {"RESUME", "OPTIONS", "QUIT", "RETURN TO TITLE", "BACK", "SAVE GAME", "START", "TRY AGAIN", "INVENTORY", "MAP"};
		createButton(new String [] {buttonN[6],buttonN[1],buttonN[2]}, titleButtons); // TITLE SCREEN
		createButton(new String [] {buttonN[0],buttonN[5],buttonN[1],buttonN[3], buttonN[2],buttonN[8], buttonN[9]}, pauseMButtons); //PAUSE MENU
		createButton(new String [] {buttonN[4],buttonN[4]}, toPauseButtons); // SAVE AND OPTIONS
		createButton(new String [] {buttonN[7],buttonN[3],buttonN[2]}, gameOverButtons); // GAME OVER

		createButton(new String[] {"BACK","WALLET","GATE 5 KEY","CHICKEN SAMMIE"}, inventoryButtons); // inventory items


		//Intializing
		titleScreen = new TitleScreen(this, titleButtons);
		pauseMenu = new PausePhoneMenu(this, pauseMButtons);
		optionsMenu = new OptionsMenu(this, toPauseButtons[1]);
		saveMenu = new SaveMenu(this, toPauseButtons[0]);
		gameOverMenu = new GameOverMenu(this, gameOverButtons);
		campusMap = new CampusMap(this, mapButton); // technically a menu sort of
		inventory = new Inventory(this, inventoryButtons); // TODO NOT FINISHED
		// CAMPUS MAP



		//MURPHYS ROOM
		classroom.setExit(hallway); // the address initially was null

		//HALLWAY01
		hallway.setEntrance(classroom);
		hallway.setExit(hallway2_3rdFloor);
		
		//3RD Floor HALLWAY02
		hallway2_3rdFloor.setEntrance(hallway);
		hallway2_3rdFloor.setExit(hallway3_3rdFloor);
		
		//3RD Floor HALLWAY03
		hallway3_3rdFloor.setEntrance(hallway2_3rdFloor);
		hallway3_3rdFloor.setExit(classroomB_3rdFloor);
		
		//3RD Floor CLASSROOM B
		classroomB_3rdFloor.setEntrance(hallway3_3rdFloor);

		//campusMap.setEnterance(hallway01);


		//ADDING Levels To Level builder Panel

		panel.add(inventory);
		panel.add(campusMap);
		panel.add(pauseMenu);

		panel.add(titleScreen);
		panel.add(optionsMenu);
		panel.add(saveMenu);
		panel.add(classroom);
		panel.add(hallway);
		panel.add(hallway2_3rdFloor);
		panel.add(hallway3_3rdFloor);
		panel.add(classroomB_3rdFloor);

		panel.add(gameOverMenu);

		panel.add(interviewMode);


		// Game State variables AT START
		currLevel = titleScreen; // which room to draw currLevel and levLevel index are one to one (default: titleScreen)
		gameRoom =  classroom; // track of the ingame rooms that player traverses with p1 (default; murphysRoom)

		//levIndex = currLevel.toString(); // which room to display
		paused =  true; // is the game paused or not (default: true)
		titleOrPause = true; // at game start options goes to pause menu (default: true)
		over = false; // is the game over? (only have to change this for gameover window debug) (default: false)

		currLevel.setVisible(true); // whatever the stating level is set it to visible
		//campusMap.setVisible(true);
	}

	/** Create the buttons that will be in the menus  */
	public void createButton(String [] arr, JButton [] buttons) {
		JButton nButton;
		Border border = BorderFactory.createBevelBorder(0);


		for (int i = 0 ; i < arr.length; ++i){
			nButton = new JButton(arr[i]);

			nButton.addActionListener(this);
			nButton.setFocusable(false);


			//Button Stylings

			nButton.setBorder(border);
			nButton.setBackground(Color.LIGHT_GRAY);

			nButton.setOpaque(true); // so that I can see the background
			// nButton.setRolloverEnabled(true);
			// nButton.setRolloverIcon(rolloverIcon);
			// nButton.setBorder(null); // get rid of the border on the button
			// nButton.setContentAreaFilled(false); // makes button transparent so imageICon can be only showing
			buttons[i] = nButton; // add button to array
		}
	}

	/**
	 * Loads any sprite within the game's resource directory.
	 *
	 * @param path The path starting from the res folder.
	 * @return An image, if it exists, otherwise null.
	 */
	public static BufferedImage loadSprite(String path) {
		try {
			return ImageIO.read(new File(RESOURCE_URL + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Room getGameRoom() {
		return gameRoom;
	}

	public RoomManager getRoomManager() {
		return roomManager;
	}

	public GamePanel getPanel() {
		return panel;
	}

	// Keyboard Listeners

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		player.setPressing(e.getKeyCode(), true);

		// P is the pause key.
		if(e.getKeyCode() == KeyEvent.VK_P) {
			if(!paused) {
				//currLevel.setVisible(false);
				currLevel = pauseMenu;
				currLevel.setVisible(true);
				//levIndex = currLevel.toString();
				paused = true; // pause the game
				System.out.println("Paused via key press");
			} else { // go back to the game
				//currLevel.setVisible(false);
				//currLevel = pauseMenu;
				currLevel.setVisible(false); // should be pause menu
				//levIndex = currLevel.toString();
				paused = false; // pause the game
				System.out.println("UnPaused via key press");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.setPressing(e.getKeyCode(), false);
	}

	// Mouse Listeners

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	// Action Listeners

	@Override
	public void actionPerformed(ActionEvent e) {

		Object buttonClicked = e.getSource();
		SimpleSoundPlayer.playSound("GroupGame/src/music/button_click01.wav");// have to wait for audio to finish before action can happen

		if(buttonClicked == titleButtons[0] || buttonClicked == pauseMButtons[0]|| buttonClicked == gameOverButtons[0]) {// go to resume

			currLevel.setVisible(false); // make previous room invisible
			currLevel = gameRoom; // last game room (currLevel is what is used to draw the levels in the paint method) change levels
			currLevel.setVisible(true); // make current room visible

			if(buttonClicked == gameOverButtons[0]) // refill if it was a game over
				player.getHealthBar().refillHealth();   //NEED TO SET THIS TO FULL otherwise game over condition will stay true

			// which level to switch to based on what the last game room you were in
			paused = false;
			titleOrPause = false; //game started make option menu go to the pause menu
			over = false;
		}
		else if(buttonClicked == pauseMButtons[1]){ // go to save

			currLevel.setVisible(false);
			currLevel = saveMenu;
			currLevel.setVisible(true);

		}
		else if(buttonClicked == titleButtons[1] || buttonClicked == pauseMButtons[2]){// go to options

			currLevel.setVisible(false);
			currLevel = optionsMenu;
			currLevel.setVisible(true);

		}
		else if(buttonClicked == pauseMButtons[3] || buttonClicked == toPauseButtons[1] && titleOrPause || buttonClicked == gameOverButtons[1]){ // back to title screen

			currLevel.setVisible(false);
			currLevel = titleScreen;
			gameRoom = classroom;
			currLevel.setVisible(true);

			player.getHealthBar().refillHealth();   //NEED TO SET THIS TO FULL
			paused = true;
			over = false;
			titleOrPause = true; //game ended make pause menu go to the title menu
		}
		else if(buttonClicked == pauseMButtons[4]|| buttonClicked == titleButtons[2]|| buttonClicked == gameOverButtons[2] ){ // quit
			System.exit(0);
		}
		else if(buttonClicked == inGameMenuButton||
				buttonClicked == toPauseButtons[0]||
				(buttonClicked == toPauseButtons[1] && !titleOrPause)||
				buttonClicked == toPauseButtons[0] || buttonClicked == mapButton || buttonClicked == inventoryButtons[0]){ // pause screen

			if(buttonClicked == mapButton){
				campusMap.setVisible(false);

			}
			else if(currLevel != gameRoom){ // if the last level was a menu level
				currLevel.setVisible(false);
			}
			currLevel = pauseMenu; // so the phone stacks ontop of the game level
			currLevel.setVisible(true);
			inventory.setVisible(false);

			paused = true; // pause the game
			System.out.println("Paused");
		}
		else if(buttonClicked == pauseMButtons[5]){
			System.out.println("Opening Player Inventory Coming Soon!");
			inventory.setVisible(true);
			pauseMenu.setVisible(false);
			//GameWindow2.setPlayMusic(false); // not working

		}else if (buttonClicked == pauseMButtons[6]){ // open the map
			pauseMenu.setVisible(false);
			campusMap.setLocation(currLevel.toString());
			campusMap.setVisible(true);
		}
	}
}
