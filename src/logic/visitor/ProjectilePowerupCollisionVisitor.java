package logic.visitor;

import logic.entity.Asteroid;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.Ship;

public class ProjectilePowerupCollisionVisitor implements Visitor{
	protected Ship.NumProjectiles _numProjectiles;
	
	public ProjectilePowerupCollisionVisitor(Ship.NumProjectiles numProj)
	{
		_numProjectiles = numProj;
	}
	
	@Override
	public void visit(ImageEntity s) {
	}

	@Override
	public void visit(Ship s) {
		s.setNumProjectiles(_numProjectiles);
	}

	@Override
	public void visit(Asteroid a) {

	}

	@Override
	public void visit(Projectile p) {
	}

	@Override
	public void visit(PowerUp p) {

	}

}
