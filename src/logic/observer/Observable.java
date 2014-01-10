package logic.observer;

import logic.event.GameEvent;

/**
 * This class represents an observable object, or "data" in the model-view paradigm
 * @author Ruben Cordeiro
 *
 */
public interface Observable {

	public void addObserver(Observer o);

	/**
	 * If this object has changed, then notify all of its observers.
	 */
	public void notifyObservers();
	
	/**
	 * If this object has changed, then notify all of its observers with the an event.
	 * @param e event to be sent to the observers
	 */
	public void notifyObservers(GameEvent e);
	
	/**
	 * Indicates whether the observable object has changed
	 * @return true if object has changed, false otherwise
	 */
	public boolean hasChanged();
	
	/**
	 * Returns the number of observers of this Observable object
	 * @return the number of observers of this Observable object
	 */
	public int countObservers();
	
	/**
	 * Clear the changed flag of the Observable object
	 */
	public void clearChanged();
	
	/**
	 * Remove the observer from the observers list
	 * @param o observer to be removed
	 */
	public void deleteObserver(Observer o);
	
	/**
	 * Set the changed flag of the Observable object
	 */
	public void setChanged();
}
