package Objects;

import java.awt.*;


/*
 * Collision does not occur if you move horizontally while adjacent to another rectangle
 * Only occurs when your above it or below it
*/

public class Rect
{
	public double x;
	public double y;
	
	public double w;
	public double h;
	
	protected double vx = 0;
	protected double vy = 0;

	protected Color c = Color.BLACK;


	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void moveBy(int dx, int dy)
	{
		this.x += (double)dx;
		this.y += (double)dy;
		
		this.vx = (double)dx;
		this.vy = (double)dy;
		
		System.out.println("x position: "+ x);
		System.out.println("y position: "+ y);
	}
	
	public boolean wasLeftOf(Rect r)
	{
		boolean result = x + w - vx < r.x  + 1;
		return result;
	}
	
	public boolean wasRightOf(Rect r)
	{
		boolean result = x - vx > r.x + r.w - 1;
		return result;
	}
	
	public boolean wasAbove(Rect r)
	{//( ALT: is the player above the rectangle)
		boolean result = y + h - vy < r.y + 1;
		return result;
	}
	
	public boolean wasBelow(Rect r) // lower player(small y) high
	{ // is the player below the rectangle (works )
		boolean result = y - vy > r.y + r.h - 1;
		return result;
	}
	
	public void pushedOutOf(Rect r)
	{
	
		 if(wasLeftOf(r))    pushLeftOf(r);
		 if(wasRightOf(r))   pushRightOf(r);
		 if(wasAbove(r))     pushAbove(r);
		 if(wasBelow(r))     pushBelow(r);
	}
	
	public void pushLeftOf(Rect r)
	{
		System.out.println("Player was at X: " + x);
		this.x = r.x - w - 1;
		System.out.println("Player moved up to X: " + x);
	}
	
	public void pushRightOf(Rect r)
	{
		System.out.println("Player was at X: " + x);
		this.x = r.x + r.w + 1;
		System.out.println("Player move down to X: " + x);
	}
	
	public void pushAbove(Rect r)
	{
		System.out.println("Player was at Y: " + y);
		this.y = r.y - h - 1;
		System.out.println("Player moved up to Y: " + y);
	}
	
	public void pushBelow(Rect r)
	{
		System.out.println("Player was at Y: " + y);
		this.y = r.y +  r.h + 1;
		System.out.println("Player moved down to Y: " + y);
	}

	public boolean contains(int mx, int my)
	{
		return (mx >= x  )   && 
			   (mx <= x+w)   && 			   
			   (my >= y  )   && 
			   (my <= y+h);
	}
	public boolean overlaps(Rect r)
	{
		boolean result = (x     <= r.x + r.w) &&
			   (x + w >= r.x      ) &&
			   (y     <= r.y + r.h) &&
			   (y + h >= r.y      );
		// if (result){
		// 	System.out.println(result);}

		
		return result;
			
    }
	// 	public double getX() {
	// 	return x;
	// }
	// public double getY() {
	// 	return y;
	// }
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}

	public void setLocation(double x, double y){
		this.x = x;
		this.y = y;
	}


	public void setColor(Color c) {
		this.c = c;
	}
	public void draw(Graphics pen)
	{
		pen.setColor(c);
		pen.drawRect((int)x, (int)y, (int)w, (int)h);
	}


}