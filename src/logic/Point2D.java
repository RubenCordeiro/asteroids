package logic;

/**
 * General bidimensional coordinates container. 
 * It avoids compiler warnings by overloading the accessors and mutators.
 * @author Ruben Cordeiro
 *
 */
public class Point2D {
	private double x, y;
	
	public Point2D (int x, int y)
	{
		this.x = x; this.y = y; 
	}
	
	public Point2D (float x, float y)
	{
		this.x = x; this.y = y; 
	}
	
	public Point2D(double x, double y) 
	{
		this.x = x; this.y = y;
	}
	
	public double X() { return x; }
	public double Y() { return y; }
	
	public void setX(int x) {this.x = x; }
	public void setX(float x) {this.x = x; }
	public void setX(double x) {this.x = x;}
	
	public void setY(int y) {this.y = y;}
	public void setY(float y) {this.y = y;}
	public void setY(double y) {this.y = y;}
}
