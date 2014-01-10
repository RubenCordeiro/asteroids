package logic.entity;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import logic.visitor.HealthPowerupCollisionVisitor;

public class HealthPowerUp extends PowerUp{
	protected int healthBenefit;
	
	public HealthPowerUp() 
	{
		super();
		HealthPowerupCollisionVisitor visitor = new HealthPowerupCollisionVisitor(0);
		this.setVisitor(visitor);
		healthBenefit = 0;
		setAlive(true);
	}
	
	public HealthPowerUp (Component a, Graphics2D g2d) {
		super(a, g2d);
		HealthPowerupCollisionVisitor visitor = new HealthPowerupCollisionVisitor(0);
		this.setVisitor(visitor);
		priority = 0;
		setAlive(true);
	}
	
	public void setHealthBenefit(int healthBenefit)
	{
		this.healthBenefit = healthBenefit;
		this.setVisitor(new HealthPowerupCollisionVisitor(healthBenefit));
	}
	
	public HealthPowerUp(Component a, Graphics2D g2d, int healthBenefit) {
		super(a, g2d);
		this.healthBenefit = healthBenefit;
		HealthPowerupCollisionVisitor visitor = new HealthPowerupCollisionVisitor(healthBenefit);
		this.setVisitor(visitor);
		priority = 0;
		setAlive(true);
	}
	
	public HealthPowerUp(Component a, Graphics2D g2d, int healthBenefit, int priority) {
		this(a, g2d, healthBenefit);
		this.priority = priority;
	}
	
	@Override
	public HealthPowerUp clone()
	{
		HealthPowerUp ret = new HealthPowerUp(this.component, this.g2d);
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
		ret.healthBenefit = healthBenefit;
		ret.setVisitor(this.getVisitor());
		return ret;
	}
	
	@Override
	public void accept(Sprite s) {
		s.getVisitor().visit(this);

	}
}
