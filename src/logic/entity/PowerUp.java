package logic.entity;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import logic.visitor.Collideable;

/**
 * Power-up's base class. It represents an object that increases the Sprite's status
 * @author Ruben Cordeiro
 */
public class PowerUp extends Sprite implements Cloneable, Collideable{
	protected int priority;
	
	public PowerUp() 
	{
		super();
	}
	public PowerUp(Component a, Graphics2D g2d) {
		super(a, g2d);
	}
	
	
	@Override
	public void accept(Sprite s) 
	{
		
	}
	
	@Override
	public PowerUp clone()
	{
		PowerUp ret = new PowerUp(this.component, this.g2d);
		ret.rotRate = rotRate;
		ret.currentState = currentState;
		ret.x = x; ret.y = y;
		ret.velX = velX; ret.velY = velY;
		ret.moveAngle = moveAngle; ret.faceAngle = faceAngle;
		ret.image = image;
		ret.at = (AffineTransform)at.clone();
		ret.width = width; ret.height = height;
		ret.alive =  alive;
		ret.priority = priority;
		return ret;
	}
}
