package logic.entity;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import logic.visitor.ProjectilePowerupCollisionVisitor;

/**
 * A ProjectilePowerUp is an object that increases the Ship's NumProjectiles status.
 * @author Ruben Cordeiro
 *
 */
public class ProjectilePowerUp extends PowerUp
{
	protected Ship.NumProjectiles _numProjectiles;
	
	public ProjectilePowerUp()
	{
		super();
		ProjectilePowerupCollisionVisitor visitor = new ProjectilePowerupCollisionVisitor(Ship.NumProjectiles.ONE);
		this.setVisitor(visitor);
		priority = 0;
		
	}
	
	public ProjectilePowerUp (Component a, Graphics2D g2d) {
		super(a, g2d);
		ProjectilePowerupCollisionVisitor visitor = new ProjectilePowerupCollisionVisitor(Ship.NumProjectiles.ONE);
		this.setVisitor(visitor);
		priority = 0;
	}

	public ProjectilePowerUp(Component a, Graphics2D g2d, Ship.NumProjectiles numProj) {
		super(a, g2d);
		_numProjectiles = numProj;
		ProjectilePowerupCollisionVisitor visitor = new ProjectilePowerupCollisionVisitor(numProj);
		this.setVisitor(visitor);
		priority = 0;
		setAlive(true);
	}

	public ProjectilePowerUp(Component a, Graphics2D g2d, Ship.NumProjectiles numProj, int priority)
	{
		this(a, g2d, numProj);
		this.priority = priority;
	}
	
	public ProjectilePowerUp(Ship.NumProjectiles numProj)
	{
		super();
		_numProjectiles = numProj;
		ProjectilePowerupCollisionVisitor visitor = new ProjectilePowerupCollisionVisitor(numProj);
		this.setVisitor(visitor);
		setAlive(true);
	}
	
	@Override
	public ProjectilePowerUp clone()
	{
		ProjectilePowerUp ret = new ProjectilePowerUp(this.component, this.g2d);
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
		ret.setVisitor(this.getVisitor());
		ret._numProjectiles = _numProjectiles;
		return ret;
	}
	
	@Override
	public void accept(Sprite s) {
		s.getVisitor().visit(this);
	}
}
