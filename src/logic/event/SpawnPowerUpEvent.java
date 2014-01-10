package logic.event;

import logic.Point2D;

/**
 * Stores information about a PowerUp to be spawned.
 * @author Ruben Cordeiro
 *
 */
public class SpawnPowerUpEvent extends GameEvent{
	protected Point2D position;
	public SpawnPowerUpEvent() {
		position = new Point2D(0,0);
	}
	
	public SpawnPowerUpEvent(double x, double y) 
	{
		position = new Point2D(x, y);
	}
	
	public Point2D position() 
	{
		return position;
	}
}
