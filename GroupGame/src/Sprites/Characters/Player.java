package Sprites.Characters;


import Game.Game;
import Objects.HealthBar;
import Objects.Items.BandAid;
import UI.Inventory;
import Utils.Input;

import java.awt.Color;
import java.awt.Graphics;


public class Player extends Character {

    /**
     * Poses MUST be in the correct order to work within the Animation class!
     * Name does not matter, but the order contributes to what animation is played.
     */
    private final static String[] POSE = new String[] {"UP", "DOWN", "LT", "RT", "IDLE"};

    private final boolean[] pressing;

    private final Inventory inventory;

    private final HealthBar healthBar;

    public Player(Game game, int x, int y) {
        super(game, "MC", POSE, 7, "png", x, y, 20, 58, 6, 4);
        c = Color.RED;
        pressing = new boolean[1024];
        inventory = new Inventory(this);
        healthBar = new HealthBar(25, 25, 20, 20, 5);
        inventory.addItem(new BandAid(this));
    }

    /**
     * Updates the players movement based on
     * user input.
     */
    @Override
    public void update() {
        super.update();

        int speed = 1;
        if(isPressing(Input.W, Input.UP)) goUP(speed);
        if(isPressing(Input.S, Input.DN)) goDN(speed);
        if(isPressing(Input.A, Input.LT)) goLT(speed);
        if(isPressing(Input.D, Input.RT)) goRT(speed);
        if(isPressing(Input.U)) inventory.useSelectedItem();
        move();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        healthBar.draw(g);
        inventory.draw(g);
    }

    /**
     * Determines if a specific key is being pressed.
     *
     * @param keyCode The code of the key in question.
     * @return True if the key is being pressed.
     */
    public boolean isPressing(int... keyCode) {
        for(int code : keyCode) {
            if(pressing[code])
                return true;
        }
        return false;
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

}
