package Sprites.Characters;
import Game.Game;


import Map.Room;
import Sprites.Sprite;

import java.awt.*;


public abstract class Character extends Sprite {

    private final Game game;

    private String name;
    private long pNum = 0L;        		// their phone number
    private boolean canInterview;  		// separates important and extra characters
    private boolean canCall;       		// can you call them
    public int actionLockCounter = 0;   //used to make character move

    public Character(Game game, String name, String[] pose, int imageCount, String filetype, int x, int y, int w, int h, double xDrawOffset, double yDrawOffset) {
        super(name, pose, imageCount, filetype, x, y, w, h, xDrawOffset, yDrawOffset);
        this.game = game;
    }

    public Character(Game game, String name, String[] pose, int imageCount, String filetype, int x, int y , int h, int w, long pNum, boolean canInterview) {
        this(game, name, pose, imageCount, filetype, x, y, w, h, 0, 0);
        this.name = name; // name of the character vs Name for the file
        this.pNum = pNum;
        this.canInterview = canInterview;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

    }

    /**
     * Modifies the position of the character
     * by its given velocity.
     */
    public void move() {
        if(!moving)return;

        // Check movement on x-axis
        if(canMoveTo(x + vx, y)) x += vx;
        else x = getXPosNextToTile();

        // Check movement on y-axis
        if(canMoveTo(x, y + vy)) y += vy;
        else y = getYPosAboveOrUnderTile();

        applyFrictionWithFloor();
    }

    /**
     * Checks if entity can move to the given location
     * within the current room.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     * @return True if the move is possible, otherwise, false.
     */
    public boolean canMoveTo(double x, double y) {
        Room room = game.getGameRoom();
        if(room != null) {
            if (room.isTransparent(x, y))
                if (room.isTransparent(x + w, y + h))
                    if (room.isTransparent(x + w, y))
                        return room.isTransparent(x, y + h);
        }
        return false;
    }

    /**
     * @return The x-coordinate closest to an adjacent tile, horizontally.
     */
    protected double getXPosNextToTile() {
        int tileXPos = getTileX() * Game.TILES_SIZE;
        if(vx > 0) { /* moving to the right */
            int xOffset = (int)(Game.TILES_SIZE - w);
            return tileXPos + xOffset - 1;
        } else return tileXPos;
    }

    /**
     * @return The y-coordinate closest to an adjacent tile, vertically.
     */
    private double getYPosAboveOrUnderTile() {
        int tileYPos = getTileY() * Game.TILES_SIZE;
        if(vy > 0) { /* going down */
            int yOffset = (int)(Game.TILES_SIZE - h);
            return tileYPos + yOffset - 1;
        } else return tileYPos;
    }

    /**
     * @return The level tile at the current x-location.
     */
    public int getTileX() {
        return (int) (x / Game.TILES_SIZE);
    }

    /**
     * @return The level tile at the current y-location.
     */
    public int getTileY() {
        return (int) (y / Game.TILES_SIZE);
    }

    /**
     * Resets all velocity related variables.
     */
    public void applyFrictionWithFloor() {
        vx = 0;
        vy = 0;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNum() {
        return pNum;
    }

    public boolean canBeInterviewed() {
        return canInterview;
    }

    public boolean canBeCalled() {
        return canCall;
    }

    public void setCanCall(boolean canCall) {
        this.canCall = canCall;
    }
}
