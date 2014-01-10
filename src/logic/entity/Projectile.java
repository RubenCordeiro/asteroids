package logic.entity;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import logic.visitor.Collideable;
import logic.visitor.ProjectileCollisionVisitor;

/**
 * A Projectile is a Sprite with a damage property.
 * @author Ruben Cordeiro
 *
 */
public class Projectile extends Sprite implements Collideable{

	protected int damage; 
	
	public Projectile()
	{
		super();
		this.setVisitor(new ProjectileCollisionVisitor());
		setAlive(true);
	}
	
	public Projectile(Component a, Graphics2D g2d) {
		super(a, g2d);
		this.setVisitor(new ProjectileCollisionVisitor());
		setAlive(true);
	}
	
	public Projectile(Component a, Graphics2D g2d, int damage)
	{
		this(a, g2d);
		this.damage = damage;
	}

	@Override
	public void accept(Sprite s) {
		s.getVisitor().visit(this);
	}
	
	@Override
	public Projectile clone() {
		Projectile cloned = new Projectile(component, g2d);
		cloned.rotRate = rotRate;
		cloned.currentState = currentState;
		cloned.x = x; cloned.y = y;
		cloned.velX = velX; cloned.velY = velY;
		cloned.moveAngle = moveAngle; cloned.faceAngle = faceAngle;
		cloned.image = image;
		cloned.at = (AffineTransform)at.clone();
		cloned.width = width; cloned.height = height;
		cloned.alive =  true;
		return cloned;
	}
	
}
