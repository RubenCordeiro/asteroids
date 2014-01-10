package logic.visitor;

import sound.SoundType;
import logic.Point2D;
import logic.entity.Asteroid;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.Ship;

import logic.event.AnimationEvent;
import logic.event.AnimationType;
import logic.event.SoundEvent;

public class AsteroidCollisionVisitor implements Visitor{
	public int damage;
	
	public AsteroidCollisionVisitor() {damage = 0;}
	
	public AsteroidCollisionVisitor(int dmg) {damage = dmg;}
	
	@Override
	public void visit(Ship s) {
		
		switch(s.getState())
		{
		case SHIELDED:
		{
			s.decShield(damage);
			
			s.setChanged();
			s.notifyObservers(new SoundEvent(SoundType.EXPLOSION));
			break;
			
		}
		case NORMAL:
		{
			s.decHealth(damage);
			
			s.setChanged();
			s.notifyObservers(new SoundEvent(SoundType.EXPLOSION));
			
			s.setChanged(); 
			Point2D center = s.center();
			
			if(!s.isAlive())
			{
				s.notifyObservers(new AnimationEvent(AnimationType.EXPLOSION_BIG,center.X(), center.Y()));
				s.setChanged();
				s.notifyObservers(new SoundEvent(SoundType.EXPLOSION));
			}
			break;
		}
		default:
			break;
		}
		
		s.setNumProjectiles(Ship.NumProjectiles.ONE);
	}

	@Override
	public void visit(Asteroid a) {
		
	}

	@Override
	public void visit(Projectile p) {
		p.setAlive(false);
	}

	@Override
	public void visit(ImageEntity s) {

	}

	@Override
	public void visit(PowerUp p) {

	}

}
