package logic.entity;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * A ScorePowerUp class increases the player's score.
 * @author Ruben Cordeiro
 *
 */
public class ScorePowerUp extends PowerUp{
	
	protected int scoreBump;
	
	public ScorePowerUp() 
	{
		super();
		scoreBump = 0;
		setAlive(true);
	}
	
	public ScorePowerUp (Component a, Graphics2D g2d) {
		super(a, g2d);
		setAlive(true);
	}
	
	public ScorePowerUp(Component a, Graphics2D g2d, int scoreBump) {
		super(a, g2d);
		this.scoreBump = scoreBump;
		setAlive(true);
	}
	
	@Override
	public ScorePowerUp clone()
	{
		ScorePowerUp ret = new ScorePowerUp(this.component, this.g2d);
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
		ret.scoreBump = scoreBump;
		return ret;
	}
	
	@Override
	public void accept(Sprite s) {
		s.getVisitor().visit(this);

	}
}
