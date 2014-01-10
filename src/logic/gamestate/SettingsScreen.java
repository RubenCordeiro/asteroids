package logic.gamestate;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ui.component.Button;
import ui.component.ToggleButton;

import logic.Point2D;
import logic.entity.ImageEntity;
import logic.event.ActionEvent;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;

/**
 * Settings screen state. It displays and allows the modification of the general game settings: 
 * <p>
 * Background music activation/deactivation;
 * <p>
 * Sound effects activation/deactivation;
 * <p>
 * Input method (mouse or keyboard) choice.
 * 
 * @author Ruben Cordeiro
 *
 */
public class SettingsScreen extends GameState implements Observer{

	ImageEntity foreground, background;
	private Button  backButton; 
	private ToggleButton soundToggleButton1, inputToggleButton,soundToggleButton2;

	
	public SettingsScreen(Component comp, Graphics2D g2d)
	{
		component = comp; this.g2d = g2d;
		foreground = new ImageEntity(comp);
		background = new ImageEntity(comp);

		foreground.load("/resources/settingsMenu.png");
		background.load("/resources/myBackground.png");

		inputToggleButton = new ToggleButton(component);
		soundToggleButton1 = new ToggleButton(component);
		soundToggleButton2 = new ToggleButton(component);
		backButton = new Button(component);

		inputToggleButton.addObserver(this);
		soundToggleButton1.addObserver(this);
		soundToggleButton2.addObserver(this);
		backButton.addObserver(this);

		inputToggleButton.loadToggledImage("/resources/keyboardToggleButton.png");
		inputToggleButton.loadUntoggledImage("/resources/mouseToggleButton.png");

		soundToggleButton1.loadToggledImage("/resources/soundButtonToggled.png");
		soundToggleButton1.loadUntoggledImage("/resources/soundButtonUntoggled.png");

		soundToggleButton2.loadToggledImage("/resources/soundButtonToggled.png");
		soundToggleButton2.loadUntoggledImage("/resources/soundButtonUntoggled.png");

		backButton.load("/resources/backButton.png");

		inputToggleButton.setPosition(new Point2D(860, 392));
		soundToggleButton1.setPosition(new Point2D(860, 480));
		soundToggleButton2.setPosition(new Point2D(860, 562));
		backButton.setPosition(new Point2D(575, 641));

		inputToggleButton.setToggledAction("keyboard");
		inputToggleButton.setUntoggledAction("mouse");

		soundToggleButton1.setToggledAction("background_activate");
		soundToggleButton1.setUntoggledAction("background_deactivate");

		soundToggleButton2.setToggledAction("effects_activate");
		soundToggleButton2.setUntoggledAction("effects_deactivate");

		backButton.setAction("back");
	}
	
	public SettingsScreen(Component comp, Graphics2D g2d, GameManager context)
	{
		this(comp, g2d);
		this.context = context;
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		g2d.drawImage(background.getImage(), 0, 0, 1280 - 1, 720 - 1, component);
		g2d.drawImage(foreground.getImage(), 0, 0, 1280 - 1, 720 - 1, component);
		
		g2d.drawImage(inputToggleButton.getImage(), (int)inputToggleButton.getX(), (int)inputToggleButton.getY(), 
				inputToggleButton.width(), inputToggleButton.height(), component);
		
		g2d.drawImage(soundToggleButton1.getImage(), (int)soundToggleButton1.getX(), (int)soundToggleButton1.getY(), 
				soundToggleButton1.width(), soundToggleButton1.height(), component);
		
		g2d.drawImage(soundToggleButton2.getImage(), (int)soundToggleButton2.getX(), (int)soundToggleButton2.getY(), 
				soundToggleButton2.width(), soundToggleButton2.height(), component);
		
		g2d.drawImage(backButton.getImage(), (int)backButton.getX(), (int)backButton.getY(), 
				backButton.width(), backButton.height(), component);
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
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		inputToggleButton.mouseClicked(e);
		backButton.mouseClicked(e);
		soundToggleButton1.mouseClicked(e);
		soundToggleButton2.mouseClicked(e);
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, GameEvent e) 
	{
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
		case "effects_activate": 
		{
			context.getSoundManager().enableEffects(true);
			break;
		}
		case "effects_deactivate": 
		{
			context.getSoundManager().enableEffects(false);
			System.out.println("Sound deactivated");
			break;
		}
		case "background_activate": 
		{
			context.getSoundManager().enableBackgroundMusic(true);
			break;
		}
		case "background_deactivate": 
		{
			context.getSoundManager().stopBackgroundSound();
			context.getSoundManager().enableBackgroundMusic(false);
			break;
		}
		case "mouse": 
		{
			context.setMouseEnabled(true);
			break;
		}
		case "keyboard": 
		{
			context.setMouseEnabled(false);
			break;
		}
		default: 
			break;
		}
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

}
