package logic;

import logic.entity.Sprite;

/**
 * Utility class with trignometric calculations methods.
 * @author Ruben Cordeiro
 *
 */
public abstract class Angle {
	
	public static double calcAngleMovementY(double angle) 
	{
		return (double) (Math.sin(angle*Math.PI / 180));
	}
	
	public static double calcAngleMovementX(double angle) 
	{
		return (double) (Math.cos(angle * Math.PI / 180));
	}
	
	/**
	 * Adjusts a Sprite's angle
	 * @param sprite Sprite object to be adjusted
	 */
	public static void adjustDirection(Sprite sprite, double angle)
	{
		angle = sprite.getFaceAngle() + angle;
		if(angle < 0) angle +=360;
		else if(angle > 360) angle -=360;
		sprite.setFaceAngle(angle);
		sprite.setMoveAngle(sprite.getFaceAngle() - 90);
		angle = sprite.getMoveAngle();
		double svx = calcAngleMovementX(angle) * 5;
		double svy = calcAngleMovementY(angle) * 5;
		sprite.setVelX(svx);
		sprite.setVelY(svy);
	}
}
