package logic.entity;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import logic.visitor.ShieldPowerupCollisionVisitor;

/**
 * PowerUp object that increases the Ship's shield.
 * @author Ruben Cordeiro
 *
 */
public class ShieldPowerUp extends PowerUp{
	protected int shieldBenefit;

	public ShieldPowerUp() 
	{
		super();
		ShieldPowerupCollisionVisitor visitor = new ShieldPowerupCollisionVisitor(0);
		this.setVisitor(visitor);
		priority = 0;
		setAlive(true);
		shieldBenefit = 0;
	}

	public ShieldPowerUp (Component a, Graphics2D g2d) {
		super(a, g2d);
		ShieldPowerupCollisionVisitor visitor = new ShieldPowerupCollisionVisitor(0);
		this.setVisitor(visitor);
		priority = 0;
		setAlive(true);
	}

	public ShieldPowerUp(Component a, Graphics2D g2d, int shieldBenefit) {
		super(a, g2d);
		this.shieldBenefit = shieldBenefit;
		ShieldPowerupCollisionVisitor visitor = new ShieldPowerupCollisionVisitor(shieldBenefit);
		this.setVisitor(visitor);
		priority = 0;
		setAlive(true);
	}

	public ShieldPowerUp(Component a, Graphics2D g2d, int shieldBenefit, int priority)
	{
		this(a, g2d, shieldBenefit);
		this.priority = priority;
	}
	
	public void setShieldBenefit(int shieldBenefit)
	{
		this.shieldBenefit = shieldBenefit; 
		this.setVisitor(new ShieldPowerupCollisionVisitor(shieldBenefit));
	}

	@Override
	public ShieldPowerUp clone()
	{
		ShieldPowerUp ret = new ShieldPowerUp(this.component, this.g2d);
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
		ret.shieldBenefit = shieldBenefit;
		ret.setVisitor(this.getVisitor());
		return ret;
	}
	
	@Override
	public void accept(Sprite s) {
		 s.getVisitor().visit(this);

	}
}
