package UI;

import Objects.Items.Item;
import Sprites.Characters.PlayerCharacter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private final PlayerCharacter player;

	private final List<Item> items;
	private final int MAX_ITEMS = 6;
	private int selectedSlot;

	private final Image hotbarImg, selectedImg;

	public Inventory(PlayerCharacter player) {
		this.player = player;
		this.items = new ArrayList<>();
		this.selectedSlot = 2;

		// Load inventory sprites
		final String RESOURCE_DIR = "GroupGame/src/images/inventory/";
		this.hotbarImg = Toolkit.getDefaultToolkit().getImage(RESOURCE_DIR + "inventory.png");
		this.selectedImg = Toolkit.getDefaultToolkit().getImage(RESOURCE_DIR + "selected.png");
	}

	/**
	 * Adds an item to the inventory.
	 *
	 * @param item The item that will be added.
	 * @return True if the item was added or false if the inventory is full.
	 */
	public boolean addItem(Item item) {
		if(items.size() < MAX_ITEMS) {
			items.add(item);
			return true;
		} else return false;
	}

	public boolean removeItem(Item item) {
		return items.remove(item);
	}

	/**
	 * @return True if the number of items within the inventory is less than the max amount set at class scope.
	 */
	public boolean isFull() {
		return items.size() >= MAX_ITEMS;
	}

	public Item getItem(int index) {
		if(index >= items.size())return null;
		return items.get(index);
	}

	/**
	 * Checks if a certain slot is currently selected.
	 *
	 * @param slot The slot in question.
	 * @return True if selected, otherwise false.
	 */
	public boolean isSlotSelected(int slot) {
		return selectedSlot == slot;
	}

	/**
	 * Draws the inventory as a series of rectangles with the item displayed within.
	 * Updates selected slot using the #1-9 on keyboard.
	 *
	 * @param g The graphics context.
	 */
	public void draw(Graphics g) {
		for(int i = 1; i <= MAX_ITEMS; i++) {
			if(player.isPressing(KeyEvent.getExtendedKeyCodeForChar(String.valueOf(i).charAt(0))))
				selectedSlot = i;
		}

		double SCALE = 2;

		// Displays main inventory bar
		int DEFAULT_BAR_WIDTH = 244;
		int DEFAULT_BAR_HEIGHT = 44;
		int BAR_WIDTH = (int) (DEFAULT_BAR_WIDTH * SCALE);
		int BAR_HEIGHT = (int) (DEFAULT_BAR_HEIGHT * SCALE);

		int xStart = 375, yStart = 590;
		g.drawImage(hotbarImg, xStart, yStart, BAR_WIDTH, BAR_HEIGHT, null);

		// Displays the items in the bar
		final int SLOT_SIZE = (int) (32 * SCALE);
		final int ITEM_OFFSET = (int) (6 * SCALE);

		for(int i = 0; i < MAX_ITEMS; i++) {
			int xItemOffset = ITEM_OFFSET * (i + 1) + (i * 3);
			int x = xStart + (i * SLOT_SIZE) + i + xItemOffset;
			Item item = getItem(i);
			if(item == null)continue;
			g.drawImage(getItem(i).getDisplayImage(), x, yStart + ITEM_OFFSET, SLOT_SIZE, SLOT_SIZE, null);
		}

		// Shows which slot is currently selected
		int DEFAULT_SELECT_SIZE = 48;
		int SELECT_SIZE = (int) (DEFAULT_SELECT_SIZE * SCALE);

		int xSelectOffset = (int) (xStart - (2 * SCALE) + (40 * SCALE * (selectedSlot - 1)));
		int ySelectOfset = (int) (yStart - (2 * SCALE));
		g.drawImage(selectedImg, xSelectOffset, ySelectOfset, SELECT_SIZE, SELECT_SIZE, null);
	}
}
