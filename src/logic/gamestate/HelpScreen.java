package logic.gamestate;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import logic.Point2D;
import logic.entity.ImageEntity;
import logic.event.ActionEvent;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;
import ui.component.Button;

/**
 * Help screen state. It displays information relative to the game objects and user controls.
 * @author Ruben Cordeiro
 *
 */
public class HelpScreen extends GameState implements Observer{

	ImageEntity keyboardBackground, mouseBackground, background;
	Button backButton;
	
	public HelpScreen(Component comp, Graphics2D g2d)
	{
		this.component = comp; 
		this.g2d = g2d;
		
		background = new ImageEntity(comp, g2d);
		background.load("/resources/myBackground.png");
		
		keyboardBackground = new ImageEntity(comp,g2d);
		keyboardBackground.load("/resources/helpScreenKeyboard.png");
		
		mouseBackground = new ImageEntity(comp, g2d);
		mouseBackground.load("/resources/helpScreenMouse.png");
		
		backButton = new Button(comp);
		backButton.setGraphics(g2d);
		backButton.load("/resources/backButton.png");
		
		backButton.setPosition(new Point2D(575, 641));
		backButton.setAction("back");
		backButton.addObserver(this);
	}

	public HelpScreen(Component comp, Graphics2D g2d, GameManager context)
	{
		this(comp, g2d);
		this.context = context;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		backButton.mouseClicked(e);
		
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
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		
		background.draw();
		
		if(context.isMouseEnabled())
			mouseBackground.draw();
		else
			keyboardBackground.draw();
		
		backButton.draw();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, GameEvent e) {
		if(!(e instanceof ActionEvent))
			return; 
		
		ActionEvent ev = (ActionEvent)e; 
		
		switch(ev.getMessage())
		{
		case "back": 
		{
			context.goNext(Screens.START, GameManager.RESUME);
			break;
		}
		}
		
	}

}
