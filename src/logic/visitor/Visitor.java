package logic.visitor;

import logic.entity.Asteroid;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.Ship;

/**
 * The root class from which all visitor objects are derived.
 * @author Ruben Cordeiro
 *
 */
public interface Visitor {
	
	public void visit(ImageEntity s);
	
	public void visit(Ship s);

	public void visit(Asteroid a);

	public void visit(Projectile p);
	
	public void visit(PowerUp p);
}
