package logic;

import java.util.ArrayList;
import java.util.Random;

import logic.entity.PowerUp;
import logic.event.GameEvent;
import logic.event.SpawnEvent;
import logic.event.SpawnPowerUpEvent;
import logic.event.WaveSpawnEvent;

/**
 * Generates PowerUp objects and inserts them in a PowerUp list. 
 * @author Ruben Cordeiro
 *
 */
public class PowerUpSpawnManager extends SpawnManager<PowerUp>{
	
	private ArrayList<PowerUp> activePowerUps, powerUps;
	private transient double randValue = 0;
	
	Random rand = new Random();
	
	public PowerUpSpawnManager() 
	{
		activePowerUps = new ArrayList<PowerUp>();
		powerUps = new ArrayList<PowerUp>();
	}
	
	public PowerUpSpawnManager(ArrayList<PowerUp> powerUps)
	{
		this.activePowerUps = powerUps;
		this.powerUps = new ArrayList<PowerUp>();
	}
	
	public void addPowerUp(PowerUp newPowerUp)
	{
		powerUps.add(newPowerUp);
	}
	
	@Override
	public void update(logic.observer.Observable o, GameEvent e) {
		if(!(e instanceof SpawnPowerUpEvent))
			return;
		
		if(powerUps.size() == 0)
			return; 
		
		randValue = rand.nextInt(100) + 1;
		
		// A PowerUp object has a 12% chance of being spawned
		if(randValue >= 12)
			return;
		
		int nextIndex = rand.nextInt(powerUps.size());
		PowerUp pow = powerUps.get(nextIndex).clone();
		pow.setPosition(((SpawnPowerUpEvent)e).position());
		activePowerUps.add(pow);
	}

	@Override
	public void handle(SpawnEvent event) 
	{
	}

	@Override
	public void handle(WaveSpawnEvent event) 
	{
	}

}
