package logic.event;

import logic.entity.Sprite;

/**
 * Stores information about a group of objects to be spawned in a specific area.
 * @author Ruben Cordeiro
 *
 */
public class WaveSpawnEvent extends GameEvent{
	protected int numSpawns;
	protected Sprite safeObject;
	protected int boundsWidth, boundsHeight;
	
	public WaveSpawnEvent()
	{
		numSpawns = 0;
	}
	
	public WaveSpawnEvent(int number)
	{
		numSpawns = number;
	}
	
	/**
	 * Constructs a WaveSpawnEvent
	 * @param number number of objects to spawn
	 * @param safeObject identifies the area where objects can't spawn
	 * @param boundsWidth width of the spawn area
	 * @param boundsHeight height of the spawn area
	 */
	public WaveSpawnEvent(int number, Sprite safeObject, int boundsWidth, int boundsHeight)
	{
		this(number);
		this.safeObject = safeObject; this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
	}
	
	public int numSpawns() 
	{
		return numSpawns;
	}
	
	public Sprite safeObject()
	{
		return safeObject;
	}
	
	public int width()
	{
		return boundsWidth;
	}
	
	public int height()
	{
		return boundsHeight;
	}
}
