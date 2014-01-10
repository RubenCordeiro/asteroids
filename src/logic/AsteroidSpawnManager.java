package logic;
import java.util.ArrayList;
import java.util.Random;

import logic.factory.AsteroidGenerator;

import logic.entity.Asteroid;
import logic.entity.Sprite;
import logic.event.GameEvent;
import logic.event.SpawnEvent;
import logic.event.WaveSpawnEvent;

/**
 * Generates asteroids and inserts them in a Asteroid list.
 * It listens to spawn events.
 * @author Ruben Cordeiro
 *
 */
public class AsteroidSpawnManager extends SpawnManager<Asteroid>{
	private ArrayList<Asteroid> asteroids;
	private AsteroidGenerator generator;
	private int numSplits;
	private double scaleFactor;
	private Random random = new Random();
	transient double ang;
	transient double x, y;
	private transient int splitIncreaseInterval = 0;

	public AsteroidSpawnManager(ArrayList<Asteroid> asts)
	{
		asteroids = asts;
		generator = new AsteroidGenerator();
		numSplits = 2;
		scaleFactor = 1 / (double)numSplits;
	}
	
	public AsteroidSpawnManager(ArrayList<Asteroid> asts, Asteroid model)
	{
		asteroids = asts; 
		generator = new AsteroidGenerator(model);
		numSplits = 2;
		scaleFactor = 1 / (double)numSplits;
	}

	/**
	 * Processes a SpawnEvent object information.
	 * A spawnEvent is sent by a single Asteroid.
	 */
	public void handle(SpawnEvent event)
	{
		int nInstances = event.numSpawns();

		Asteroid ast1, newAst;

		if(event.sprite() instanceof Asteroid)
			ast1 = (Asteroid)event.sprite();
		else
			return;

		for(;nInstances > 0; --nInstances)
		{
			newAst = generator.generate(ast1, scaleFactor);

			newAst.setMoveAngle(random.nextInt(360));
			newAst.setRotationRate(random.nextDouble());
			newAst.setFaceAngle(random.nextInt(360));

			ang = newAst.getMoveAngle() - 90;

			x = Angle.calcAngleMovementX(ang);
			y = Angle.calcAngleMovementY(ang);
			newAst.setVelocity(new Point2D(x, y));

			asteroids.add(newAst);
		}
	}

	/**
	 * Processes a WaveSpawnEvent's information.
	 */
	public void handle(WaveSpawnEvent event)
	{
		if(splitIncreaseInterval >= GameSettings.SPLIT_INCREASE_INTERVAL)
		{
			++numSplits;
			splitIncreaseInterval = 0;	
		}
		else
			++splitIncreaseInterval;
		
		scaleFactor = (1 - (1 / (double)numSplits));
		Asteroid newAst;
		int numSpawns = event.numSpawns();
		int width = event.width();
		int height = event.height();
		Sprite safeObject = event.safeObject();

		for(; numSpawns > 0; --numSpawns)
		{
			newAst = generator.generate(random.nextDouble() + 0.5);

			do
			{
				if(random.nextDouble() < 0.5)
				{
					if(random.nextDouble() < 0.5)
						x = 0;
					else
						x = width; 

					y = random.nextInt(height);
				}
				else
				{
					if(random.nextDouble() < 0.5)
						y = 0;
					else
						y = height;

					x = random.nextInt(width);
				}

				newAst.setPosition(new Point2D(x, y));
				
			} while(newAst.collidesWith(safeObject));

			newAst.setMoveAngle(random.nextInt(360));
			newAst.setRotationRate(random.nextDouble());
			newAst.setFaceAngle(random.nextInt(360));

			ang = newAst.getMoveAngle() - 90;

			x = Angle.calcAngleMovementX(ang);
			y = Angle.calcAngleMovementY(ang);
			newAst.setVelocity(new Point2D(x, y));
			newAst.setNumSplits(numSplits);
			newAst.setDamage(newAst.getDamage() + GameSettings.WAVESCOREBONUS);

			asteroids.add(newAst);
		}
	}

	@Override
	public void update(logic.observer.Observable o, GameEvent e) 
	{
		if(e instanceof SpawnEvent)
			handle((SpawnEvent)e);
		else
			if(e instanceof WaveSpawnEvent)
				handle((WaveSpawnEvent)e);
	}
}
