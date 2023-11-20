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
	private final int MAX_ITEMS = 9;
	private int selectedSlot;

	public Inventory(PlayerCharacter player) {
		this.player = player;
		this.items = new ArrayList<>();
		this.selectedSlot = 2;
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

		final int X_OFFSET = 350, Y_OFFSET = 600;
		final int SLOT_SIZE = 64;

		for(int i = 0; i < MAX_ITEMS; i++) {
			int x = X_OFFSET + (i * SLOT_SIZE) + i;
			g.setColor(isSlotSelected(i + 1) ? Color.RED : Color.WHITE);
			g.drawRect(x, Y_OFFSET, SLOT_SIZE, SLOT_SIZE);
			Item item = getItem(i);
			if(item == null)continue;
			g.drawImage(getItem(i).getDisplayImage(), x, Y_OFFSET, SLOT_SIZE, SLOT_SIZE, null);
		}
	}
}
