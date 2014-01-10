package logic.gamestate;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ui.component.Button;

import logic.entity.ImageEntity;
import logic.event.ActionEvent;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;

/**
 * Paused screen state
 * @author Ruben Cordeiro
 *
 */
public class PauseScreen extends GameState implements Observer
{
	ImageEntity _pauseImage;
	Button resumeGameButton, mainMenuButton;
	
	public PauseScreen()
	{
	}
	
	public PauseScreen(Component comp, Graphics2D g2d)
	{
		this.component = comp; this.g2d = g2d;
		_pauseImage = new ImageEntity(component);
		_pauseImage.load("/resources/pausedScreenBackground_complete.png");
		
		resumeGameButton = new Button(comp);
		mainMenuButton = new Button(comp);
		
		mainMenuButton.load("/resources/optionBackground.png");
		resumeGameButton.load("/resources/optionBackground.png");
		
		resumeGameButton.setX(394);
		resumeGameButton.setY(411);
		mainMenuButton.setX(398);
		mainMenuButton.setY(554);
		
		resumeGameButton.addObserver(this);
		mainMenuButton.addObserver(this);
		
		resumeGameButton.setAction("resume_game");
		mainMenuButton.setAction("main_menu");
	}
	
	public PauseScreen(Component comp, Graphics2D g2d, GameManager context)
	{
		this(comp, g2d);
		this.context = context;
	}
	
	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() 
	{
		g2d.drawImage(_pauseImage.getImage(), 0, 0, 1280 - 1, 720 - 1, component);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//if(e.getKeyCode() == KeyEvent.VK_SPACE)
			//context.goNext(Screens.MAIN);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		resumeGameButton.mouseClicked(e);
		mainMenuButton.mouseClicked(e);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, GameEvent e) {
		if(!(o instanceof Button))
			return;
		
		ActionEvent action = (ActionEvent)e;
		
		switch(action.getMessage())
		{
		case "resume_game": 
		{
			context.goNext(Screens.MAIN, GameManager.RESUME);
			break;
		}
		case "main_menu":
		{
			context.goNext(Screens.START, GameManager.RESUME);
			break;
		}
		default: 
			break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

}
