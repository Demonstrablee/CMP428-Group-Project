package Map;

import Game.Game;
import Game.GameScreen;
import Objects.HealthStation;
import Sprites.Characters.Enemy;
import Sprites.Characters.Student;
import Utils.Rect;

import java.awt.*;
import java.util.Map;

import static Game.Game.*;

public abstract class Room extends GameScreen {

	// Tile Data
	private final Map<RoomLayer, int[][]> data;

	// Entrances & Exits
	private Room entrance;
	private Room exit;
	int [] enterPos = new int [4]; /* describes the rect that acts as the entrance for the room */
	int [] exitPos = new int[4];
	protected Rect dRectExit = null;
	protected Rect dRectEnter = null;

	// Entities
	protected HealthStation healthStation;
	protected Student[] students;
	protected Enemy[] enemies;

	public Room(Game game, String name) {
		super(game, name);
		this.data = game.getRoomManager().loadRoomData(name);
		this.entrance = null;
		this.exit = null;
		setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
	}

	@Override
	public void setVisible(boolean visible) {
		setVisible(visible, true);
	}

	/**
	 * Draws the tiles that make up the room.
	 *
	 * @param g The graphics context.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setClip(0, 0, GAME_WIDTH, GAME_HEIGHT);

		for(RoomLayer layer : RoomLayer.values())
			drawLayer(g, layer);
	}

	/**
	 * Draw all tiles for a specific layer.
	 *
	 * @param g     The graphics context.
	 * @param layer The layer to be drawn.
	 */
	private void drawLayer(Graphics g, RoomLayer layer) {
		for(int h = 0; h < TILES_IN_HEIGHT; h++) {
			for(int w = 0; w < getWidth(); w++) {
				int x = w * TILES_SIZE;
				int y = h * TILES_SIZE;

				int index = getTileIndex(layer, w, h);
				if(index < 0)continue;
				Image image = game.getRoomManager().getTile(layer, index);
				if(image == null) continue;
				g.drawImage(image, x, y, TILES_SIZE, TILES_SIZE, null);
			}
		}
	}

	public boolean isTransparent(double px, double py) {
		int x = (int)px;  int y = (int)py;
		if(x < 0 || x >= (getWidth() * TILES_SIZE)) return false;
		if(y < 0 || y >= GAME_HEIGHT) return false;
		for(RoomLayer layer : RoomLayer.values()) {
			int index = getTileIndex(layer, x / TILES_SIZE, y / TILES_SIZE);
			if(layer.isSolidTile(index))
				return false;
		}
		return true;
	}

	/**
	 * Get the index of a tile at a specific location within a specific layer.
	 *
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @return An integer that determines which tile will be placed.
	 */
	public int getTileIndex(RoomLayer layer, int x, int y) {
		int index = -1;
		try {
			index = data.get(layer)[y][x];
		} catch (ArrayIndexOutOfBoundsException ignored) {}
		return index;
	}

	/**
	 * @return The max width of the room.
	 */
	public int getWidth() {
		return data.get(RoomLayer.FLOORS)[0].length;
	}

	// Entrance Handlers

	public Room getEntrance() {
		return entrance;
	}

	public void setEntrance(Room entrance) {
		this.entrance = entrance;
	}

	/**
	 *  @return An array with the dimensions that describe the entrance of a room.
	 */
	public int[] getRoomEntrancePos() {
		return enterPos;
	}

	/**
	 * Set the location of the entrance point in the room dRectEnter will be
	 * created from these points.
	 *
	 * @param enterPos The x, y, width, and height for the entrance.
	 */
	public void setRoomEnterPos(int[] enterPos) {
		this.enterPos = enterPos;
		dRectEnter = new Rect(enterPos[0], enterPos[1], enterPos[2], enterPos[3]);
		dRectEnter.setColor(Color.CYAN);
	}

	/**
	 * @return The rectangle that represents the room entrance, or null if it is not defined.
	 */
	public Rect getEnterRect() {
		return dRectEnter;
	}

	// Exit Handlers

	public Room getExit() {
		return exit;
	}

	public void setExit(Room exit) {
		this.exit = exit;
	}

	/**
	 *  @return An array with the dimensions that describe the exit of a room.
	 */
	public int[] getRoomExitPos() {
		return exitPos;
	}

	/**
	 * Set the location of the exit point in the room dRectExit will be
	 * created from these points.
	 *
	 * @param exitPos The x, y, width, and height for the exit.
	 */
	public void setRoomExitPos(int[] exitPos) {
		this.exitPos = exitPos;
		dRectExit = new Rect(exitPos[0], exitPos[1], exitPos[2], exitPos[3]);
		dRectExit.setColor(Color.MAGENTA);
	}

	/**
	 * @return The rectangle that represents the room exit, or null if it is not defined.
	 */
	public Rect getExitRect() {
		return dRectExit;
	}

	// Entities

	public HealthStation getHealthStation() {
		return healthStation;
	}

	public void setHealthStation(HealthStation healthStation) {
		this.healthStation = healthStation;
	}

	public Student[] getStudents() {
		return students;
	}

	public Enemy[] getEnemies() {
		return enemies;
	}
}
