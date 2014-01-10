package logic.event;
import logic.Point2D;

/**
 * Stores information about an animation that needs to be generated. 
 * @author Ruben Cordeiro
 *
 */
public class AnimationEvent extends GameEvent{
	

	private AnimationType _animationType;
	
	private Point2D _coordinates;
	
	public AnimationEvent(AnimationType t, double x, double y) 
	{
		_animationType = t;
		_coordinates = new Point2D(x, y);
	}
	
	public AnimationType type() {
		return _animationType;
	}
	
	public Point2D position() {return _coordinates;}
}
