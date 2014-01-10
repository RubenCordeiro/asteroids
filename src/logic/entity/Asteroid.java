package logic.entity;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import logic.visitor.AsteroidCollisionVisitor;
import logic.visitor.Collideable;

/**
 * An Asteroid is an object that damages the Ship.
 * It stores information about it's damage and splits.
 * @author Ruben Cordeiro
 *
 */
public class Asteroid extends Sprite implements Collideable{
	
	protected int damage;
	
	/**
	 * Number of smaller Asteroid objects that can be generated
	 */
	protected int splits;
	
	/**
	 * Default constructor for unit testing purposes
	 */
	public Asteroid()
	{
		super();
		this.setVisitor(new AsteroidCollisionVisitor());
		damage = 0;
		splits = 0;
	}
	
	public Asteroid(Component a, Graphics2D g2d) {
		super(a, g2d);
		this.setVisitor(new AsteroidCollisionVisitor());
		damage = 0;
		splits = 0;
	}
	
	public Asteroid(Component a, Graphics2D g2d, int damage) {
		super(a, g2d);
		this.setVisitor(new AsteroidCollisionVisitor(damage));
		this.damage = damage;
		splits = 0;
	}

	@Override
	public void accept(Sprite s) {
		s.getVisitor().visit(this);
	}
		
	public int getNumSplits() {return splits;}
	public void setNumSplits(int instanceNumber) {splits = instanceNumber;}
	
	public int getDamage() {return damage;}
	public void setDamage(int damage) 
	{
		this.damage = damage;
		this.setVisitor(new AsteroidCollisionVisitor(damage));
	}
	
	@Override
	public void setAlive(boolean b)
	{
		alive = b; 
		setChanged();
	}
	
	@Override
	public Asteroid clone() {
		Asteroid ret = new Asteroid(this.component, this.g2d, this.damage);
		ret.setAlive(true);
		ret.splits = splits - 2;
		ret.rotRate = rotRate;
		ret.currentState = currentState;
		ret.x = x; ret.y = y;
		ret.velX = velX; ret.velY = velY;
		ret.moveAngle = moveAngle; ret.faceAngle = faceAngle;
		ret.image = image;
		ret.at = (AffineTransform)at.clone();
		ret.width = width; ret.height = height;
		ret.observers = observers;
		ret.setVisitor(this.getVisitor());
		return ret;
	}
}
