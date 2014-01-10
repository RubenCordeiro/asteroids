package logic.entity;

import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import sound.SoundType;

import logic.Point2D;
import logic.Angle;
import logic.event.SoundEvent;

import logic.visitor.Collideable;
import logic.visitor.ShipCollisionVisitor;

/**
 * This class has two different set of states: the vital status and the power-up status. 
 * Based on the current state, the image of the object is updated.
 * 
 * @author Ruben Cordeiro
 *
 */
public class Ship extends Sprite implements Collideable{

	public enum State {NORMAL, SHIELDED};

	public enum NumProjectiles {ONE, TWO, THREE, FOUR};

	protected NumProjectiles numProjectiles;

	protected State currState;

	protected int health, shield;

	Map<State, Image> imageStates;

	/**
	 * Default constructor for unit testing purposes
	 */
	public Ship()
	{
		super();
		ShipCollisionVisitor v = new ShipCollisionVisitor();
		this.setVisitor(v);
		currState = State.NORMAL;
		imageStates = new HashMap<State, Image>();
	}
	
	public Ship(Component a, Graphics2D g2d) 
	{
		super(a, g2d);
		ShipCollisionVisitor v = new ShipCollisionVisitor();
		this.setVisitor(v);
		currState = State.NORMAL;
		imageStates = new HashMap<State, Image>();
		Image shielded, normal;

		try
		{
			shielded = ImageIO.read(getClass().getResource("/resources/ship_shield.png"));
			normal = ImageIO.read(getClass().getResource("/resources/ship.png"));
			imageStates.put(State.SHIELDED,  shielded);
			imageStates.put(State.NORMAL,  normal);
			setImage(normal);
		}catch(IOException e) {
			e.printStackTrace();
		}

		numProjectiles = NumProjectiles.ONE;

		health = 0; shield = 0;
	}

	public Ship(Component comp, Graphics2D g2d, int health, int shield) {
		this(comp, g2d);
		this.health = health; this.shield = shield;
		currState = State.NORMAL;
	}

	/**
	 * Clones the model and inserts the new projectiles in the list. 
	 * According to the ship's power-up state, the number and orientation of the projectiles varies. 
	 * @param model Projectile to be cloned
	 * @param projectiles collection of active Projectile objects
	 */
	public void fireProjectile(Projectile model, ArrayList<Projectile> projectiles) {

		Projectile p = model.clone();
		int width = p.width();
		int height = p.height();
		double x = center().X() - width/2;
		double y = center().Y() - height/2;
		p.setPosition(new Point2D(x, y));
		p.setFaceAngle(this.faceAngle);
		p.setMoveAngle(this.faceAngle - 90);

		double angle = p.getMoveAngle();
		double svx = Angle.calcAngleMovementX(angle) * 5;
		double svy = Angle.calcAngleMovementY(angle) * 5;
		p.setVelocity(new Point2D(svx, svy));
		p.setAlive(true);

		switch(numProjectiles)
		{
		case ONE: 
			projectiles.add(p);
			break;
		case TWO:
		{
			Projectile p2 = p.clone();
			Angle.adjustDirection(p2, 4);
			Angle.adjustDirection(p, -4);
			projectiles.add(p);
			projectiles.add(p2);
			break;
		}
		case THREE: 
		{
			Projectile p2 = p.clone();
			Projectile p3 = p.clone();
			Angle.adjustDirection(p, -4);
			Angle.adjustDirection(p3, 4);
			projectiles.add(p);
			projectiles.add(p2);
			projectiles.add(p3);
			break;
		}
		case FOUR: 
		{
			Projectile p2, p3, p4;
			p2 = p.clone();
			p3 = p.clone();
			p4 = p.clone();
			Angle.adjustDirection(p, -5);
			Angle.adjustDirection(p2, 5);
			Angle.adjustDirection(p3, -10);
			Angle.adjustDirection(p4, 10);
			projectiles.add(p);
			projectiles.add(p2);
			projectiles.add(p3);
			projectiles.add(p4);
			break;
		}
		default: 
			break;
		}

		notifyObservers(new SoundEvent(SoundType.SHOT));
	}

	public State getState() {return currState;}
	
	public void setState(State s) 
	{
		currState = s;
		if(imageStates.containsKey(currState))
			setImage(imageStates.get(currState));

		setChanged();
	}

	public void setNumProjectiles(Ship.NumProjectiles number)
	{
		numProjectiles = number;
	}

	public int health() {return health;}
	public int shield() {return shield;}

	public void incHealth(int healthBenefit) 
	{
		health += healthBenefit; 
		setChanged(); 
		notifyObservers();
	}
	
	public void incShield(int shieldBenefit)
	{
		shield += shieldBenefit; 
		if(shield > 0) 
			setState(State.SHIELDED);

		notifyObservers();
	}

	public void decHealth(int healthDamage) 
	{
		health -= healthDamage; 
		
		if(health <= 0)
		{
			setAlive(false);
			health = 0;
		}

		setChanged(); 
		notifyObservers();
	}

	public void decShield(int shieldDamage) 
	{
		shield -= shieldDamage; 

		if(shield <= 0)
		{
			setState(State.NORMAL);
			shield = 0;
		}

		notifyObservers();
	}
	
	public NumProjectiles numProjectiles()
	{
		return numProjectiles;
	}

	/**
	 * Draws the Ship's current state representation.
	 */
	@Override
	public void draw() {
		transform();
		if(imageStates.containsKey(currState))
			g2d.drawImage(imageStates.get(currState), at, component);
	}

	@Override
	public void accept(Sprite s) {
		s.getVisitor().visit(this);

	}
}
