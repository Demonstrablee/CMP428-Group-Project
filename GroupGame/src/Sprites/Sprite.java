package Sprites;
import java.awt.*;

import Game.Game;
import Utils.Rect;

public class Sprite extends Rect {

	private final Animation[] animation;
	protected boolean moving;

	private final double xDrawOffset, yDrawOffset;
	
	final static int UP = 0;
	final static int DN = 1;
	final static int LT = 2;
	final static int RT = 3;
	final static int IDLE = 4;
	int pose = DN;

	public Sprite(String name, String[] pose, int imageCount, String filetype, int x, int y, int w, int h, double xDrawOffset, double yDrawOffset) {
		super(x, y, w, h);
		this.xDrawOffset = xDrawOffset * Game.SCALE;
		this.yDrawOffset = yDrawOffset * Game.SCALE;

		animation = new Animation[pose.length];

		for(int i = 0; i < pose.length; i ++)
			animation[i] = new Animation(name, pose[i], imageCount,  18, filetype);
	}

	public Sprite(String name, String[] pose, int imageCount, String filetype, int x, int y, int w, int h) {
		this(name, pose, imageCount, filetype, x, y, w, h, 0, 0);
	}

	public void update() {
		moving = false;
	}

	/**Change the animation to move to the left */
	public void goLT(int dx) {
		pose = LT;
		
		vx = -dx * Game.SCALE;

		moving = true;
	}

	/**Change the animation to move to the right */
	public void goRT(int dx) {
		pose = RT;
		
		vx = dx * Game.SCALE;

		moving = true;
	}

	/**Change the animation to move to the Up */
	public void goUP(int dy) {
		pose = UP;
		
		vy = -dy * Game.SCALE;

		moving = true;
	}

	/**Change the animation to move to the down */
	public void goDN(int dy) {
		pose = DN;
		
		vy = dy * Game.SCALE;

		moving = true;
	}
	
	/**Draw the character with the current animation */
	public void draw(Graphics pen) {
		Image sprite;

		// Let the idle animation play when the character isn't moving
		if (!moving)
			sprite = animation[IDLE].getCurrentImage();
		else
			sprite = animation[pose].getCurrentImage();

		int width  = (int) (sprite.getWidth(null)  * 1.75);
		int height = (int) (sprite.getHeight(null) * 1.75);

		// The draw offset shifts the image within the sprite hit-box.
		pen.drawImage(sprite, (int)(x - xDrawOffset), (int)(y - yDrawOffset), width, height, null);

		pen.setColor(c);
		pen.drawRect((int)x, (int)y, (int)w, (int)h);
	}
}