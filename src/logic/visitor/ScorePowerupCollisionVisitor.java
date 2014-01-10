package logic.visitor;

import logic.entity.Asteroid;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.Ship;
import logic.event.ScoreBumpEvent;


public class ScorePowerupCollisionVisitor implements Visitor{
	protected int scoreBump;
	
	public ScorePowerupCollisionVisitor(int scoreBump) {
		this.scoreBump = scoreBump;
	}
	
	@Override
	public void visit(Ship s) {
		s.setChanged();
		s.notifyObservers(new ScoreBumpEvent(scoreBump));
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
