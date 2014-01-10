package logic.entity;

import java.util.ArrayList;

import logic.Point2D;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;

/**
 * Base game entity class. It represents an object in the game space.
 * It stores information relative to the position, velocity and orientation of the object.  
 * @author Ruben Cordeiro
 *
 */
public abstract class GameObject implements Observable{
	protected boolean alive, changed; 
	protected double x, y; 
	protected double velX, velY; 
	protected double moveAngle, faceAngle; 
	protected ArrayList<Observer> observers;

	public GameObject()
	{
		changed = false;
		alive = false; 
		x = 0.0; 
		y =0.0; 
		velX = 0.0; 
		velY = 0.0; 
		moveAngle = 0.0; 
		faceAngle = 0.0; 
	}

	//getters
	public boolean isAlive() {return alive;}; 
	public double getX() {return x;}
	public double getY() {return y;}
	public double getVelX(){return velX;}
	public double getVelY(){return velY;}
	public double getMoveAngle(){ return moveAngle;}
	public double getFaceAngle() { return faceAngle;}
	public Point2D getPosition() {return new Point2D(x, y);}
	public Point2D getVelocity() {return new Point2D(velX, velY);}

	// mutators
	public void setAlive(boolean b) {alive = b; }
	public void setX(double x) {this.x = x; }
	public void setY(double y) {this.y = y;}
	public void incX(double i) {x += i; }
	public void incY(double i) {y += i; }
	public void setVelX(double velX) {this.velX = velX; }
	public void setVelY(double velY) {this.velY = velY; }
	public void setVelocity(Point2D vel) {velX = vel.X(); velY = vel.Y();}
	public void incVelX(double velFactor) {velX += velFactor;}
	public void incVelY(double velFactor) {velY += velFactor;}
	
	public void setFaceAngle(double angle) {faceAngle = angle;}
	public void setFaceAngle(float angle) {faceAngle = angle;}
	public void setFaceAngle(int angle) {faceAngle = angle;}
	
	public void setMoveAngle(double angle) {moveAngle = angle;}
	public void setMoveAngle(float angle) {moveAngle = ((double)angle);}
	public void setMoveAngle(int angle) {moveAngle = ((double)angle);}
	
	public void incMoveAngle(double angle) {moveAngle += angle;}
	public void incFaceAngle(double angle) {faceAngle += angle; }
	
	
	
	@Override
	public void addObserver(Observer o) {
		
		if(observers == null)
			observers = new ArrayList<Observer>();
		
		observers.add(o);

	}
	@Override
	public void notifyObservers() {
		
		if(observers == null)
			return; 
		
		if(hasChanged())
			for(int n = 0; n < observers.size(); ++n)
				observers.get(n).update(this, null);

		clearChanged();
	}

	@Override
	public void notifyObservers(GameEvent e) {
		
		if(observers == null)
			return; 
		
		if(hasChanged())
			for(int n = 0; n < observers.size(); ++n)
				observers.get(n).update(this, e);

		clearChanged();
	}

	@Override
	public boolean hasChanged() {return changed;}

	@Override
	public int countObservers() {
		if(observers == null)
			return 0; 
		else
			return observers.size();
	}

	@Override
	public void clearChanged() {changed = false;}

	@Override
	public void deleteObserver(Observer o) {
		if(observers == null)
			return;

		for(int n = 0; n < observers.size(); ++n)
			if(observers.get(n).equals(o))
				observers.remove(n);
	}

	@Override
	public void setChanged() {changed = true;}
}
