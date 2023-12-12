package Sprites;

import Game.GameWindow;

import java.awt.*;

/**
 * The Animation class manages a series of images (frames) and
 * the amount of time to display each frame.
 */
public class Animation {

	private final Image[] image;
	private final int duration;
	private int delay;
	private int current;
	
	public Animation(String name, String pose, int count, int duration, String type) {
		this.duration = duration;
		delay         = duration;
		image = new Image[count];

		if(pose == null || type == null)return;
		for(int i = 0; i < count; i++) {
			// Modify the path based on the computer OS and load the desired sprites.
			if (GameWindow.OS.contains("Mac"))
				   image[i] = Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/"+ name +"/" + name + "_" + pose + "/"+ name +"_" +pose +"_"+ i + "." + type);
			else image[i] = Toolkit.getDefaultToolkit().getImage("GroupGame\\src\\images\\"+ name +"\\" + name + "_" + pose + "\\"+ name +"_" +pose +"_"+ i + "." + type);
		}
	}

	/**
	 * Loads a sprite for the 'Blue Monster'
	 */
	public Animation(String name, int count, int duration) {
		this(name, null, count, duration, null);

		for(int i = 0; i < count; i++)
			image[i] = Toolkit.getDefaultToolkit().getImage(name + i + ".png");
	}

	/**
	 * Makes sure animation plays only once.
	 *
	 * @return True if the animation has completed.
	 */
	public boolean animationFinish() {
		boolean isLast = isLastImage();
		if(isLast) current = 0;
		return isLast;
	}

	/**
	 * @return True if the current displayed image is the last of the animation.
	 */
	private boolean isLastImage() {
		return current == image.length - 1;
	}
	
	public Image getStaticImage() {
		return image[0];
	}

	/**
	 * @return The current image or the following image within an animation if it's duration has expired.
	 */
	public Image getCurrentImage() {
		delay--;
		if(delay == 0) {
			if(!animationFinish())
				current++;
			delay = duration;
		}
		return image[current];
	}
}