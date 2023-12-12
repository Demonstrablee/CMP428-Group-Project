package Objects;

import Utils.Rect;

import java.awt.Graphics;

public class Wall extends Rect
{
	//Rect resizer;
	
	public Wall(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		
		//resizer = new Rect(x+w-20, y+h-20, 20, 20);
	}
/*	
	public void moveBy(int dx, int dy)
	{
		super.moveBy(dx, dy);
		
		resizer.moveBy(dx,  dy);
	}
	
	public void resizeBy(int dw, int dh)
	{
		super.resizeBy(dw, dh);
		
		resizer.moveBy(dw, dh);
	}
*/	
	public String toString()
	{
		return "new Wall(" + x + ", " + y + ", " + w + ", " + h + ");";
	}
	
	
	public void draw(Graphics pen)
	{
		super.draw(pen);
		
		//resizer.draw(pen);
	}

}