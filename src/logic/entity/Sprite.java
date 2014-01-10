
package logic.entity;
import java.awt.*;
import java.awt.geom.AffineTransform;

import logic.Point2D;
import logic.visitor.Visitor;

public class Sprite extends ImageEntity {
	protected double rotRate; 
	protected int currentState;

	protected Visitor collisionStrategy;
	
	public Sprite() 
	{
		super();
		alive = true;
		rotRate = 0.0;
		currentState = 0;
	}
	
	public Sprite(Component a, Graphics2D g2d) 
	{
		super(a);
		alive = false;
		this.g2d = g2d;
		x = 0; y = 0;
		velX = 0; velY = 0;
		rotRate = 0.0;
		currentState = 0;
	}
	
	/**
	 * Draws the bounding box of a Sprite with a specific color
	 * @param c color of the bounding box to be drawn
	 */
	public void drawBounds(Color c) {
		g2d.setColor(c);
		g2d.draw(getBounds());
	}

	/**
	 * Updates a Sprite's position with the it's velocity components
	 */
	public void updatePosition() {
		x += velX;
		y += velY;
	}

	public double rotationRate() {return rotRate;}

	public void setRotationRate(double rate) {rotRate = rate;}

	/**
	 * Updates the Sprite's angle based on it's rotation rate
	 */
	public void updateRotation() {
		setFaceAngle((getFaceAngle() + rotRate));

		double faceAngle =  getFaceAngle();

		if(faceAngle < 0)
			setFaceAngle(360 - rotRate);
		else
			if(faceAngle > 360)
				setFaceAngle(rotRate);
	}

	public int state() {return currentState;}
	public void setState(int state) {currentState = state;}
	
	public Point2D center() { return new Point2D(getCenterX(), getCenterY());}

	public boolean alive() {return alive;}
	public void setAlive(boolean b) {alive = b;}

	/**
	 * Tests for collision with a rectangle
	 * @param r rectangle to be tested
	 * @return true if the object intersects with the rectangle, false otherwise
	 */
	public boolean collidesWith(Rectangle r) {return getBounds().intersects(r);}
	
	/**
	 * Tests for collision with an ImageEntity
	 * @param sprite ImageEntity to be tested
	 * @return true if they intersect, false otherwise
	 */
	public boolean collidesWith(ImageEntity sprite) {return getBounds().intersects(sprite.getBounds());}
	
	/**
	 * Tests for collision with a two dimensional point
	 * @param point Point2D to be tested
	 * @return true if the object contains the point, false otherwise
	 */
	public boolean collidesWith(Point2D point){ return (getBounds().contains(point.X(), point.Y())); }
	
	
	public void setVisitor(Visitor newV) {collisionStrategy = newV;}
	public Visitor getVisitor() {return collisionStrategy;}
	
	public Sprite clone() {
		Sprite cloned = new Sprite(component, g2d);
		cloned.rotRate = rotRate;
		cloned.currentState = currentState;
		cloned.x = x; cloned.y = y;
		cloned.velX = velX; cloned.velY = velY;
		cloned.moveAngle = moveAngle; cloned.faceAngle = faceAngle;
		cloned.image = image;
		cloned.at = (AffineTransform)at.clone();
		cloned.width = width; cloned.height = height;
		cloned.alive =  alive;
		return cloned;
	}

}