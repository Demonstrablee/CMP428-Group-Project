package Objects;

import java.awt.*;

public class Rect
{
	public double x;
	public double y;
	public double w;
	public double h;
	
	double vx = 0;
	double vy = 0;

	Color c = Color.BLACK;


	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void moveBy(int dx, int dy)
	{
		this.x += dx;
		this.y += dy;
	}
	
	public boolean wasLeftOf(Rect r)
	{
		return x - vx < r.x - w + 1;
	}
	
	public boolean wasRightOf(Rect r)
	{
		return x - vx > r.x + r.w - 1;
	}
	
	public boolean wasAbove(Rect r)
	{
		return y - vy < r.y - h + 1;
	}
	
	public boolean wasBelow(Rect r)
	{
		return y - vy > r.y + r.h - 1;
	}
	
	public void pushedOutOf(Rect r)
	{
		// this.x = 100;
		// this.y = 200;
		if(wasLeftOf(r))    pushLeftOf(r);
		if(wasRightOf(r))   pushRightOf(r);
		if(wasAbove(r))     pushAbove(r);
		if(wasBelow(r))     pushBelow(r);
	}
	
	public void pushLeftOf(Rect r)
	{
		this.x = r.x - w - 1;
	}
	
	public void pushRightOf(Rect r)
	{
		this.x = r.x + r.w + 1;
	}
	
	public void pushAbove(Rect r)
	{
		this.y = r.y - h - 1;
	}
	
	public void pushBelow(Rect r)
	{
		this.y = r.y +  r.h + 1;
	}
	
	
	public boolean overlaps(Rect r)
	{
		return (x     <= r.x + r.w) &&
			   (x + w >= r.x      ) &&
			   (y     <= r.y + r.h) &&
			   (y + h >= r.y      );
    }

	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
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