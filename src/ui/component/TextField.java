package ui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import logic.entity.ImageEntity;
import logic.event.ActionEvent;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;

/**
 * General lightweight component that allows the editing of a single line of text
 * 
 * @author Ruben Cordeiro
 *
 */
public class TextField extends ImageEntity implements Observable, KeyListener, MouseListener{
	ActionEvent action;
	String buffer;
	private boolean selected, enabled;
	private int maxSize;
	private Color textColor;
	private Font font;

	public TextField(Component comp, Graphics2D g2d)
	{
		this.component = comp; this.g2d = g2d;
		buffer = new String();
		observers = new ArrayList<Observer>();
		selected = true;
		enabled = true;
	}

	public TextField(Component comp, Graphics2D g2d, Color textColor)
	{
		this(comp, g2d);
		this.textColor = textColor;
	}

	public TextField(Component comp, Graphics2D g2d, Color textColor, Font f)
	{
		this(comp, g2d, textColor);
		setFont(f);
	}

	public void setAction(String message)
	{
		action = new ActionEvent(message);
	}

	public void draw() {
		
		if(!enabled)
			return; 
		
		transform();
		g2d.drawImage(getImage(), at, component);

		if(textColor != null)
			g2d.setColor(textColor);

		Font prev = g2d.getFont();

		if(font != null)
			g2d.setFont(font);

		double textPosX = this.x, textPosY = y + (height / 2.0);
		g2d.drawString(buffer, (int)textPosX, (int)textPosY);

		g2d.setFont(prev);
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
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

	@Override
	public boolean hasChanged() {
		return changed;
	}

	@Override
	public int countObservers() {
		return observers.size();
	}

	@Override
	public void clearChanged() {
		changed = false;

	}

	@Override
	public void deleteObserver(Observer o) {
		for(int n = 0; n < observers.size(); ++n)
			if(observers.get(n).equals(o))
				observers.remove(n);
	}

	@Override
	public void setChanged() {
		changed = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int x = e.getX();
		int y = e.getY(); 

		if(getBounds().contains(x, y))
		{
			selected = true; 
			buffer = new String();
		}
		else
			selected = false;
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
	public void keyPressed(KeyEvent e) {
		if(!selected || !enabled)
			return;

		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			if(!buffer.isEmpty())
				buffer = buffer.substring(0, buffer.length()-1);
		}
		else
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				notifyObservers();
				buffer = new String();
			}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

		if(!selected || buffer.length() >= maxSize || !enabled)
			return;

		char character = e.getKeyChar();
		
		if(Character.isLetterOrDigit(character))
			buffer += character;
	}

	public Color getTextColor() {
		return textColor;
	}

	public String getText()
	{
		return buffer;
	}
	
	public void setText(String text)
	{
		buffer = text;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

}
