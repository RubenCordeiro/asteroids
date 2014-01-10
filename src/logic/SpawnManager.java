package logic;

import logic.event.SpawnEvent;
import logic.event.WaveSpawnEvent;
import logic.observer.Observer;

/**
 * Base class for all the spawn managers.
 * @author Ruben Cordeiro
 *
 * @param <T> Object class to be spawned
 */
public abstract class SpawnManager<T> implements Observer{

	public abstract void handle(SpawnEvent event);
	public abstract void handle(WaveSpawnEvent event);
}
