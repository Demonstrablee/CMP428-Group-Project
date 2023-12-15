package Utils;

import java.awt.*;


/*
 * Collision does not occur if you move diagonally while adjacent to another rectangle
 * Only occurs when your above it or below it
*/
public class Rect {

	protected double x, y, w, h;
	protected double vx,vy;
	private boolean held = false;

	protected Color c = Color.BLACK;

	public Rect(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	// Movement

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;

		vx = dx;
		vy = dy;
	}

	// Collision

	public boolean wasLeftOf(Rect r) {
		return x + w - vx < r.x  + 1;
	}

	public boolean wasRightOf(Rect r) {
		return x - vx > r.x + r.w - 1;
	}

	public boolean wasAbove(Rect r) {
		return y + h - vy < r.y + 1;
	}

	public boolean wasBelow(Rect r) {
		return y - vy > r.y + r.h - 1;
	}

	public void pushedOutOf(Rect r) {
		if(wasLeftOf(r))    pushLeftOf(r);
		if(wasRightOf(r))   pushRightOf(r);
		if(wasAbove(r))     pushAbove(r);
		if(wasBelow(r))     pushBelow(r);
	}

	public void pushLeftOf(Rect r) {
		System.out.println("Player was at X: " + x);
		x = r.x - w - 1;
		System.out.println("Player moved up to X: " + x);
	}

	public void pushRightOf(Rect r) {
		System.out.println("Player was at X: " + x);
		x = r.x + r.w + 1;
		System.out.println("Player move down to X: " + x);
	}

	public void pushAbove(Rect r) {
		System.out.println("Player was at Y: " + y);
		y = r.y - h - 1;
		System.out.println("Player moved up to Y: " + y);
	}

	public void pushBelow(Rect r) {
		System.out.println("Player was at Y: " + y);
		y = r.y +  r.h + 1;
		System.out.println("Player moved down to Y: " + y);
	}

	public boolean contains(int mx, int my) {
		return (mx >= x  )   &&
				   (mx <= x+w)   &&
				   (my >= y  )   &&
				   (my <= y+h);
	}

	public boolean overlaps(Rect r) {
		return (x     <= r.x + r.w) &&
				   (x + w >= r.x      ) &&
				   (y     <= r.y + r.h) &&
				   (y + h >= r.y      );
	}
	
	public void chaseDamage(Rect r)
	{
		 if(wasAbove(r))     moveBy(0,8);
		 if(wasBelow(r))     moveBy(0,-8);
	}
	
	public void chase(Rect r)
	{
		 if(wasLeftOf(r))    moveBy(2,0);
		 if(wasRightOf(r))   moveBy(-2,0);
		 if(wasAbove(r))     moveBy(0,5);
		 if(wasBelow(r))     moveBy(0,-5);
	}

	public void grab() {
		held = true;
	}

	public void drop() {
		held = false;
	}

	public boolean isHeld() {
		return held;
	}

	public void draw(Graphics pen) {
		pen.setColor(c);
		pen.drawRect((int)x, (int)y, (int)w, (int)h);
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return w;
	}

	public double getHeight() {
		return h;
	}
}