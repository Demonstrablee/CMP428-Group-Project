package Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

import static Game.Game.*;

public class RoomManager {

	private Image[] floorTiles, wallTiles, furnitureTiles;

	public RoomManager() {
		loadResources();
	}

	/**
	 * Loads all the tiles needed to build a room.
	 */
	private void loadResources() {
		floorTiles = loadLayerTiles(RoomLayer.FLOORS);
		wallTiles = loadLayerTiles(RoomLayer.WALLS);
		furnitureTiles = loadLayerTiles(RoomLayer.FURNITURE);
	}

	/**
	 * Takes a sprite sheet and cuts out each tile and places
	 * them in an array.
	 *
	 * @param sprites The sprite sheet(s) in which tiles will be imported from.
	 * @return An array of tile images.
	 */
	private Image[] importTiles(BufferedImage... sprites) {
		List<Image> tiles = new ArrayList<>();
		for(BufferedImage sprite : sprites) {
			final int WIDTH = sprite.getWidth() / TILES_DEFAULT_SIZE;
			final int HEIGHT = sprite.getHeight() / TILES_DEFAULT_SIZE;

			for(int h = 0; h < HEIGHT; h++)
				for(int w = 0; w < WIDTH; w++)
					tiles.add(sprite.getSubimage(w * TILES_DEFAULT_SIZE, h * TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE, TILES_DEFAULT_SIZE));
		}
		return tiles.toArray(tiles.toArray(new Image[0]));
	}

	private Image[] loadLayerTiles(RoomLayer layer) {
		return importTiles(loadSprite("tiles/" + layer.getName() + ".png"));
	}

	/**
	 * Get a tile based on different factors.
	 *
	 * @param layer The specific layer it is drawn for.
	 * @param index The index of the tile within the sprite sheet.
	 * @return An image object, if it exists.
	 */
	public Image getTile(RoomLayer layer, int index) {
		if(layer.equals(RoomLayer.FLOORS)) 		 return getFloorTile(index);
		else if(layer.equals(RoomLayer.WALLS)) return getWallTile(index);
		else                                   return getFurnitureTile(index);
	}

	/**
	 * Obtain a specific floor tile image.
	 *
	 * @param index The index of the tile within the sprite sheet.
	 * @return An image object, if it exists.
	 */
	private Image getFloorTile(int index) {
		if(index >= floorTiles.length)return null;
		return floorTiles[index];
	}

	/**
	 * Obtain a specific wall tile image.
	 *
	 * @param index The index of the tile within the sprite sheet.
	 * @return An image object, if it exists.
	 */
	private Image getWallTile(int index) {
		if(index >= wallTiles.length)return null;
		return wallTiles[index];
	}

	/**
	 * Obtain a specific furniture tile image.
	 *
	 * @param index The index of the tile within the sprite sheet.
	 * @return An image object, if it exists.
	 */
	private Image getFurnitureTile(int index) {
		if(index >= furnitureTiles.length)return null;
		return furnitureTiles[index];
	}

	/**
	 * Loads a room's property file which contains all its data.
	 *
	 * @param path The path to the room file.
	 * @return A loaded properties file, so long as it exists.
	 */
	private Properties getRoomPropertyFile(String path) {
		path = path.concat(".properties");
		File roomFile = new File(path);
		Properties properties = new Properties();
		if(roomFile.exists()) {
			try {
				properties.load(new FileInputStream(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	/**
	 * Determines all tile information for a room based on the
	 * tile index given within each room's properties file.
	 *
	 * @param name The room name.
	 * @return A map consisting of a key layer with tile index values.
	 */
	public Map<RoomLayer, int[][]> loadRoomData(String name) {
		Properties roomProperties = getRoomPropertyFile(RESOURCE_URL + "rooms/" + name);
		Map<RoomLayer, int[][]> data = new HashMap<>();
		for(RoomLayer layer : RoomLayer.values())
			data.put(layer, parseRoomData(roomProperties, layer));
		return data;
	}

	/**
	 * Takes a string of room data and makes it useful.
	 *
	 * @param properties The properties file of the room.
	 * @param layer      The layer that will be parsed.
	 * @return A 2D array of tile index for a specific layer.
	 */
	private int[][] parseRoomData(Properties properties, RoomLayer layer) {
		String data = properties.getProperty(layer.getName());
		String[] rows = data.split(";");

		int[][] roomData = null;
		for(int h = 0; h < rows.length; h++) {
			String[] columns = rows[h].split(",");
			for(int w = 0; w < columns.length; w++) {
				if(roomData == null) roomData = new int[rows.length][columns.length];
				int value = Integer.parseInt(columns[w].trim());
				value = (value > 0 ? (value - 1) : -1);
				roomData[h][w] = value;
			}
		}
		return roomData;
	}
}
