package logic.observer;

import logic.event.GameEvent;

/**
 * Represents an object that receives notifications from an Observable object
 * @author Ruben Cordeiro
 *
 */
public interface Observer 
{
	public void update(Observable o, GameEvent e);
}
