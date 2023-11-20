package Sprites;
import java.awt.*;

import Objects.Rect;

public class Sprite extends Rect
{
	Animation[] animation; // ARRAY OF ANIMATIONS
	
	final static int UP = 0;
	final static int DN = 1;
	final static int LT = 2;
	final static int RT = 3;
	final static int IDLE = 4;
	int pose = DN;
	
	static double scale = 1.4;
	
	public boolean moving = false;
	
	public Sprite(String name, String[] pose, int imagecount, int start, String filetype, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		
		animation = new Animation[pose.length];  
		
		for(int i = 0; i < pose.length; i ++)
		{
			animation[i] = new Animation(name, pose[i], imagecount, start,  18, filetype);
		}
	}
	/**Change the animation to move to the left */
	public void goLT(int dx)
	{
		pose = LT;
		
		moving = true;
		
		vx = -dx;
	}
	/**Change the animation to move to the right */
	public void goRT(int dx)
	{
		pose = RT;
		
		moving = true;
		
		vx = dx;
	}
	/**Change the animation to move to the Up */
	public void goUP(int dy)
	{
		pose = UP;
		
		moving = true;
		
		vy = -dy;
	}
	/**Change the animation to move to the down */
	public void goDN(int dy)
	{
		pose = DN;
		
		moving = true;
		
		vy = dy;
	}
	
	/**Draw the character with the current animation */
	public void draw(Graphics pen)
	{	
		Image temp;
		
		if (!moving) // if you hare not moving

			temp = animation[IDLE].getCurrentImage(); // let the idle animation play when the character isnt moving
		
		else
		
			temp = animation[pose].getCurrentImage();
	

		 w = scale * temp.getWidth(null);
		 h = scale * temp.getHeight(null);
			
		pen.drawImage(temp, (int)(x), (int)(y), (int)w, (int)h, null);
		
		pen.setColor(c); // use the color set for each character type
        pen.drawRect((int)(x), (int)(y), (int)w, (int)h);
		//pen.drawRect((int)(x - Camera.x), (int)(y - Camera.y), (int)w, (int)h);
	}
	
}