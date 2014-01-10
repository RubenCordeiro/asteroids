package logic.visitor;

import logic.entity.Asteroid;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.Ship;


public class ShieldPowerupCollisionVisitor implements Visitor{
	protected int shieldBenefit;

	public ShieldPowerupCollisionVisitor(int shieldBenefit) {
		this.shieldBenefit = shieldBenefit;
	}

	@Override
	public void visit(Ship s) {

		s.incShield(shieldBenefit);
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
