package Map;

/**
 * The individual layers that build a complete map.
 */
public enum RoomLayer {

	FLOORS,
	WALLS(30, 31, 70, 71),
	FURNITURE(8, 9, 15, 22, 23, 24, 25, 31, 44, 45, 60, 61, 75, 76, 82, 94, 99);

	private final int[] solidTiles;

	RoomLayer(int... solidTiles) {
		this.solidTiles = solidTiles;
	}

	/**
	 * Checks if a specific tile index is solid.
	 *
	 * @param index The index of the tile.
	 * @return True if solid and has collision.
	 */
	public boolean isSolidTile(int index) {
		for(int tileIndex : solidTiles)
			if(tileIndex == index)
				return true;
		return false;
	}

	/**
	 * @return The written name of a layer in lowercase.
	 */
	public String getName() {
		return name().toLowerCase();
	}
}
