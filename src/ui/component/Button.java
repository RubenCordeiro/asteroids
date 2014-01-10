package ui.component;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import logic.entity.ImageEntity;
import logic.event.ActionEvent;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;

/**
 * General purpose button object that listens to mouse events
 * @author Ruben Cordeiro
 *
 */
public class Button extends ImageEntity implements Observable, MouseListener{
	protected ArrayList<Observer> observers;
	protected boolean changed;
	
	/**
	 * Message event that is sent to the listeners when the Button is pressed
	 */
	protected ActionEvent action;
	
	public Button()
	{
		super();
		observers = new ArrayList<Observer>();
		changed = false;
	}
	
	public Button(Component comp)
	{
		super(comp);
		observers = new ArrayList<Observer>();
		changed = false;
	}
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	@Override
	public void clearChanged() {
		changed = false;
		
	}
	
	@Override
	public int countObservers() {
		return observers.size();
	}
	@Override
	public void deleteObserver(Observer o) {
		for(int n = 0; n < observers.size(); ++n)
			if(observers.get(n).equals(o))
				observers.remove(n);
	}

	public void doClick()
	{
		notifyObservers();
	}

	@Override
	public boolean hasChanged() {
		return changed;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(getBounds().contains(x, y))
			notifyObservers();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		for(int n = 0; n < observers.size(); ++n)
			observers.get(n).update(this, action);
		
	}

	@Override
	public void notifyObservers(GameEvent e) {
		for(int n = 0; n < observers.size(); ++n)
			observers.get(n).update(this, e);
		
	}

	public void setAction(String msg)
	{
		action = new ActionEvent(msg);
	}

	/**
	 * Changes the boundaries of the Button object
	 * @param x new horizontal position
	 * @param y new vertical position
	 * @param width new width
	 * @param height new height
	 */
	public void setBounds(int x, int y, int width, int height)
	{
		this.x = x; this.y = y; 
		this.width = width; this.height = height;
	}

	@Override
	public void setChanged() {
		changed = true;
	}

}
