package logic.event;

import logic.entity.ImageEntity;

/**
 * Stores information about a generic entity to be spawned a given number of times.
 * @author Ruben Cordeiro
 *
 */
public class SpawnEvent extends GameEvent{
	protected ImageEntity s;
	protected int numSpawns;
	
	public SpawnEvent() {}
	
	public SpawnEvent(ImageEntity spr){s = spr; numSpawns = 0;}
	
	public SpawnEvent(ImageEntity spr, int nSpawns)
	{
		s = spr; numSpawns = nSpawns;
	}
	
	public ImageEntity sprite() {return s;}
	
	public int numSpawns() {return numSpawns;}
}
