package Sprites.Characters;


import Objects.HealthBar;
import Objects.Items.BandAid;
import UI.Inventory;

import java.awt.Color;
import java.awt.Graphics;


public class PlayerCharacter extends Character {

    /**
     * Poses MUST be in the correct order to work within the Animation class!
     * Name does not matter, but the order contributes to what animation is played.
     */
    static String [] pose = new String[] {"UP", "DOWN", "LT", "RT","IDLE"};

    private final boolean[] pressing;

    private final Inventory inventory;

    private final HealthBar healthBar;

    public PlayerCharacter(int x, int y, int w, int h) {
        super("MC", pose, 7, 0, "png", x, y, w, h);
        c = Color.RED;
        pressing = new boolean[1024];
        inventory = new Inventory(this);
        healthBar = new HealthBar(100, 100, 20, 20, 3);
        inventory.addItem(new BandAid(this));
        inventory.addItem(new BandAid(this));
        inventory.addItem(new BandAid(this));
    }

    /**
     * Determines if a specific key is being pressed.
     *
     * @param keyCode The code of the key in question.
     * @return True if the key is being pressed.
     */
    public boolean isPressing(int keyCode) {
        return pressing[keyCode];
    }

    /**
     * Updates the value for a key interaction.
     *
     * @param keyCode The code of the interacted key.
     * @param value   Whether the key was pressed or released.
     */
    public void setPressing(int keyCode, boolean value) {
        pressing[keyCode] = value;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    @Override
    public void draw(Graphics pen) {
        super.draw(pen);
    }
}
