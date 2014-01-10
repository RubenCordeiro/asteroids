package logic.visitor;

import logic.entity.Asteroid;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.Ship;


public class HealthPowerupCollisionVisitor implements Visitor{
	protected int healthBenefit;

	public HealthPowerupCollisionVisitor(int healthBenefit) {
		this.healthBenefit = healthBenefit;
	}


	@Override
	public void visit(Ship s) {
		s.incHealth(healthBenefit);
		s.setChanged();
		s.notifyObservers();
	}

	@Override
	public void visit(Asteroid a) {
	}

	@Override
	public void visit(Projectile p) {
	}


	@Override
	public void visit(ImageEntity s) {

	}


	@Override
	public void visit(PowerUp p) {

	}

}
