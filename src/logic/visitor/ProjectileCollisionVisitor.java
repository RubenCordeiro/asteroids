package logic.visitor;

import sound.SoundType;
import logic.entity.Asteroid;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.Ship;
import logic.event.AnimationEvent;
import logic.event.AnimationType;
import logic.event.ScoreBumpEvent;
import logic.event.SoundEvent;
import logic.event.SpawnEvent;
import logic.event.SpawnPowerUpEvent;


public class ProjectileCollisionVisitor implements Visitor{

	@Override
	public void visit(Ship s) {

	}

	@Override
	public void visit(Asteroid a) {
		
		a.setAlive(false);
		a.notifyObservers(new ScoreBumpEvent(a.getDamage()));
		
		a.setChanged();
		a.notifyObservers(new SoundEvent(SoundType.EXPLOSION));
		
		a.setChanged();
		if(a.getNumSplits() > 0)
		{
			a.notifyObservers(new AnimationEvent(AnimationType.EXPLOSION_BIG, a.center().X(), a.center().Y()));
		}
		else
			a.notifyObservers(new AnimationEvent(AnimationType.EXPLOSION_SMALL, a.center().X(), a.center().Y()));
		
		a.setChanged();
		a.notifyObservers(new SpawnEvent(a,a.getNumSplits()));
		
		a.setChanged();
		a.notifyObservers(new SpawnPowerUpEvent(a.center().X(), a.center().Y()));
		
	}

	@Override
	public void visit(Projectile p) {

	}

	@Override
	public void visit(ImageEntity s) {

	}

	@Override
	public void visit(PowerUp p) {
		p.setAlive(false);
	}
}
